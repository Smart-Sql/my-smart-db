package org.gridgain.myservice.jdbc;

import cn.mysuper.jdbc.service.ICallFunc;
import org.gridgain.jdbc.MyJdbc;

public class CallFuncImpl implements ICallFunc {
    public CallFuncImpl() {
    }

    public Boolean hasConnPermission(String userToken) {
        return MyJdbc.hasConnPermission(userToken);
    }
}

