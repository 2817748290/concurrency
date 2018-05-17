package com.zhoulin.concurrency.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduledThreadPoolTest {

    public static void main(String[] args) {

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        // 定时器
        executorService.scheduleAtFixedRate(new Runnable(){
            @Override
            public void run() {
                log.warn("schedule run !");
            }
        }, 1, 3, TimeUnit.SECONDS);


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn(" timer run !");
            }
        }, new Date(), 5 * 1000);

    }

}
