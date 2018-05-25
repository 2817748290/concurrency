package com.zhoulin.concurrency.cache;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * cmd /c dir 是执行完dir命令后关闭命令窗口。
 * cmd /k dir 是执行完dir命令后不关闭命令窗口.
 * cmd /c start dir 会打开一个新窗口后执行dir指令，原窗口会关闭。
 * cmd /k start dir 会打开一个新窗口后执行dir指令，原窗口不会关闭。
 * redis-cli.exe -h 127.0.0.1 -p 端口
 * info replication -- 查看主从复制
 * info sentinel-- 查看哨兵情况
 *
 * 启动服务工具类
 */
public class StartRedisServer {
    private final static String redisRootPath = "D:\\Java\\tools\\redis";

    public static void main(String[] args) {
        List<String> cmds = new ArrayList<String>();
        String cmdRedis6379 = "cmd /k start start6379.cmd ";//redis-server.exe redis.conf
        String cmdRedis6380 = "cmd /k start start6380.cmd ";//redis-server.exe redis.conf
        String cmdRedis6381 = "cmd /k start start6381.cmd ";//redis-server.exe redis.conf

        cmds.add(cmdRedis6379);
        cmds.add(cmdRedis6380);
        cmds.add(cmdRedis6381);

        String cmdRedis26379 = "cmd /k start start26379.cmd";//redis-server.exe sentinel.conf --sentinel
        String cmdRedis26479 = "cmd /k start start26380.cmd";//redis-server.exe sentinel.conf --sentinel
        String cmdRedis26579 = "cmd /k start start26381.cmd";//redis-server.exe sentinel.conf --sentinel

        cmds.add(cmdRedis26379);
        cmds.add(cmdRedis26479);
        cmds.add(cmdRedis26579);

        initRedisServer(cmds);
    }

    public static void initRedisServer(List<String> cmdStr){
        if(cmdStr != null && cmdStr.size() > 0){
            for (String cmd:cmdStr
                    ) {
                try {
                    Process exec = Runtime.getRuntime().exec(cmd, null, new File(redisRootPath));
                    Thread.sleep(1*1000);
                }catch (InterruptedException e) {
                    System.out.println("线程中断异常" + e.getMessage());
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("cmd command error" + e.getMessage());
                    e.printStackTrace();
                }

            }
        }

    }
}