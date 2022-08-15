package org.gridgain.myservice;

import cn.smart.service.IMyLoadCron;
import org.apache.ignite.Ignition;
import org.gridgain.plus.SmartFunc;

public class MyLoadCronImpl implements IMyLoadCron {
    @Override
    public void loadCronFromDb() {
        SmartFunc.initJob(Ignition.ignite());
    }
}
