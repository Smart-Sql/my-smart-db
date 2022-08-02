package cn.mysuper.service;

/**
 * 执行 sql
 * */
public interface IThinStatement {

    /**
     * 获取 执行的sql
     * */
    public String getSql(String sql, String userToken);
}
