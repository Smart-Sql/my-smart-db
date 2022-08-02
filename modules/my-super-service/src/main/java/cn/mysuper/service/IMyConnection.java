package cn.mysuper.service;

import cn.mysuper.model.MyUrlToken;

/**
 * 修改 jdbc 连接
 * */
public interface IMyConnection {

    public MyUrlToken getToken(String url);

    public Boolean isJdbcThin(String var1);

    public Boolean hasConnPermission(String var1);
}
