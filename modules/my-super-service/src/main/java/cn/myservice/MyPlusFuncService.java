package cn.myservice;

import cn.mysuper.service.IMyPlusFunc;

/**
 * 实例化 IMyPlusFunc 接口
 * */
public class MyPlusFuncService {

    private IMyPlusFunc iMyPlusFunc;

    private static class InstanceHolder {
        public static MyPlusFuncService instance;

        static {
            try {
                instance = new MyPlusFuncService();
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
    public static MyPlusFuncService getInstance() {
        return MyPlusFuncService.InstanceHolder.instance;
    }

    /**
     * 构造函数设置为私有，只能通过 getInstance() 方法获取
     * */
    private MyPlusFuncService() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> cls = Class.forName("org.gridgain.myservice.MyPlusFuncImpl");
        iMyPlusFunc = (IMyPlusFunc) cls.newInstance();
    }

    public IMyPlusFunc getiMyPlusFunc() {
        return iMyPlusFunc;
    }
}









































