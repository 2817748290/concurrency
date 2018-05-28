package com.zhoulin.concurrency.singleton;

import com.zhoulin.concurrency.annotation.NotRecommend;
import com.zhoulin.concurrency.annotation.NotThreadSafe;
import com.zhoulin.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 懒汉模式
 * 双重检测机制DCL double checked locking
 */
@Slf4j
@NotThreadSafe
public class SingletonTest3 {

    // 单例对象
    private static SingletonTest3 instance = null;

    // 1. memory = allocate() 分配对象的内存空间
    // 2. ctorInstance 初始化对象
    // 3. instance = memory 设置instance指向分配的内存空间

    // JVM和CPU优化，发生了指令重排

    // 1.
    // 3.
    // 2.

    /* 双重检测机制
    如果instance不为空的话，不用执行下面的加锁和初始化操作，减少了性能的开销
    但是在多线程的情况也存在问题 线程B会调用到未初始化完毕的对象
     */

    //静态的工厂方法
    public static SingletonTest3 getInstance() {
        // 双重检测机制
        if (instance == null){ // 线程B执行
            //同步锁
            synchronized(SingletonTest3.class){
                log.info("first create");
                instance = new SingletonTest3(); // 线程A执行 3.
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        log.info("instance {} ", getInstance());
    }
}
