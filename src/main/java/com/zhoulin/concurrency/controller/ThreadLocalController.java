package com.zhoulin.concurrency.controller;

import com.zhoulin.concurrency.threadLocal.RequestHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping("/threadLocal")
public class ThreadLocalController {

    @RequestMapping(value = "/threadLocal/say")
    @ResponseBody
    public String say(){

        return "" + RequestHolder.getId();

    }

    @RequestMapping(value = "/local/talk")
    @ResponseBody
    public String talk(){

        return "" + RequestHolder.getId();

    }

}
