package cn.myservice;

import cn.mysuper.service.IMyConnection;

/**
 * 处理连接字符串
 * */
public class MyConnectionService {
    private IMyConnection myConnection;

    private static class InstanceHolder {
        public static MyConnectionService instance = new MyConnectionService();
    }

    /**
     * 获取单例模式
     * */
    public static MyConnectionService getInstance() {
        return MyConnectionService.InstanceHolder.instance;
    }

    /**
     * 构造函数设置为私有，只能通过 getInstance() 方法获取
     * */
    private MyConnectionService() {
        try {
            Class<?> cls = Class.forName("org.gridgain.myservice.MyConnectionImpl");
            myConnection = (IMyConnection) cls.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public IMyConnection getMyConnection() {
        return myConnection;
    }
}
