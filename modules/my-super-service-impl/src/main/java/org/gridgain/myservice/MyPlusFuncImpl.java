package org.gridgain.myservice;

import cn.mysuper.service.IMyPlusFunc;
import java.util.ArrayList;
import java.util.List;

import org.apache.ignite.Ignition;
import org.apache.ignite.scheduler.SchedulerFuture;
import org.gridgain.plus.dml.MySmartScenes;
import org.gridgain.plus.sql.MySuperSql;
import org.gridgain.plus.sql.jdbc.SmartJdbcFunc;
import org.smart.service.MySmartScenesService;
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

//    @Override
//    public Object myFun(String methodName, String... ps) {
//        return MyPlusUtil.invokeFunc(Ignition.ignite(), methodName, ps);
//    }

    @Override
    public Object myFun(String methodName, Object... ps) {
        return MyPlusUtil.invokeFuncObj(Ignition.ignite(), methodName, ps);
    }

    @Override
    public Object myInvoke(String methodName, String group_id, Object... ps) {
        List<Object> lst = new ArrayList<>();
        for (Object m : ps)
        {
            lst.add(m);
        }
        return this.mySmartScenes.invokeScenes(Ignition.ignite(), MyGson.lineToObj(group_id), methodName, lst);
    }

    @Override
    public Object myInvokeLink(String methodName, String group_id, Object... ps) {
        List<Object> lst = new ArrayList<>();
        for (Object m : ps)
        {
            lst.add(m);
        }
        Object rs = mySmartScenes.invokeScenesLink(Ignition.ignite(), MyGson.lineToObj(group_id), methodName, lst);
        return rs;
    }

    @Override
    public Object myInvokeAllFuncScenes(String methodName, String group_id, Object... ps) {
        List<Object> lst = new ArrayList<>();
        for (Object m : ps)
        {
            lst.add(m);
        }
        Object rs = SmartJdbcFunc.invokeAllFuncScenes(Ignition.ignite(), MyGson.lineToObj(group_id), methodName, lst);
        return rs;
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




































