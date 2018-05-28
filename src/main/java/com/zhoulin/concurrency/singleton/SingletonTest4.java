package com.zhoulin.concurrency.singleton;

import com.zhoulin.concurrency.annotation.NotThreadSafe;
import com.zhoulin.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 懒汉模式
 * 双重检测机制 + volatile -> 防止指令重排
 */
@Slf4j
@ThreadSafe
public class SingletonTest4 {

    // 单例对象
    private volatile static SingletonTest4 instance = null;

    // 1. memory = allocate() 分配对象的内存空间
    // 2. ctorInstance 初始化对象
    // 3. instance = memory 设置instance指向分配的内存空间

    // JVM和CPU优化，发生了指令重排

    // 1.
    // 3.
    // 2.

    /* 双重检测机制 + volatile
    如果instance不为空的话，不用执行下面的加锁和初始化操作，减少了性能的开销
    volatile 可以防止指令重排
     */

    //静态的工厂方法
    public static SingletonTest4 getInstance() {
        // 双重检测机制
        if (instance == null){ // 线程B执行
            //同步锁
            synchronized(SingletonTest4.class){
                log.info("first create");
                instance = new SingletonTest4(); // 线程A执行 3.
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        log.info("instance {} ", getInstance());
    }
}
