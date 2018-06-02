package com.zhoulin.concurrency.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时调度
 */
@Slf4j
public class ScheduledThreadPoolTest {

    public static void main(String[] args) {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        // 延迟3s执行
        executorService.schedule(new Runnable(){
            @Override
            public void run() {
                log.warn("schedule run !");
                }
            },3,TimeUnit.SECONDS);

        // 延迟1s执行 每隔3s执行
        executorService.scheduleAtFixedRate(new Runnable(){
            @Override
            public void run() {
                log.warn("schedule run !");
            }
        }, 1, 3, TimeUnit.SECONDS);

        // 定时器
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn(" timer run !");
            }
        }, new Date(), 5 * 1000);

    }

}
