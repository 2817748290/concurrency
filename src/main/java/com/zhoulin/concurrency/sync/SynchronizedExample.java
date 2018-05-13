package com.zhoulin.concurrency.sync;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Synchronized JVM实现
 * 非公平锁
 * 由于是JVM实现的不会造成死锁
 */
@Slf4j
public class SynchronizedExample {

    private static final Logger logger = LoggerFactory.getLogger(SynchronizedExample.class);

    public synchronized void test1(int j){

        for (int i=0;i<10;i++){
            logger.info("test1 {} - {}", j, i);
        }

    }

    public void test2(int j){
        synchronized (SynchronizedExample.class){
            for (int i=0;i<10;i++){
                logger.info("test1 {} - {}", j, i);
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedExample synchronizedExample1 = new SynchronizedExample();
        SynchronizedExample synchronizedExample2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() ->{
            synchronizedExample1.test2(1);
        });
        executorService.execute(() ->{
            synchronizedExample2.test2(2);
        });

    }

}
