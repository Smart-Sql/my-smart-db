package cn.mysuper.service;

import java.sql.ResultSet;
import java.util.List;

/**
 * 自定义方法
 * */
public interface IMyPlusFunc {

    public Boolean hasConnPermission(String var1);

    /**
     * 获取定时任务详细信息
     * */
    public String getScheduler(String schedulerName);

    /**
     * 自定义函数的要求
     * 1、必须有一个默认构造函数
     * 2、方法名不能重载
     * */
    public Object myFun(final String methodName, final Object... ps);

    /**
     * 场景调用
     * */
    //public Object myInvoke(final String methodName, final String group_id, final String... ps);
    public Object myInvoke(final String methodName, final String group_id, final Object... ps);

    /**
     * 联接方法的调用
     * */
    //public Object myInvokeLink(final String methodName, final String group_id, final String... ps);
    public Object myInvokeLink(final String methodName, final String group_id, final Object... ps);

    /**
     * 场景调用
     * */
    //public Object myInvokeAllFuncScenes(final String methodName, final String group_id, final String... ps);
    public Object myInvokeAllFuncScenes(final String methodName, final String group_id, final Object... ps);

    public String superSql(final byte[] var1, final byte[] var2);
    //public String superSql(final String userToken, final String sql);

    /**
     * 显示训练数据
     * */
    public List showTrainData(final String cacheName, final Integer item_size);

    public void train_matrix_single(final String dataset_name, final String table_name, final String value);
}
