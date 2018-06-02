package com.zhoulin.concurrency.immutable;

import com.google.common.collect.Maps;
import com.zhoulin.concurrency.annotation.NotThreadSafe;
import com.zhoulin.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 *
 */
@Slf4j
@ThreadSafe
public class CollectionsTest {

    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(2,3);
        map.put(3,4);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {

        map.put(1,7);
        log.info("{}", map.get(1));
    }

}
