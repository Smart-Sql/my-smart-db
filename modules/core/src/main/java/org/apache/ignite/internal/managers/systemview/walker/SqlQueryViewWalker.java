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

package org.apache.ignite.internal.managers.systemview.walker;

import java.util.Date;
import java.util.UUID;
import org.apache.ignite.spi.systemview.view.SqlQueryView;
import org.apache.ignite.spi.systemview.view.SystemViewRowAttributeWalker;

/**
 * Generated by {@code org.apache.ignite.codegen.SystemViewRowAttributeWalkerGenerator}.
 * {@link SqlQueryView} attributes walker.
 * 
 * @see SqlQueryView
 */
public class SqlQueryViewWalker implements SystemViewRowAttributeWalker<SqlQueryView> {
    /** {@inheritDoc} */
    @Override public void visitAll(AttributeVisitor v) {
        v.accept(0, "queryId", String.class);
        v.accept(1, "sql", String.class);
        v.accept(2, "originNodeId", UUID.class);
        v.accept(3, "startTime", Date.class);
        v.accept(4, "duration", long.class);
        v.accept(5, "diskAllocationCurrent", long.class);
        v.accept(6, "diskAllocationMax", long.class);
        v.accept(7, "diskAllocationTotal", long.class);
        v.accept(8, "initiatorId", String.class);
        v.accept(9, "local", boolean.class);
        v.accept(10, "memoryCurrent", long.class);
        v.accept(11, "memoryMax", long.class);
        v.accept(12, "schemaName", String.class);
        v.accept(13, "distributedJoins", boolean.class);
        v.accept(14, "enforceJoinOrder", boolean.class);
        v.accept(15, "lazy", boolean.class);
    }

    /** {@inheritDoc} */
    @Override public void visitAll(SqlQueryView row, AttributeWithValueVisitor v) {
        v.accept(0, "queryId", String.class, row.queryId());
        v.accept(1, "sql", String.class, row.sql());
        v.accept(2, "originNodeId", UUID.class, row.originNodeId());
        v.accept(3, "startTime", Date.class, row.startTime());
        v.acceptLong(4, "duration", row.duration());
        v.acceptLong(5, "diskAllocationCurrent", row.diskAllocationCurrent());
        v.acceptLong(6, "diskAllocationMax", row.diskAllocationMax());
        v.acceptLong(7, "diskAllocationTotal", row.diskAllocationTotal());
        v.accept(8, "initiatorId", String.class, row.initiatorId());
        v.acceptBoolean(9, "local", row.local());
        v.acceptLong(10, "memoryCurrent", row.memoryCurrent());
        v.acceptLong(11, "memoryMax", row.memoryMax());
        v.accept(12, "schemaName", String.class, row.schemaName());
        v.acceptBoolean(13, "distributedJoins", row.distributedJoins());
        v.acceptBoolean(14, "enforceJoinOrder", row.enforceJoinOrder());
        v.acceptBoolean(15, "lazy", row.lazy());
    }

    /** {@inheritDoc} */
    @Override public int count() {
        return 16;
    }
}