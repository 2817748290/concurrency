package com.zhoulin.concurrency.syncContainer;

public abstract class AbstractTest {

    public static int a ;

    static {
       a = 1;
    }
    public static void add(){
        System.out.println("add()..." + a);
    }



}
