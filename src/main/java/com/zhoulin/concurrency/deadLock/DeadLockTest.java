package com.zhoulin.concurrency.deadLock;

import lombok.extern.slf4j.Slf4j;

/**
 * 死锁模拟
 */
@Slf4j
public class DeadLockTest implements Runnable{

    private int flag;

    private static Object o1 = new Object(), o2 = new Object();

    @Override
    public void run() {
        log.info("flag : {}", flag);
        if (flag == 1){
            synchronized (o1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    log.info("1");
                }
            }
        }

        if (flag == 0){
            synchronized (o2){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    log.info("2");
                }
            }
        }
    }

    public static void main(String[] args) {

        DeadLockTest lock1 = new DeadLockTest();
        DeadLockTest lock2 = new DeadLockTest();

        lock1.flag = 1;
        lock2.flag = 0;

        new Thread(lock1).start();
        new Thread(lock2).start();
    }
}
