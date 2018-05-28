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
 * 子类无法继承带有synchronized的方法
 * 不可中断锁
 * 适合竞争不激烈（低并发）
 * 可读性好
 */
@Slf4j
public class SynchronizedExample {

    private static final Logger logger = LoggerFactory.getLogger(SynchronizedExample.class);

    // 修饰一个代码块
    public synchronized void test1(int j){

        for (int i=0;i<10;i++){
            logger.info("test {} - {}", j, i);
        }

    }

    // 修饰一个方法
    public void test2(int j){
        synchronized (SynchronizedExample.class){
            for (int i=0;i<10;i++){
                logger.info("test {} - {}", j, i);
            }
        }
    }

    // 修饰一个类
    public void test3(int j){
        synchronized (SynchronizedExample.class){
            for (int i = 0; i < 10; i++){
                log.info("test {} - {}", j, i);
            }
        }
    }

    // 修饰一个静态方法
    public static synchronized void test4(int j){
        for (int i = 0; i < 10; i++){
            log.info("test {} - {}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample synchronizedExample1 = new SynchronizedExample();
        SynchronizedExample synchronizedExample2 = new SynchronizedExample();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() ->{
            synchronizedExample1.test3(1);
        });
        executorService.execute(() ->{
            synchronizedExample2.test3(2);
        });
        executorService.shutdown();
    }

}
