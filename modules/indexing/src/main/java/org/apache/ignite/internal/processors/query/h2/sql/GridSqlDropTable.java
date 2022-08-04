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

package org.apache.ignite.internal.processors.query.h2.sql;

/**
 * DROP TABLE statement.
 */
public class GridSqlDropTable extends GridSqlStatement {
    /** Schema name. */
    private String schemaName;

    /** Table name. */
    private String tblName;

    /** Quietly ignore this command if table does not exist. */
    private boolean ifExists;

    /**
     * @return Schema name.
     */
    public String schemaName() {
        return schemaName;
    }

    /**
     * @param schemaName Schema name.
     */
    public void schemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    /**
     * @return Table name.
     */
    public String tableName() {
        return tblName;
    }

    /**
     * @param tblName Table name.
     */
    public void tableName(String tblName) {
        this.tblName = tblName;
    }

    /**
     * @return Quietly ignore this command if table does not exist.
     */
    public boolean ifExists() {
        return ifExists;
    }

    /**
     * @param ifExists Quietly ignore this command if table does not exist.
     */
    public void ifExists(boolean ifExists) {
        this.ifExists = ifExists;
    }

    /** {@inheritDoc} */
    @Override public String getSQL() {
        return null;
    }
}