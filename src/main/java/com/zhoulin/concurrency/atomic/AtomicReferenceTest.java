package com.zhoulin.concurrency.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class AtomicReferenceTest {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {

        count.compareAndSet(0,2);
        count.compareAndSet(0,1);
        count.compareAndSet(1,4);
        count.compareAndSet(2,5);

        log.info("count : " + count.get());
    }


}
