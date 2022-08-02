package org.gridgain.myservice;

import cn.mysuper.service.IThinStatement;
import org.apache.ignite.Ignition;
import org.gridgain.plus.sql.MySuperSql;

/**
 * 执行 sql
 * */
public class MyThinStatementImpl implements IThinStatement {

    /**
     * 输入 sql 和 userToken 获取 thin sql
     * */
    @Override
    public String getSql(String userToken, String sql) {
        return MySuperSql.superSql(Ignition.ignite(), userToken, sql);
    }
}
