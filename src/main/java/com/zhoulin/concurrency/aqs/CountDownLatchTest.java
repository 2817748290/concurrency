package com.zhoulin.concurrency.aqs;

import com.zhoulin.concurrency.annotation.ThreadSafe;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * CountDownLatch 充当计数器
 * 保证原子性
 */
public class CountDownLatchTest {

    private final static Logger logger  = LoggerFactory.getLogger(CountDownLatchTest.class);

    // 请求总数
    public static int clientTotal = 200;

    public static void main(String[] args) throws InterruptedException {

        // 初始化一个可缓存线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        // CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。每当一个线程完成了自己的任务后，计数器的值就会减1
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++){
            final int count = i;
            executorService.execute(() ->{
                try {
                    add(count);
                } catch (Exception e) {
                    logger.error("exception", e);
                } finally {
                    // 刷新总线程数 clientTotal - 1
                    countDownLatch.countDown();
                }
            });
        }

        // 使任务处于阻塞等待的状态 直到countDownLatch减到0为止
        countDownLatch.await();
//        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        logger.info("finish");
        executorService.shutdown();

    }

    public static void add(int i) throws InterruptedException {
        Thread.sleep(100);
        logger.info("{}", i);
        Thread.sleep(100);
    }

}
