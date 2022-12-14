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

package org.apache.ignite.internal.mxbean;

import org.apache.ignite.internal.GridKernalContext;
import org.apache.ignite.internal.processors.query.h2.IgniteH2Indexing;

/**
 * QueryMXBean implementation.
 */
public class SqlQueryMXBeanImpl implements SqlQueryMXBean {
    /** */
    private final IgniteH2Indexing h2idx;

    /**
     * @param ctx Context.
     */
    public SqlQueryMXBeanImpl(GridKernalContext ctx) {
        h2idx = (IgniteH2Indexing)ctx.query().getIndexing();
    }

    /** {@inheritDoc} */
    @Override public long getLongQueryWarningTimeout() {
        return h2idx.longRunningQueries().getTimeout();
    }

    /** {@inheritDoc} */
    @Override public void setLongQueryWarningTimeout(long longQryWarningTimeout) {
        h2idx.longRunningQueries().setTimeout(longQryWarningTimeout);
    }

    /** {@inheritDoc} */
    @Override public int getLongQueryTimeoutMultiplier() {
        return h2idx.longRunningQueries().getTimeoutMultiplier();
    }

    /** {@inheritDoc} */
    @Override public void setLongQueryTimeoutMultiplier(int longQryTimeoutMultiplier) {
        h2idx.longRunningQueries().setTimeoutMultiplier(longQryTimeoutMultiplier);
    }

    /** {@inheritDoc} */
    @Override public long getResultSetSizeThreshold() {
        return h2idx.longRunningQueries().getResultSetSizeThreshold();
    }

    /** {@inheritDoc} */
    @Override public void setResultSetSizeThreshold(long rsSizeThreshold) {
        h2idx.longRunningQueries().setResultSetSizeThreshold(rsSizeThreshold);
    }

    /** {@inheritDoc} */
    @Override public int getResultSetSizeThresholdMultiplier() {
        return h2idx.longRunningQueries().getResultSetSizeThresholdMultiplier();
    }

    /** {@inheritDoc} */
    @Override public void setResultSetSizeThresholdMultiplier(int rsSizeThresholdMultiplier) {
        h2idx.longRunningQueries().setResultSetSizeThresholdMultiplier(rsSizeThresholdMultiplier);
    }

    /** {@inheritDoc} */
    @Override public Long getSqlGlobalMemoryQuotaBytes() {
        return h2idx.memoryManager().getGlobalQuota();
    }

    /** {@inheritDoc} */
    @Override public String getSqlGlobalMemoryQuota() {
        return h2idx.memoryManager().getGlobalQuotaInOriginalNotation();
    }

    /** {@inheritDoc} */
    @Override public void setSqlGlobalMemoryQuota(String size) {
        h2idx.memoryManager().setGlobalQuota(size);
    }

    /** {@inheritDoc} */
    @Override public long getSqlFreeMemoryBytes() {
        return h2idx.memoryManager().freeMemory();
    }

    /** {@inheritDoc} */
    @Override public Long getSqlQueryMemoryQuotaBytes() {
        return h2idx.memoryManager().getQueryQuota();
    }


    /** {@inheritDoc} */
    @Override public String getSqlQueryMemoryQuota() {
        return h2idx.memoryManager().getQueryQuotaStrinInOriginalNotation();
    }

    /** {@inheritDoc} */
    @Override public void setSqlQueryMemoryQuota(String size) {
        h2idx.memoryManager().setQueryQuota(size);
    }

    /** {@inheritDoc} */
    @Override public boolean isSqlOffloadingEnabled() {
        return h2idx.memoryManager().isOffloadingEnabled();
    }

    /** {@inheritDoc} */
    @Override public void setSqlOffloadingEnabled(boolean enabled) {
        h2idx.memoryManager().setOffloadingEnabled(enabled);
    }
}
