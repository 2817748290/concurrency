package com.zhoulin.concurrency.immutable;

import com.google.common.collect.Maps;
import com.zhoulin.concurrency.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 *  fina 修饰变量
 * （1）final修饰基本数据类型变量的时候，一旦在初始化之后就不能被修改。
 * （2）引用类型的变量，在初始化之后不能指向另一个对象
 */
@Slf4j
@NotThreadSafe
public class finalTest {

    private final static int a = 1;
    private final static String b = "2";

    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(2,3);
        map.put(3,4);
    }

    public static void main(String[] args) {

//        a = 2;
//        b = "3";
//
//        map = new HashMap<>();
        map.put(1,7);
        log.info("{}", map.get(1));
    }

}
