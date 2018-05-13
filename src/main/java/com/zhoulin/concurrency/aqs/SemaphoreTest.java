package com.zhoulin.concurrency.aqs;

import com.zhoulin.concurrency.annotation.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 信号量 限制获取某种资源的线程数量
 * 管理一系列许可证
 */
public class SemaphoreTest {

    private final static Logger logger  = LoggerFactory.getLogger(SemaphoreTest.class);

    // 请求总数
    public static int clientTotal = 20;

    public static int threadNum = 5;

    public static void main(String[] args) throws InterruptedException {

        // 初始化一个可缓存线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadNum);

        for (int i = 0; i < clientTotal; i++){
            final int count = i;
            executorService.execute(() ->{
                try {
                    semaphore.acquire();
                    add(count);
                    semaphore.release();
                } catch (Exception e) {
                    logger.error("exception", e);
                }
            });
        }

        executorService.shutdown();

    }

    public static void add(int i) throws InterruptedException {
        Thread.sleep(1000);
        logger.info("{}", i);
        Thread.sleep(100);
    }

}
