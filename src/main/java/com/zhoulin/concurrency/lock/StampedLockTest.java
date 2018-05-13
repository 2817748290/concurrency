package com.zhoulin.concurrency.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock 读写锁的改进
 * StampedLock就可以实现一种无障碍操作，即读写之间不会阻塞对方，但是写和写之间还是阻塞的
 */
public class StampedLockTest {

    private final static Logger logger  = LoggerFactory.getLogger(StampedLockTest.class);

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    private static int count = 0;

    private final static StampedLock stampedLock = new StampedLock();

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
        long stamp = stampedLock.writeLock();
        try{
            count ++;
        } finally {
            // 解锁
            stampedLock.unlock(stamp);
        }
    }

}
