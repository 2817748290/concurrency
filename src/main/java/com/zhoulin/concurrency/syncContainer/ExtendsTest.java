package com.zhoulin.concurrency.syncContainer;

import java.lang.reflect.Array;

public class ExtendsTest implements InterfaceTest{

    static AbstractTest listTest = new ListTest();

    public static void main(String[] args) {
        listTest.add();
        System.out.println();
        byte[] bytes = new byte[10];
    }

}
