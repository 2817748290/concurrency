package com.zhoulin.concurrency.atomic;

import com.zhoulin.concurrency.annotation.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Atomic源码实现的时候使用的一个 unsafe 的类
 *
 * * AtomicInteger.java
 * public final int incrementAndGet() {
 *         return unsafe.getAndAddInt(this, valueOffset, 1) + 1;
 *     }
 *
 * * Unsafe.java
 * public final int getAndAddInt(Object var1, long var2, int var4) {
 *         int var5;
 *         do {
 *             var5 = this.getIntVolatile(var1, var2);
 *         } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
 *                  this.compareAndSwapInt(count, valueOffset, var5, var5 + 1)
 *         return var5;
 *     }
 *
 * *由于Java方法无法直接访问底层系统，需要通过本地（native）方法来访问
 * public final native boolean compareAndSwapInt(Object var1, long var2, int var4, int var5);
 * var1: 要修改的对象 count AtomicInteger对象
 * var2: 偏移量 valueOffset count属性在内存中的地址
 * var4: 预期的原值 工作内存（共享内存副本）中的count值
 * var5: 修改以后的值
 * 比对从底层传过来的值 与 工作内存中的值 是否匹配进行运算
 *
 * compareAndSwapXXX
 * CAS
 */
@ThreadSafe
public class AtomicLongTest {
    private final static Logger logger  = LoggerFactory.getLogger(AtomicLongTest.class);

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static AtomicLong count = new AtomicLong(0);

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
        countDownLatch.await();
        executorService.shutdown();
        logger.info("size - {}", count.toString());
    }

    public static void add(){
        count.incrementAndGet();
    }
}

