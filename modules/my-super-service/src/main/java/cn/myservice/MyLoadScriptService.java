package cn.myservice;

import cn.smart.service.IMyLoadScript;

public class MyLoadScriptService {

    private IMyLoadScript myLoadScript;

    private static class InstanceHolder {
        public static MyLoadScriptService instance;

        static {
            try {
                instance = new MyLoadScriptService();
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
    public static MyLoadScriptService getInstance() {
        return MyLoadScriptService.InstanceHolder.instance;
    }

    /**
     * 构造函数设置为私有，只能通过 getInstance() 方法获取
     * */
    private MyLoadScriptService() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> cls = Class.forName("cn.smart.service.impl.MyLoadScriptImpl");
        myLoadScript = (IMyLoadScript) cls.newInstance();
    }

    public IMyLoadScript getMyLoadScript() {
        return myLoadScript;
    }
}
