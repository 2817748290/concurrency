package com.zhoulin.concurrency.syncContainer;

import com.zhoulin.concurrency.bean.UserInfo;

import java.util.*;

public class ListTest extends AbstractTest{

    public static void main(String[] args) {
//        List<String> list = new ArrayList<String>(5);
////        HashSet<String> ts = new HashSet<String>();
////       ts.add("123");
////       ts.add("12");
////       ts.add("a");
////       ts.add("a");
////       System.out.println(ts);
////        List<Object> objectList = new ArrayList<>();
////
////        List<String> stringList = new ArrayList<>();
////
////        objectList = Collections.singletonList(stringList);
////
////        objectList.addAll(stringList);
//        List list = new ArrayList();
//        list.add("1");
//        list.add("2");
//        list.add(3);
//        list.forEach(str -> System.out.println(((String)str).length()));

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("周林");

        UserInfo userInfo1 = (UserInfo) userInfo.clone();
        System.out.println(userInfo.toString());
        System.out.println(userInfo1.toString());
        System.out.println( userInfo.getClass() == userInfo1.getClass());
    }

}
