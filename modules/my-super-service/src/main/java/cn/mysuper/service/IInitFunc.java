package cn.mysuper.service;

/**
 * 初始化自定义方法的接口
 * */
public interface IInitFunc {

    /**
     * 初始化自定义的方法
     * */
    public void initFunc();

    public void initSchemaFunc(final Object ignite, final String schemaName);

    public void dropSchemaFunc(final Object ignite, final String schemaName);
}
