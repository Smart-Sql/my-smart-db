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

package org.apache.ignite.internal.pagemem.wal.record.delta;

import org.apache.ignite.IgniteCheckedException;
import org.apache.ignite.internal.pagemem.PageMemory;
import org.apache.ignite.internal.processors.cache.persistence.tree.io.PageIO;
import org.apache.ignite.internal.processors.cache.persistence.tree.io.PageMetaIO;
import org.apache.ignite.internal.util.typedef.internal.S;

/**
 *
 */
public class MetaPageUpdateLastAllocatedIndex extends PageDeltaRecord {
    /** */
    private final int lastAllocatedIdx;

    /**
     * @param pageId Meta page ID.
     */
    public MetaPageUpdateLastAllocatedIndex(int grpId, long pageId, int lastAllocatedIdx) {
        super(grpId, pageId);

        this.lastAllocatedIdx = lastAllocatedIdx;
    }

    /** {@inheritDoc} */
    @Override public void applyDelta(PageMemory pageMem, long pageAddr) throws IgniteCheckedException {
        int type = PageIO.getType(pageAddr);

        assert type == PageIO.T_META || type == PageIO.T_PART_META;

        PageMetaIO io = PageIO.getPageIO(type, PageIO.getVersion(pageAddr));

        io.setLastAllocatedPageCount(pageAddr, lastAllocatedIdx);
    }

    /** {@inheritDoc} */
    @Override public RecordType type() {
        return RecordType.META_PAGE_UPDATE_LAST_ALLOCATED_INDEX;
    }

    /**
     * @return Root ID.
     */
    public int lastAllocatedIndex() {
        return lastAllocatedIdx;
    }

    /** {@inheritDoc} */
    @Override public String toString() {
        return S.toString(MetaPageUpdateLastAllocatedIndex.class, this, "super", super.toString());
    }
}
