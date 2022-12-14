/*
 * Copyright 2019 GridGain Systems, Inc. and Contributors.
 *
 * Licensed under the GridGain Community Edition License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.gridgain.com/products/software/community-edition/gridgain-community-edition-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.internal.processors.query.h2.database.io;

import java.util.List;
import org.apache.ignite.IgniteCheckedException;
import org.apache.ignite.internal.pagemem.PageUtils;
import org.apache.ignite.internal.processors.cache.persistence.tree.BPlusTree;
import org.apache.ignite.internal.processors.cache.persistence.tree.io.BPlusIO;
import org.apache.ignite.internal.processors.cache.persistence.tree.io.BPlusLeafIO;
import org.apache.ignite.internal.processors.cache.persistence.tree.io.IOVersions;
import org.apache.ignite.internal.processors.cache.persistence.tree.io.PageIO;
import org.apache.ignite.internal.processors.query.h2.database.H2Tree;
import org.apache.ignite.internal.processors.query.h2.database.InlineIndexColumn;
import org.apache.ignite.internal.processors.query.h2.database.inlinecolumn.InlineIndexColumnFactory;
import org.apache.ignite.internal.processors.query.h2.opt.H2CacheRow;
import org.apache.ignite.internal.processors.query.h2.opt.H2Row;

/**
 * Leaf page for H2 row references.
 */
public abstract class AbstractH2ExtrasLeafIO extends BPlusLeafIO<H2Row> implements H2RowLinkIO {
    /** Payload size. */
    protected final int payloadSize;

    /** */
    public static void register() {
        register(false);

        register(true);
    }

    /**
     * @param mvcc Mvcc flag.
     */
    private static void register(boolean mvcc) {
        short type = mvcc ? PageIO.T_H2_EX_REF_MVCC_LEAF_START : PageIO.T_H2_EX_REF_LEAF_START;

        for (short payload = 1; payload <= PageIO.MAX_PAYLOAD_SIZE; payload++) {
            IOVersions<? extends AbstractH2ExtrasLeafIO> io =
                getVersions((short)(type + payload - 1), payload, mvcc);

            PageIO.registerH2ExtraLeaf(io, mvcc);
        }
    }

    /**
     * @param payload Payload size.
     * @param mvccEnabled Mvcc flag.
     * @return IOVersions for given payload.
     */
    public static IOVersions<? extends BPlusLeafIO<H2Row>> getVersions(int payload, boolean mvccEnabled) {
        assert payload >= 0 && payload <= PageIO.MAX_PAYLOAD_SIZE;

        if (payload == 0)
            return mvccEnabled ? H2MvccLeafIO.VERSIONS : H2LeafIO.VERSIONS;
        else
            return (IOVersions<BPlusLeafIO<H2Row>>)PageIO.getLeafVersions((short)(payload - 1), mvccEnabled);
    }

    /**
     * @param type Type.
     * @param payload Payload size.
     * @param mvcc Mvcc flag.
     * @return Versions.
     */
    private static IOVersions<? extends AbstractH2ExtrasLeafIO> getVersions(short type, short payload, boolean mvcc) {
        return new IOVersions<>(mvcc ? new H2MvccExtrasLeafIO(type, 1, payload) : new H2ExtrasLeafIO(type, 1, payload));
    }

    /**
     * @param type Page type.
     * @param ver Page format version.
     * @param itemSize Item size.
     * @param payloadSize Payload size.
     */
    AbstractH2ExtrasLeafIO(short type, int ver, int itemSize, int payloadSize) {
        super(type, ver, itemSize + payloadSize);

        this.payloadSize = payloadSize;
    }

    /** {@inheritDoc} */
    @SuppressWarnings("ForLoopReplaceableByForEach")
    @Override public final void storeByOffset(long pageAddr, int off, H2Row row) {
        assertPageType(pageAddr);

        H2CacheRow row0 = (H2CacheRow)row;

        assert row0.link() != 0;

        List<InlineIndexColumn> inlineIdxs = InlineIndexColumnFactory.getCurrentInlineIndexes();

        assert inlineIdxs != null : "no inline index helpers";

        int fieldOff = 0;

        for (int i = 0; i < inlineIdxs.size(); i++) {
            InlineIndexColumn idx = inlineIdxs.get(i);

            int size = idx.put(pageAddr, off + fieldOff, row.getValue(idx.columnIndex()), payloadSize - fieldOff);

            if (size == 0)
                break;

            fieldOff += size;
        }

        H2IOUtils.storeRow(row0, pageAddr, off + payloadSize, storeMvccInfo());
    }

    /** {@inheritDoc} */
    @Override public final void store(long dstPageAddr, int dstIdx, BPlusIO<H2Row> srcIo, long srcPageAddr, int srcIdx) {
        assertPageType(dstPageAddr);

        int srcOff = srcIo.offset(srcIdx);

        byte[] payload = PageUtils.getBytes(srcPageAddr, srcOff, payloadSize);
        long link = PageUtils.getLong(srcPageAddr, srcOff + payloadSize);

        assert link != 0;

        int dstOff = offset(dstIdx);

        PageUtils.putBytes(dstPageAddr, dstOff, payload);

        H2IOUtils.store(dstPageAddr, dstOff + payloadSize, srcIo, srcPageAddr, srcIdx, storeMvccInfo());
    }

    /** {@inheritDoc} */
    @Override public H2Row getLookupRow(BPlusTree<H2Row, ?> tree, long pageAddr, int idx)
        throws IgniteCheckedException {
        long link = getLink(pageAddr, idx);

        if (storeMvccInfo()) {
            long mvccCrdVer = getMvccCoordinatorVersion(pageAddr, idx);
            long mvccCntr = getMvccCounter(pageAddr, idx);
            int mvccOpCntr = getMvccOperationCounter(pageAddr, idx);

            return ((H2Tree)tree).createMvccRow(link, mvccCrdVer, mvccCntr, mvccOpCntr);
        }

        return ((H2Tree)tree).createRow(link);
    }

    /** {@inheritDoc} */
    @Override public final long getLink(long pageAddr, int idx) {
        return PageUtils.getLong(pageAddr, offset(idx) + payloadSize);
    }

    /** {@inheritDoc} */
    @Override public int getPayloadSize() {
        return payloadSize;
    }
}
