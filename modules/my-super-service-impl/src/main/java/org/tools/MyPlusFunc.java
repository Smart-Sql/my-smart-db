package org.tools;

import clojure.lang.RT;
import cn.myservice.MyPlusFuncService;
import cn.mysuper.service.IMyPlusFunc;
import org.apache.ignite.Ignition;
import org.gridgain.dml.util.MyCacheExUtil;
import org.gridgain.myservice.MyPlusFuncImpl;
import org.gridgain.plus.dml.MySmartSql;

import java.io.Serializable;
import java.util.List;

public class MyPlusFunc implements Serializable {

    private static final long serialVersionUID = 175563786053447344L;
    private static IMyPlusFunc myPlusFunc = new MyPlusFuncImpl();

    /**
     * 获取 table 的自增长
     * */
    public static Object auto_id(final String tableName) {
        return Ignition.ignite().atomicSequence(tableName, 0, true).incrementAndGet();
    }

    /**
     * 获取序列的第 n 个
     * */
    public static Object nth(final Object coll, final int n) {
        return RT.nth(coll, n);
    }

    /**
     * 获取序列的第一个
     * */
    public static Object first(final Object lst) {
        return RT.first(lst);
    }

    /**
     * 显示信息
     * */
    public static String showMsg(final String msg)
    {
        return msg;
    }

    /**
     * 获取定时任务详细信息
     * */
    public static String getScheduler(final String schedulerName)
    {
        return myPlusFunc.getScheduler(schedulerName);
    }

    /**
     * 自定义函数的要求
     * 1、必须有一个默认构造函数
     * 2、方法名不能重载
     * */
    public static String myFun(final String methodName, final String... ps) {
        return MyConvertUtil.ConvertToString(myPlusFunc.myFun(methodName, ps));
    }

    /**
     * 场景调用
     * */
    public static String myInvoke(final String methodName, final String group_id, final String... ps)
    {
        return MyConvertUtil.ConvertToString(myPlusFunc.myInvoke(methodName, group_id, ps));
    }

    /**
     * 联接函数的调用
     * */
    public static String myInvokeLink(final String methodName, final String group_id, final String... ps)
    {
        return MyConvertUtil.ConvertToString(myPlusFunc.myInvokeLink(methodName, group_id, ps));
    }

    /**
     * 联接函数的调用
     * */
    public static String myInvokeAllFuncScenes(final String methodName, final String group_id, final String... ps)
    {
        return MyConvertUtil.ConvertToString(myPlusFunc.myInvokeAllFuncScenes(methodName, group_id, ps));
    }

    public static String smartSql(byte[] userToken, byte[] sql) {
        //Object m_obj = MyCacheExUtil.restore(sql);
        //System.out.println(m_obj);
//        Object m_obj = MyCacheExUtil.restore(sql);
//        if (m_obj instanceof List) {
//            List rs = MySmartSql.reSmartSegmentLst((List) m_obj);
//            for (Object r : rs) {
//                System.out.println(r);
//            }
//        }
        return myPlusFunc.superSql(userToken, sql);
    }

    public static String my_line_binary(byte[] bytes) {
        return (String)MyLineToBinary.restore(bytes);
    }

    public static Boolean hasConnPermission(String userToken) {
        return myPlusFunc.hasConnPermission(userToken);
    }
}
