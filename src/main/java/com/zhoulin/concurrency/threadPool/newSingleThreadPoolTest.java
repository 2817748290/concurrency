package com.zhoulin.concurrency.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class newSingleThreadPoolTest {

    private final static ReentrantLock lock = new ReentrantLock();

    private static int clientTotal = 200;

    private static int threadNum = 10;

    private static LongAdder count = new LongAdder();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newSingleThreadExecutor(new MyThreadFactory("single"));
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        final Semaphore semaphore = new Semaphore(threadNum);

        for (int i = 0; i < clientTotal; i++){
            final int j = i;
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    log.info("Thread : {}, task : {}", Thread.currentThread(), j);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count {}", count);
    }

    public static void add(){
        try{
            lock.lock();
            count.increment();
        } finally {
            lock.unlock();
        }
    }
}
