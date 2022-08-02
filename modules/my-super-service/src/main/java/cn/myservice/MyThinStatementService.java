package cn.myservice;

import cn.mysuper.service.IThinStatement;

public class MyThinStatementService {

    private IThinStatement thinStatement;

    private static class InstanceHolder {

        public static MyThinStatementService instance;

        static {
            try {
                instance = new MyThinStatementService();
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
    public static MyThinStatementService getInstance() {
        return MyThinStatementService.InstanceHolder.instance;
    }

    /**
     * 构造函数设置为私有，只能通过 getInstance() 方法获取
     * */
    private MyThinStatementService() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> cls = Class.forName("org.gridgain.myservice.MyThinStatementImpl");
        thinStatement = (IThinStatement) cls.newInstance();
    }

    public IThinStatement getThinStatement() {
        return thinStatement;
    }
}







































