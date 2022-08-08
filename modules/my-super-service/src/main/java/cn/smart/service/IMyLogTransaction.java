package cn.smart.service;

/**
 * 用于两地三中心，这种高可用场景。
 * 用户自己实现高可用的程序。这个只是一个接口而已
 * 用户继承这个接口后，在配置文件中设置好自己实现的 class 全称。
 * 在执行增删改的过程中，先执行增删改，在执行用户自己实现这个接口的方法，如果返回 false 就回滚
 * */
public interface IMyLogTransaction {

    public void begin();

    public Boolean saveTo(final byte[] data);

    public void commit();

    public void rollback();
}
