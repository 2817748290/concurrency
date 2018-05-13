package com.zhoulin.concurrency.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition
 */
@Slf4j
public class ConditionTest {

    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            try {
                reentrantLock.lock();
                log.info("wait signal"); // 1
                condition.await();
            } catch (InterruptedException e) {
                log.error("exception", e);
            } finally {
                log.info("get signal"); // 4
                reentrantLock.unlock();
            }
        }).start();

        new Thread(() -> {
            try {
                reentrantLock.lock();
                log.info("get lock"); // 2
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                log.error("exception", e);
            } finally {
                condition.signalAll();
                log.error("send signal ~"); // 3
                reentrantLock.unlock();
            }
        }).start();



    }

}
