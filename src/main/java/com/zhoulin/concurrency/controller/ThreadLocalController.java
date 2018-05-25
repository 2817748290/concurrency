package com.zhoulin.concurrency.controller;

import com.zhoulin.concurrency.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.TimeUnit;

@Controller
@Slf4j
//@RequestMapping("/threadLocal")
public class ThreadLocalController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/threadLocal/say")
    @ResponseBody
    public String say(){
        String key = "say";
        String say = "hello !";
        ValueOperations<String, String> operations = redisTemplate.opsForValue();

        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            log.info("从缓存中获取了信息 >> " + operations.get(key));
            return "" + operations.get(key);
        }
        operations.set(key, say, 12, TimeUnit.HOURS);
        return "" + say;
    }

    @RequestMapping(value = "/local/talk")
    @ResponseBody
    public String talk(){

        return "" + RequestHolder.getId();

    }

}
