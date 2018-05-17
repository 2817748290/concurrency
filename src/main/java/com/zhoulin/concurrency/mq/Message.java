package com.zhoulin.concurrency.mq;

import lombok.Data;

import java.util.Date;

@Data
public class Message {

    private long id;

    private String msg;

    private Date sendTime;

}
