package cn.myservice.jdbc;


import cn.mysuper.jdbc.service.ICallFunc;

public class CallFuncService {
    private ICallFunc callFunc;

    public static CallFuncService getInstance() {
        return CallFuncService.InstanceHolder.instance;
    }

    private CallFuncService() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> cls = Class.forName("org.gridgain.myservice.jdbc.CallFuncImpl");
        this.callFunc = (ICallFunc)cls.newInstance();
    }

    public ICallFunc getCallFunc() {
        return this.callFunc;
    }

    private static class InstanceHolder {
        public static CallFuncService instance;

        private InstanceHolder() {
        }

        static {
            try {
                instance = new CallFuncService();
            } catch (ClassNotFoundException var1) {
                var1.printStackTrace();
            } catch (IllegalAccessException var2) {
                var2.printStackTrace();
            } catch (InstantiationException var3) {
                var3.printStackTrace();
            }

        }
    }
}

