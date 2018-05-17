package com.zhoulin.concurrency.mq;

import com.zhoulin.concurrency.mq.kafka.KafkaReceiver;
import com.zhoulin.concurrency.mq.kafka.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MQController {

    @Autowired
    private KafkaSender kafkaSender;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public String send(@RequestParam(value = "msg") String msg){

        kafkaSender.send(msg);

        KafkaReceiver kafkaReceiver = new KafkaReceiver();
        return "success";
    }

}
