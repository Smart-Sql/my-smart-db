package org.gridgain.myservice;

import cn.mysuper.model.MyUrlToken;
import cn.mysuper.service.IMyConnection;
import org.gridgain.plus.dml.MyLexical;

/**
 * 数据库连接处理
 * */
public class MyConnectionImpl implements IMyConnection {
    @Override
    public MyUrlToken getToken(String s) {
        return MyLexical.my_url_tokens(s);
    }

    @Override
    public Boolean isJdbcThin(String url) {
        return MyLexical.isJdbcThin(url);
    }

    @Override
    public Boolean hasConnPermission(String url) {
        return MyLexical.hasConnPermission(url);
    }
}
