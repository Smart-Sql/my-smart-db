package org.gridgain.smart.recovery;

import org.gridgain.internal.h2.mvstore.MVStore;
import org.gridgain.internal.h2.mvstore.tx.Transaction;
import org.gridgain.internal.h2.mvstore.tx.TransactionMap;
import org.gridgain.internal.h2.mvstore.tx.TransactionStore;

import java.sql.*;
import java.util.List;

/**
 * 从文件中将数据恢复到集群中
 * */
public class MyRecovery {

    private MVStore mvStore;
    private String tranSession;
    private String jdbcUrl;

    /**
     * 生成不可变对象
     * */
    public MyRecovery(final MVStore mvStore, final String tranSession, final String jdbcUrl)
    {
        this.mvStore = mvStore;
        this.tranSession = tranSession;
        this.jdbcUrl = jdbcUrl;
    }

    /**
     * 恢复到集群
     * */
    public void recoveryToCluster() throws SQLException, ClassNotFoundException {
        TransactionStore transactionStore = new TransactionStore(mvStore);
        transactionStore.init();

        Transaction transaction = transactionStore.begin();
        TransactionMap<Integer, byte[]> transactionMap = transaction.openMap(this.tranSession);

        for (Integer key : transactionMap.keySet())
        {
            byte[] vs = transactionMap.get(key);
            toCluster(vs);
        }
    }

    /**
     * 通过 JDBC 将 数据写到集群
     * */
    private void toCluster(final byte[] vs) throws ClassNotFoundException, SQLException {

        Class.forName("org.apache.ignite.IgniteJdbcDriver");
        Connection conn = DriverManager.getConnection(this.jdbcUrl);
        PreparedStatement stmt = conn.prepareStatement("recovery_to_cluster(?)");
        stmt.setBytes(1, vs);
        stmt.executeQuery();
        stmt.close();
        conn.close();
    }
}





















































