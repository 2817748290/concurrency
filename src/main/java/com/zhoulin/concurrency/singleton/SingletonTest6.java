package com.zhoulin.concurrency.singleton;

import com.zhoulin.concurrency.annotation.Recommend;
import com.zhoulin.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 枚举模式：最安全的单例模式
 * 枚举单例有序列化和线程安全的保证（默认线程是安全的）
 */
@Slf4j
@ThreadSafe
@Recommend
public class SingletonTest6 {

    // 私有构造方法
    public SingletonTest6() {
    }

    public static SingletonTest6 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {

        INSTANCE;

        private SingletonTest6 singleton;

        // JVM保证这个方法绝对只调用一次
        Singleton(){
            singleton = new SingletonTest6();
        }

        public SingletonTest6 getInstance() {
            return singleton;
        }

    }

    public static void main(String[] args) {
        log.info("instance {} ", getInstance().hashCode());
    }
}
