package org.apache.ignite.smart.service;


import cn.smart.service.IMyLogTransaction;
import com.google.common.base.Strings;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;

public class MyLogService {
    private IMyLogTransaction myLog;

    private static class InstanceHolder {
        public static MyLogService instance;

        static {
            try {
                instance = new MyLogService();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取单例模式
     * */
    public static MyLogService getInstance() {
        return MyLogService.InstanceHolder.instance;
    }

    /**
     * 构造函数设置为私有，只能通过 getInstance() 方法获取
     * */
    private MyLogService() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Ignite ignite = Ignition.ignite();
        String myLogCls = ignite.configuration().getMyLogCls();
        if (!Strings.isNullOrEmpty(myLogCls)) {
            Class<?> cls = Class.forName(myLogCls);
            myLog = (IMyLogTransaction) cls.newInstance();
        }
    }

    public IMyLogTransaction getMyLog() {
        return myLog;
    }
}
