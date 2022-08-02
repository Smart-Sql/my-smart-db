package cn.myservice;

import cn.mysuper.service.IMySqlAst;

public class MySqlAstService {
    private IMySqlAst mySqlAst;

    private static class InstanceHolder {
        public static MySqlAstService instance;

        static {
            try {
                instance = new MySqlAstService();
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
    public static MySqlAstService getInstance() {
        return MySqlAstService.InstanceHolder.instance;
    }

    /**
     * 构造函数设置为私有，只能通过 getInstance() 方法获取
     * */
    private MySqlAstService() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> cls = Class.forName("org.gridgain.myservice.MySqlAstImpl");
        mySqlAst = (IMySqlAst) cls.newInstance();
    }

    public IMySqlAst getMySqlAst() {
        return mySqlAst;
    }

}
