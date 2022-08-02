package cn.myservice;

import cn.mysuper.service.IInitFunc;

/**
 * 初始化表
 * */
public class MyInitFuncService {

    private IInitFunc initFunc;

    private static class InstanceHolder {
        public static MyInitFuncService instance;

        static {
            try {
                instance = new MyInitFuncService();
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
    public static MyInitFuncService getInstance() {
        return MyInitFuncService.InstanceHolder.instance;
    }

    /**
     * 构造函数设置为私有，只能通过 getInstance() 方法获取
     * */
    private MyInitFuncService() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> cls = Class.forName("org.gridgain.myservice.MyInitFuncImpl");
        initFunc = (IInitFunc) cls.newInstance();
    }

    public IInitFunc getInitFunc() {
        return initFunc;
    }
}
