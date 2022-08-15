package org.gridgain.myservice;

import cn.mysuper.service.IInitFunc;
import org.apache.ignite.*;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.internal.GridKernalContext;
import org.apache.ignite.internal.IgniteEx;
import org.apache.ignite.internal.processors.query.h2.ConnectionManager;
import org.apache.ignite.internal.processors.query.h2.IgniteH2Indexing;
import org.gridgain.nosql.MyNoSqlUtil;
import org.gridgain.plus.SmartFunc;
import org.gridgain.plus.init.PlusInit;
import org.tools.MyInitCache;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class MyInitFuncImpl implements IInitFunc {

    @Override
    public void initFunc() {
        Ignite ignite = Ignition.ignite();

        MyInitCache myInitCache = new MyInitCache();
        myInitCache.InitCache(ignite);

        PlusInit plusInit = new PlusInit(ignite);
        plusInit.initialization();

        GridKernalContext ctx = ((IgniteEx)ignite).context();
        IgniteH2Indexing h2Indexing = (IgniteH2Indexing)ctx.query().getIndexing();
        ConnectionManager connMgr = h2Indexing.connections();
        System.out.println("自定义方法的初始化");

        try {
            String clause = "CREATE ALIAS IF NOT EXISTS auto_id FOR \"org.tools.MyPlusFunc.auto_id\"";
            connMgr.executeStatement("PUBLIC", clause);
            System.out.println("自定义方法：auto_id 初始化成功！");

            clause = "CREATE ALIAS IF NOT EXISTS my_fun FOR \"org.tools.MyPlusFunc.myFun\"";
            connMgr.executeStatement("PUBLIC", clause);
            System.out.println("自定义方法：my_fun 初始化成功！");

            clause = "CREATE ALIAS IF NOT EXISTS my_invoke FOR \"org.tools.MyPlusFunc.myInvoke\"";
            connMgr.executeStatement("PUBLIC", clause);
            System.out.println("自定义方法：my_invoke 初始化成功！");

            clause = "CREATE ALIAS IF NOT EXISTS my_invoke_link FOR \"org.tools.MyPlusFunc.myInvokeLink\"";
            connMgr.executeStatement("PUBLIC", clause);
            System.out.println("自定义方法：my_invoke_link 初始化成功！");

//            clause = "CREATE ALIAS IF NOT EXISTS nth FOR \"org.tools.MyPlusFunc.nth\"";
//            connMgr.executeStatement("PUBLIC", clause);
//            System.out.println("自定义方法：nth 初始化成功！");
//
//            clause = "CREATE ALIAS IF NOT EXISTS first FOR \"org.tools.MyPlusFunc.first\"";
//            connMgr.executeStatement("PUBLIC", clause);
//            System.out.println("自定义方法：first 初始化成功！");

            clause = "CREATE ALIAS IF NOT EXISTS show_msg FOR \"org.tools.MyPlusFunc.showMsg\"";
            connMgr.executeStatement("PUBLIC", clause);
            System.out.println("自定义方法：show_msg 初始化成功！");

            clause = "CREATE ALIAS IF NOT EXISTS get_scheduler FOR \"org.tools.MyPlusFunc.getScheduler\"";
            connMgr.executeStatement("PUBLIC", clause);
            System.out.println("自定义方法：get_scheduler 初始化成功！");

            clause = "CREATE ALIAS IF NOT EXISTS hasConnPermission FOR \"org.tools.MyPlusFunc.hasConnPermission\"";
            connMgr.executeStatement("PUBLIC", clause);
            System.out.println("自定义方法：hasConnPermission 初始化成功！");

            clause = "CREATE ALIAS IF NOT EXISTS superSql FOR \"org.tools.MyPlusFunc.superSql\"";
            connMgr.executeStatement("PUBLIC", clause);
            System.out.println("自定义方法：superSql 初始化成功！");

//            clause = "CREATE ALIAS IF NOT EXISTS my_line_binary FOR \"org.tools.MyPlusFunc.my_line_binary\"";
//            connMgr.executeStatement("PUBLIC", clause);
//            System.out.println("自定义方法：my_line_binary 初始化成功！（这个方法是用作测试的）");

            CacheConfiguration<?, ?> template_cfg = new CacheConfiguration<>("MyMeta_template*").setSqlSchema("MY_META");
            template_cfg.setCacheMode(CacheMode.REPLICATED);
            template_cfg.setReadFromBackup(true);
            ignite.addCacheConfiguration(template_cfg);

            //CacheConfiguration<?, ?> cacheCfg = new CacheConfiguration<>("public_meta").setSqlSchema("PUBLIC");
            ignite.getOrCreateCache(new CacheConfiguration<>("public_meta").setSqlSchema("PUBLIC"));
            ignite.getOrCreateCache(new CacheConfiguration<>("my_meta_table").setSqlSchema("MY_META"));
            initSchemaFunc(ignite, "MY_META");

            //IgniteCache<MyCachePK, MyCaches> my_caches = ignite.cache("my_caches");
            MyNoSqlUtil.initCaches(ignite);
            // 加载定时任务
            SmartFunc.initJob(ignite);
        } catch (IgniteCheckedException var5) {
            var5.printStackTrace();
        }

    }

    @Override
    public void initSchemaFunc(Object ignite, String schemaName) {
        GridKernalContext ctx = ((IgniteEx)ignite).context();
        IgniteH2Indexing h2Indexing = (IgniteH2Indexing)ctx.query().getIndexing();
        ConnectionManager connMgr = h2Indexing.connections();
        System.out.println(schemaName + ": 自定义方法的初始化");

        schemaName = schemaName.toUpperCase();

        try {
            String clause = "CREATE ALIAS IF NOT EXISTS auto_id FOR \"org.tools.MyPlusFunc.auto_id\"";
            connMgr.executeStatement(schemaName, clause);
            System.out.println(schemaName + ": 自定义方法：auto_id 初始化成功！");

            clause = "CREATE ALIAS IF NOT EXISTS my_fun FOR \"org.tools.MyPlusFunc.myFun\"";
            connMgr.executeStatement(schemaName, clause);
            System.out.println(schemaName + ": 自定义方法：my_fun 初始化成功！");

            clause = "CREATE ALIAS IF NOT EXISTS my_invoke FOR \"org.tools.MyPlusFunc.myInvoke\"";
            connMgr.executeStatement(schemaName, clause);
            System.out.println(schemaName + ": 自定义方法：my_invoke 初始化成功！");

            clause = "CREATE ALIAS IF NOT EXISTS my_invoke_link FOR \"org.tools.MyPlusFunc.myInvokeLink\"";
            connMgr.executeStatement(schemaName, clause);
            System.out.println(schemaName + "：my_invoke_link 初始化成功！");

//            clause = "CREATE ALIAS IF NOT EXISTS nth FOR \"org.tools.MyPlusFunc.nth\"";
//            connMgr.executeStatement(schemaName, clause);
//            System.out.println(schemaName + ": 自定义方法：nth 初始化成功！");
//
//            clause = "CREATE ALIAS IF NOT EXISTS first FOR \"org.tools.MyPlusFunc.first\"";
//            connMgr.executeStatement(schemaName, clause);
//            System.out.println(schemaName + ": 自定义方法：first 初始化成功！");

            clause = "CREATE ALIAS IF NOT EXISTS show_msg FOR \"org.tools.MyPlusFunc.showMsg\"";
            connMgr.executeStatement(schemaName, clause);
            System.out.println(schemaName + ": 自定义方法：show_msg 初始化成功！");

            clause = "CREATE ALIAS IF NOT EXISTS get_scheduler FOR \"org.tools.MyPlusFunc.getScheduler\"";
            connMgr.executeStatement(schemaName, clause);
            System.out.println(schemaName + ": 自定义方法：get_scheduler 初始化成功！");

            clause = "CREATE ALIAS IF NOT EXISTS hasConnPermission FOR \"org.tools.MyPlusFunc.hasConnPermission\"";
            connMgr.executeStatement(schemaName, clause);
            System.out.println(schemaName + ": 自定义方法：hasConnPermission 初始化成功！");

            clause = "CREATE ALIAS IF NOT EXISTS superSql FOR \"org.tools.MyPlusFunc.superSql\"";
            connMgr.executeStatement(schemaName, clause);
            System.out.println(schemaName + ": 自定义方法：superSql 初始化成功！");

//            clause = "CREATE ALIAS IF NOT EXISTS my_line_binary FOR \"org.tools.MyPlusFunc.my_line_binary\"";
//            connMgr.executeStatement(schemaName, clause);
//            System.out.println(schemaName + ": 自定义方法：my_line_binary 初始化成功！（这个方法是用作测试的）");
        } catch (IgniteCheckedException var5) {
            var5.printStackTrace();
        }
    }

    @Override
    public void dropSchemaFunc(Object ignite, String schemaName) {
        GridKernalContext ctx = ((IgniteEx)ignite).context();
        IgniteH2Indexing h2Indexing = (IgniteH2Indexing)ctx.query().getIndexing();
        ConnectionManager connMgr = h2Indexing.connections();

        schemaName = schemaName.toUpperCase();

        try {
            String clause = "DROP ALIAS IF EXISTS auto_id";
            connMgr.executeStatement(schemaName, clause);
            System.out.println(schemaName + ": 自定义方法：auto_id 删除成功！");
            clause = "DROP ALIAS IF EXISTS my_fun";
            connMgr.executeStatement(schemaName, clause);
            System.out.println(schemaName + ": 自定义方法：my_fun 删除成功！");
            clause = "DROP ALIAS IF EXISTS my_invoke";
            connMgr.executeStatement(schemaName, clause);
            System.out.println(schemaName + ": 自定义方法：my_invoke 删除成功！");

            clause = "DROP ALIAS IF EXISTS my_invoke_link";
            connMgr.executeStatement(schemaName, clause);
            System.out.println(schemaName + ": 自定义方法：my_invoke_link 删除成功！");

//            clause = "DROP ALIAS IF EXISTS nth FOR \"org.tools.MyPlusFunc.nth\"";
//            connMgr.executeStatement(schemaName, clause);
//            System.out.println("自定义方法：nth 初始化成功！");
//            clause = "DROP ALIAS IF EXISTS first FOR \"org.tools.MyPlusFunc.first\"";
//            connMgr.executeStatement(schemaName, clause);
//            System.out.println("自定义方法：first 初始化成功！");
            clause = "DROP ALIAS IF EXISTS show_msg";
            connMgr.executeStatement(schemaName, clause);
            System.out.println(schemaName + ": 自定义方法：show_msg 删除成功！");
            clause = "DROP ALIAS IF EXISTS get_scheduler";
            connMgr.executeStatement(schemaName, clause);
            System.out.println(schemaName + ": 自定义方法：get_scheduler 删除成功！");
            clause = "DROP ALIAS IF EXISTS hasConnPermission";
            connMgr.executeStatement(schemaName, clause);
            System.out.println(schemaName + ": 自定义方法：hasConnPermission 删除成功！");
            clause = "DROP ALIAS IF EXISTS superSql";
            connMgr.executeStatement(schemaName, clause);
            System.out.println(schemaName + ": 自定义方法：superSql 删除成功！");
//            clause = "DROP TABLE IF EXISTS my_line_binary FOR \"org.tools.MyPlusFunc.my_line_binary\"";
//            connMgr.executeStatement(schemaName, clause);
//            System.out.println("自定义方法：my_line_binary 初始化成功！（这个方法是用作测试的）");
        } catch (IgniteCheckedException var5) {
            var5.printStackTrace();
        }
    }

//    protected IgniteLogger log;
//    //private Logger LOG = Logger.getLogger(JdbcThinConnection.class.getName());
//
//    @Override
//    public void initFunc() {
//
//        GridKernalContext ctx = ((IgniteEx) Ignition.ignite()).context();
//        log = ctx.log(getClass());
//
//        IgniteH2Indexing h2Indexing = (IgniteH2Indexing)ctx.query().getIndexing();
//        ConnectionManager connMgr = h2Indexing.connections();
//
//        // 自定义方法的初始化
//        //ConnectionManager connMgr = ((IgniteH2Indexing)((IgniteEx) Ignition.ignite()).context().query().getIndexing()).connections();
//        try {
//            String clause = "CREATE ALIAS IF NOT EXISTS auto_id FOR \"org.tools.MyPlusFunc.auto_id\"";
//            connMgr.executeStatement("PUBLIC", clause);
//            log.info("自定义方法：auto_id 初始化成功！");
//
//            clause = "CREATE ALIAS IF NOT EXISTS my_fun FOR \"org.tools.MyPlusFunc.myFun\"";
//            connMgr.executeStatement("PUBLIC", clause);
//            log.info("自定义方法：my_fun 初始化成功！");
//
//            clause = "CREATE ALIAS IF NOT EXISTS my_invoke FOR \"org.tools.MyPlusFunc.myInvoke\"";
//            connMgr.executeStatement("PUBLIC", clause);
//            log.info("自定义方法：my_invoke 初始化成功！");
//
//            clause = "CREATE ALIAS IF NOT EXISTS nth FOR \"org.tools.MyPlusFunc.nth\"";
//            connMgr.executeStatement("PUBLIC", clause);
//            log.info("自定义方法：nth 初始化成功！");
//
//            clause = "CREATE ALIAS IF NOT EXISTS first FOR \"org.tools.MyPlusFunc.first\"";
//            connMgr.executeStatement("PUBLIC", clause);
//            log.info("自定义方法：first 初始化成功！");
//
//            clause = "CREATE ALIAS IF NOT EXISTS show_msg FOR \"org.tools.MyPlusFunc.showMsg\"";
//            connMgr.executeStatement("PUBLIC", clause);
//            log.info("自定义方法：show_msg 初始化成功！");
//
//            clause = "CREATE ALIAS IF NOT EXISTS get_scheduler FOR \"org.tools.MyPlusFunc.getScheduler\"";
//            connMgr.executeStatement("PUBLIC", clause);
//            log.info("自定义方法：get_scheduler 初始化成功！");
//        } catch (IgniteCheckedException e) {
//            e.printStackTrace();
//        }
//    }
}
