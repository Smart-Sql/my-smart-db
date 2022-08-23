package org.gridgain.smart.backup;


import org.gridgain.internal.h2.mvstore.MVStore;
import org.gridgain.internal.h2.mvstore.tx.Transaction;
import org.gridgain.internal.h2.mvstore.tx.TransactionMap;
import org.gridgain.internal.h2.mvstore.tx.TransactionStore;

import java.util.concurrent.ConcurrentHashMap;

public class MyLogStore {
    private MVStore mvStore;
    private ConcurrentHashMap<String, MyLogTrans> hashMap;

    private static class InstanceHolder {
        public static MyLogStore instance = new MyLogStore();
    }

    public static MyLogStore getInstance() {
        return MyLogStore.InstanceHolder.instance;
    }

    public void setMvStore(final String path) {
        mvStore = MVStore.open(path);
        hashMap = new ConcurrentHashMap<String, MyLogTrans>();
    }

    private boolean saveTranSession(String tranSession)
    {
        boolean flag = true;
        TransactionStore transactionStore = new TransactionStore(mvStore);
        transactionStore.init();

        Transaction transaction = transactionStore.begin();
        TransactionMap<String, Object> transactionMap = transaction.openMap("my_tran_session");

        try
        {
            transactionMap.put(tranSession, new Object());
            transaction.commit();
        }
        catch (Exception e)
        {
            transaction.rollback();
            flag = false;
        }

        return flag;
    }

    public void createSession(String tranSession) {
        this.hashMap.put(tranSession, new MyLogTrans(mvStore, tranSession));
    }

    public void saveTo(String tranSession, byte[] bytes) {
        if (this.hashMap.size() > 0)
        {
            MyLogTrans m = this.hashMap.get(tranSession);
            m.getTransactionMap().put(m.getIndex() + 1, bytes);
            m.setIndex(m.getIndex() + 1);
        }
    }

    public void commit(String tranSession) {
        if (this.hashMap.size() > 0)
        {
            MyLogTrans m = this.hashMap.get(tranSession);
            m.getTransaction().commit();
            this.hashMap.remove(tranSession);
        }
    }

    public void rollback(String tranSession) {
        if (this.hashMap.size() > 0)
        {
            if (this.hashMap.containsKey(tranSession)) {
                MyLogTrans m = this.hashMap.get(tranSession);
                if (m.getTransaction().getStatus() != 0) {
                    m.getTransaction().rollback();
                    this.hashMap.remove(tranSession);
                }
                else
                {
                    removeDate(tranSession);
                }
            }
            else
            {
                removeDate(tranSession);
            }
        }
        else
        {
            removeDate(tranSession);
        }
    }

    private void removeDate(String tranSession)
    {
        MyLogTrans m = new MyLogTrans(mvStore, tranSession);
        try {
            m.getTransactionMap().remove(tranSession);
            m.getTransaction().commit();
        }
        catch (Exception e)
        {
            m.getTransaction().rollback();
        }
    }
}
