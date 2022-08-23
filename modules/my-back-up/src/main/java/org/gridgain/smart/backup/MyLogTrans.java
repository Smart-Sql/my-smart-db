package org.gridgain.smart.backup;

import org.gridgain.internal.h2.mvstore.MVStore;
import org.gridgain.internal.h2.mvstore.tx.Transaction;
import org.gridgain.internal.h2.mvstore.tx.TransactionMap;
import org.gridgain.internal.h2.mvstore.tx.TransactionStore;

public class MyLogTrans {
    private Integer index = 0;
    private Transaction transaction;
    private TransactionMap<Integer, byte[]> transactionMap;

    public MyLogTrans(final MVStore mvStore, final String tranSession)
    {
        TransactionStore transactionStore = new TransactionStore(mvStore);
        transactionStore.init();
        this.transaction = transactionStore.begin();
        this.transactionMap = transaction.openMap(tranSession);
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public TransactionMap<Integer, byte[]> getTransactionMap() {
        return transactionMap;
    }

    public void setTransactionMap(TransactionMap<Integer, byte[]> transactionMap) {
        this.transactionMap = transactionMap;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
