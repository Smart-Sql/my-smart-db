package org.smart.service;

import org.gridgain.plus.dml.MySmartScenes;

public class MySmartScenesService {

    private MySmartScenes mySmartScenes;

    private static class InstanceHolder {
        public static MySmartScenesService instance;

        static {
            try {
                instance = new MySmartScenesService();
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
    public static MySmartScenesService getInstance() {
        return MySmartScenesService.InstanceHolder.instance;
    }

    /**
     * 构造函数设置为私有，只能通过 getInstance() 方法获取
     * */
    private MySmartScenesService() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        mySmartScenes = new MySmartScenes();
    }

    public MySmartScenes getMySmartScenes() {
        return mySmartScenes;
    }
}
