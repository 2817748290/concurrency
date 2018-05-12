package com.zhoulin.concurrency.commonUnSafe;

import com.zhoulin.concurrency.annotation.NotThreadSafe;
import com.zhoulin.concurrency.annotation.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 线程封闭测试
 * 针对StringBuffer测试线程安全性
 * 由于StringBuffer方法中使用synchronized关键字的原因
 * 性能损耗
 */
@ThreadSafe
public class StringBufferTest {

    private final static Logger logger  = LoggerFactory.getLogger(StringBufferTest.class);

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static StringBuffer stringBuffer = new StringBuffer();

    public static void main(String[] args) throws InterruptedException {

        // 初始化一个可缓存线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        // 限制资源的线程数量
        final Semaphore semaphore = new Semaphore(threadTotal);

        // CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。每当一个线程完成了自己的任务后，计数器的值就会减1
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        for (int i = 0; i < clientTotal; i++){
            executorService.execute(() ->{
                try {
                    // 申请许可
                    semaphore.acquire();
                    // 执行操作
                    add();
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
        logger.info("size - {}", stringBuffer.length());
    }

    public static void add(){
        stringBuffer.append("1");
    }

}
