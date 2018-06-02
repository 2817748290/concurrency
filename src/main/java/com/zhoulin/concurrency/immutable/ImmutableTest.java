package com.zhoulin.concurrency.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.zhoulin.concurrency.annotation.ThreadSafe;
import groovy.transform.Immutable;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Slf4j
@ThreadSafe
public class ImmutableTest {

    private final static List<Integer> intList = new ArrayList<>();

    static {
        intList.add(1);
        intList.add(2);
        intList.add(3);
    }

    private final static ImmutableList list = ImmutableList.copyOf(intList);

    private final static ImmutableSet set = ImmutableSet.copyOf(intList);

    private final static ImmutableMap<Integer, Integer> map = ImmutableMap.<Integer,Integer>builder().put(1,2).build();

    public static void main(String[] args) {

        list.add(4);
        set.add(4);
        map.put(1,7);
        log.info("list {}", list.get(0));
        log.info("map {}", map.get(1));
    }

}
