package com.zhoulin.concurrency.singleton;

import com.zhoulin.concurrency.annotation.NotRecommend;
import com.zhoulin.concurrency.annotation.NotThreadSafe;
import com.zhoulin.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 懒汉模式
 * 加入synchronized实现线程安全
 * 但是会降低性能
 */
@Slf4j
@ThreadSafe
@NotRecommend
public class SingletonTest2 {

    // 单例对象
    private static SingletonTest2 instance = null;

    //静态的工厂方法
    public static synchronized SingletonTest2 getInstance() {
        if (instance == null){
            log.info("first create");
            instance = new SingletonTest2();
        }
        return instance;
    }

    public static void main(String[] args) {
        log.info("instance {} ", getInstance());
    }
}
