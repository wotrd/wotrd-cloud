package com.wotrd.dubbo.common.retry.queue;

import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.collections.StoredSortedMap;
import com.sleepycat.collections.TransactionRunner;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.Transaction;

import java.io.Serializable;
import java.util.AbstractQueue;
import java.util.Iterator;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/5/13 19:32
 */
public class BdbQueue<E extends Serializable> extends AbstractQueue<E> {
    private BdbEnvironment bdbEnvironment;
    private Database queueDb;
    private StoredSortedMap<Long, E> queueMap;
    private TransactionRunner transactionRunner;
    private String queueName = "";
    private final Object syncObj = new Object();
    private static final String MESSAGE_STORE = "message_store";

    BdbQueue(BdbEnvironment environment, String dbName) {
        DatabaseConfig dbConfig = new DatabaseConfig();
        dbConfig.setTransactional(true);
        dbConfig.setAllowCreate(true);
        this.bdbEnvironment = environment;
        this.queueName = dbName;
        this.queueDb = this.bdbEnvironment.getEnvironment().openDatabase((Transaction) null, "message_store" + this.getQueueName(), dbConfig);
        EntryBinding<Long> messageKeyBinding = new SerialBinding<>(this.bdbEnvironment.getJavaCatalog(), Long.class);
        EntryBinding<Object> messageValueBinding = new SerialBinding<>(this.bdbEnvironment.getJavaCatalog(), Object.class);
        this.queueMap = new StoredSortedMap(this.queueDb, messageKeyBinding, messageValueBinding, true);
        this.transactionRunner = new TransactionRunner(this.bdbEnvironment.getEnvironment());
    }

    public void close() {
        if (this.queueDb != null) {
            this.queueDb.close();
        }

    }

    public Iterator<E> iterator() {
        return this.queueMap.values().iterator();
    }

    public int size() {
        return this.queueMap.size();
    }

    public boolean offer(E e) {
        if (null == e) {
            throw new IllegalArgumentException("原始不允许为空");
        } else {
            synchronized (this.syncObj) {
                Long lastKey = this.queueMap.lastKey();
                lastKey = lastKey == null ? 1L : lastKey + 1L;
                this.queueMap.put(lastKey, e);
                return true;
            }
        }
    }

    public E poll() {
        synchronized (this.syncObj) {
            Long firstKey;
            return (firstKey = this.queueMap.firstKey()) != null && this.queueMap.get(firstKey) != null ? this.queueMap.remove(firstKey) : null;
        }
    }

    public E peek() {
        synchronized (this.syncObj) {
            Long firstKey;
            E val;
            return (firstKey = this.queueMap.firstKey()) != null && (val = this.queueMap.get(firstKey)) != null ? val : null;
        }
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public Database getQueueDb() {
        return this.queueDb;
    }

    public StoredSortedMap<Long, E> getQueueMap() {
        return this.queueMap;
    }

    public String getQueueName() {
        return this.queueName;
    }

    public BdbEnvironment getBdbEnvironment() {
        return this.bdbEnvironment;
    }

    public TransactionRunner getTransactionRunner() {
        return this.transactionRunner;
    }
}
