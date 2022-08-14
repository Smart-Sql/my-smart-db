package cn.myservice;

import cn.smart.service.IMyLoadCron;

public class MyLoadCronService {

    private IMyLoadCron myLoadCron;

    private static class InstanceHolder {
        public static MyLoadCronService instance;

        static {
            try {
                instance = new MyLoadCronService();
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
    public static MyLoadCronService getInstance() {
        return MyLoadCronService.InstanceHolder.instance;
    }

    /**
     * 构造函数设置为私有，只能通过 getInstance() 方法获取
     * */
    private MyLoadCronService() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> cls = Class.forName("org.gridgain.myservice.MyLoadCronImpl");
        myLoadCron = (IMyLoadCron) cls.newInstance();
    }

    public IMyLoadCron getMyLoadCron() {
        return myLoadCron;
    }

}
