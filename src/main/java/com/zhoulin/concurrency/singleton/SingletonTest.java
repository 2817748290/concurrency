package com.zhoulin.concurrency.singleton;

import com.zhoulin.concurrency.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 *
 *   if (instance == null){
 *             log.info("first create");
 *             instance = new SingletonTest();
 *         }
 *         return instance;
 * 如果两个线程同时执行到这一段代码 会同时生成两个对象
 */
@Slf4j
@NotThreadSafe
public class SingletonTest {

    // 单例对象
    private static SingletonTest instance = null;

    //静态的工厂方法
    public static synchronized SingletonTest getInstance() {
        if (instance == null){
            log.info("first create");
            instance = new SingletonTest();
        }
        return instance;
    }

    public static void main(String[] args) {
        log.info("instance {} ", getInstance());
    }
}
