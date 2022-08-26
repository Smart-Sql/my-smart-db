package org.gridgain.myservice;

import cn.mysuper.service.IMyPlusFunc;
import java.util.ArrayList;
import java.util.List;

import org.apache.ignite.Ignition;
import org.apache.ignite.scheduler.SchedulerFuture;
import org.gridgain.dml.util.MyCacheExUtil;
import org.gridgain.plus.dml.MySmartScenes;
import org.gridgain.plus.dml.MySmartSql;
import org.gridgain.plus.sql.MySuperSql;
import org.gridgain.plus.sql.jdbc.SmartJdbcFunc;
import org.smart.service.MySmartScenesService;
import org.tools.MyConvertUtil;
import org.tools.MyGson;
import org.tools.MyPlusUtil;

public class MyPlusFuncImpl implements IMyPlusFunc {

    private MySmartScenes mySmartScenes = null;

    public MyPlusFuncImpl()
    {
        mySmartScenes = MySmartScenesService.getInstance().getMySmartScenes();
    }

    @Override
    public Boolean hasConnPermission(String userToken) {
        return MySuperSql.getGroupId(Ignition.ignite(), userToken);
    }

    @Override
    public String getScheduler(String schedulerName) {
        StringBuilder sb = new StringBuilder();
        SchedulerFuture schedulerFuture = (SchedulerFuture)MyPlusUtil.getIgniteScheduleProcessor(Ignition.ignite()).getScheduledFutures().get(schedulerName);
        sb.append("任务名称：" + schedulerName);
        sb.append(" 是否在运行：" + schedulerFuture.isRunning());
        sb.append(" 开始时间：" + schedulerFuture.createTime());
        sb.append(" 结束时间：" + schedulerFuture.lastFinishTime());
        return sb.toString();
    }

    @Override
    public Object myFun(String methodName, String... ps) {
        return MyPlusUtil.invokeFunc(Ignition.ignite(), methodName, ps);
    }

//    @Override
//    public Object myFun(String methodName, Object... ps) {
//        Ignite ignite = Ignition.ignite();
//        MyFunc myFunc = (MyFunc)ignite.cache("my_func").get(methodName);
//
//        try {
//            Class<?> cls = Class.forName(myFunc.getCls_name());
//            Constructor constructor = cls.getConstructor();
//            Object myobj = constructor.newInstance();
//            Method[] methods = myobj.getClass().getMethods();
//            Method[] var9 = methods;
//            int var10 = methods.length;
//
//            for(int var11 = 0; var11 < var10; ++var11) {
//                Method method = var9[var11];
//                if (method.getName().equals(myFunc.getJava_method_name())) {
//                    Object rs = method.invoke(myobj, ps);
//                    return rs;
//                }
//            }
//        } catch (ClassNotFoundException var14) {
//            var14.printStackTrace();
//        } catch (InstantiationException var15) {
//            var15.printStackTrace();
//        } catch (InvocationTargetException var16) {
//            var16.printStackTrace();
//        } catch (NoSuchMethodException var17) {
//            var17.printStackTrace();
//        } catch (IllegalAccessException var18) {
//            var18.printStackTrace();
//        }
//
//        return null;
//    }

    @Override
    public Object myInvoke(String methodName, String group_id, String... ps) {
        List<String> lst = new ArrayList<>();
        for (String m : ps)
        {
            lst.add(m);
        }
        return this.mySmartScenes.invokeScenes(Ignition.ignite(), MyGson.lineToObj(group_id), methodName, lst);
    }

    @Override
    public Object myInvokeLink(String methodName, String group_id, String... ps) {
        List<String> lst = new ArrayList<>();
        for (String m : ps)
        {
            lst.add(m);
        }
        Object rs = mySmartScenes.invokeScenesLink(Ignition.ignite(), MyGson.lineToObj(group_id), methodName, lst);
        return MyConvertUtil.ConvertToString(rs);
    }

    @Override
    public Object myInvokeAllFuncScenes(String methodName, String group_id, String... ps) {
        List<String> lst = new ArrayList<>();
        for (String m : ps)
        {
            lst.add(m);
        }
        Object rs = SmartJdbcFunc.invokeAllFuncScenes(Ignition.ignite(), MyGson.lineToObj(group_id), methodName, lst);
        return MyConvertUtil.ConvertToString(rs);
    }

//    @Override
//    public Object myInvoke(String methodName, Object... ps) {
//        ArrayList<Object> lst = new ArrayList();
//        Object[] var4 = ps;
//        int var5 = ps.length;
//
//        for(int var6 = 0; var6 < var5; ++var6) {
//            Object m = var4[var6];
//            lst.add(m);
//        }
//
//        return MyScenes.superInvoke(Ignition.ignite(), methodName, lst);
//    }

//    @Override
//    public String superSql(final String userToken, final String sql) {
//        return MySuperSql.superSql(Ignition.ignite(), userToken, sql);
//    }

    @Override
    public String superSql(final byte[] userToken, final byte[] sql) {
//        List m_obj = (List) MyCacheExUtil.restore(sql);
//        List rs = MySmartSql.reSmartSegmentLst(m_obj);
//        for (Object r : rs)
//        {
//            System.out.println(r);
//        }
        return MySuperSql.superSql(Ignition.ignite(), userToken, sql);
    }
}




































