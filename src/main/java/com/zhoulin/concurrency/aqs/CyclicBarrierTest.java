package com.zhoulin.concurrency.aqs;

import com.zhoulin.concurrency.annotation.ThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * CyclicBarrier 同步屏障
 * CyclicBarrier(int parties)，其参数表示屏障拦截的线程数量
 * 让一组线程到达一个屏障（也可以叫同步点）时被阻塞，直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活
 */
public class CyclicBarrierTest {

    private final static Logger logger  = LoggerFactory.getLogger(CyclicBarrierTest.class);

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {

        // 初始化一个可缓存线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++){
            Thread.sleep(1000);
            final int count = i;
            executorService.execute(() ->{
                try {
                    add(count);
                } catch (Exception e) {
                    logger.error("exception", e);
                }
            });
        }

        executorService.shutdown();

    }

    public static void add(int i) throws InterruptedException, BrokenBarrierException {
        Thread.sleep(1000);
        logger.info("{} is ready !", i);
        cyclicBarrier.await();
        logger.info("{} is continue !", i);
    }

}
