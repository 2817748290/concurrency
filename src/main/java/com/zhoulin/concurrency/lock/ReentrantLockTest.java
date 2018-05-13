package com.zhoulin.concurrency.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 可重入锁 JDK实现
 * 公平锁还是非公平锁
 * 最好在finally中声明释放锁
 */
public class ReentrantLockTest {

    private final static Logger logger  = LoggerFactory.getLogger(ReentrantLockTest.class);

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    private static int count = 0;

    private final static Lock reentrantLock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < clientTotal; i++){
            executorService.execute(()-> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    logger.error("exception", e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        logger.info("count : {}", count);
    }

    private static void add(){
        // 上锁
        reentrantLock.lock();
        try{
            count ++;
        } finally {
            // 解锁
            reentrantLock.unlock();
        }
    }

}
