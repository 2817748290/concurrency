package com.zhoulin.concurrency.publish;

import com.zhoulin.concurrency.annotation.NotThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 对象发布
 * state域中的值会被其他线程通过 unsafePublish.getState()[0] 直接修改掉
 * 导致其他线程无法得到准确的值
 */
@Slf4j
@NotThreadSafe
public class UnsafePublish {

    @Getter
    private String[] state = {"a", "b", "c"};

    public static void main(String[] args) {

        UnsafePublish unsafePublish = new UnsafePublish();

        log.info("state {}", Arrays.toString(unsafePublish.getState()));

        unsafePublish.getState()[0] = "d";
        log.info("state {}", Arrays.toString(unsafePublish.getState()));

    }

}
