package com.zhoulin.concurrency.syncContainer;

import com.google.common.collect.Lists;
import com.zhoulin.concurrency.annotation.NotThreadSafe;
import com.zhoulin.concurrency.annotation.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Collections.synchronizedList() 线程安全
 * 同步容器
 */
@ThreadSafe
public class CollectionsTest {

    private final static Logger logger  = LoggerFactory.getLogger(CollectionsTest.class);

    private static List<Integer> list = Collections.synchronizedList(Lists.newArrayList());

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static void main(String[] args) throws InterruptedException {

        // 初始化一个可缓存线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 限制资源的线程数量
        final Semaphore semaphore = new Semaphore(threadTotal);

        // CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。每当一个线程完成了自己的任务后，计数器的值就会减1
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++){
            final int count = i;
            executorService.execute(() ->{
                try {
                    // 申请许可
                    semaphore.acquire();
                    // 执行操作
                    add(count);
                    // 释放许可
                    semaphore.release();
                } catch (InterruptedException e) {
                    logger.error("exception", e);
                }
                // 刷新总线程数 clientTotal - 1
                countDownLatch.countDown();
            });
        }

        // 使任务处于阻塞等待的状态 直到countDownLatch减到0为止
        countDownLatch.await();
        executorService.shutdown();
        logger.info("{}", list.size());
    }

    public static void add(int i){
        list.add(i);
    }

}
