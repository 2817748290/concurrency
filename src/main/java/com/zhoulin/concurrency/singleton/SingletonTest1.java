package com.zhoulin.concurrency.singleton;

import com.zhoulin.concurrency.annotation.NotThreadSafe;
import com.zhoulin.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 饿汉模式
 * 单例实例在类装载时进行创建
 * 如果构造方法中的存在过多的处理 类加载速度慢 性能低（私有构造方法不存在过多的处理）
 * 如果类如果只是装载不去调用 会造成资源的浪费
 *
 */
@Slf4j
@ThreadSafe
public class SingletonTest1 {

    // 私有构造方法
    public SingletonTest1() {
    }

    // 单例对象
    private static SingletonTest1 instance = new SingletonTest1();

    //静态的工厂方法
    public static SingletonTest1 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        log.info("instance {} ", getInstance());
    }
}
