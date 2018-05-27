package com.zhoulin.concurrency.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;


/**
 * AtomicIntegerFieldUpdater 用于更新某个类指定属性
 */
@Slf4j
public class AtomicIntegerFieldUpdaterTest {

    private static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterTest> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterTest.class, "count");

    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {

        AtomicIntegerFieldUpdaterTest test = new AtomicIntegerFieldUpdaterTest();

        if (atomicIntegerFieldUpdater.compareAndSet(test, 100, 120)){
            log.info("update success {}", test.getCount());
        }

        if (atomicIntegerFieldUpdater.compareAndSet(test, 100, 120)){
            log.info("update success {}", test.getCount());
        }else {
            log.info("update failed {}", test.getCount());
        }

    }

}
