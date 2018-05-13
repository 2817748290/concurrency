package com.zhoulin.concurrency.lock;

import javax.xml.crypto.Data;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock 读写锁
 */
public class ReentrantReadWriteLockTest {

    private final Map<String, Data> map = new TreeMap<>();

    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    private final Lock readLock = reentrantReadWriteLock.readLock();

    private final Lock writeLock = reentrantReadWriteLock.writeLock();

    public Data getString(String key){
        readLock.lock();
        try{
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys(){
        writeLock.lock();
        try {
            return map.keySet();
        }finally {
            writeLock.unlock();
        }
    }

    public Data push(String key, Data value){
        writeLock.lock();
        try {
           return map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

    }

}
