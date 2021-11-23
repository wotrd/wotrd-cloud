package com.wotrd.dubbo.vm;

import com.sleepycat.collections.StoredKeySet;

import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/11/18 00:01
 */
public class RuntimeConstantPoolTest {

    /**
     * vm args -XX:PermSize=6M -XX:MaxPermSize=6M
     * @param args
     */
    public static void main(String[] args) {
//        Set<String> stringSet = new HashSet<>();
//        int i=0;
//        while (true){
//            stringSet.add(String.valueOf(i++).intern());
//        }
        String str = new StringBuilder().append("hello").append("world").toString();
        System.out.println(str.intern() == str);
        String str1 = new StringBuilder().append("crgagag").append("cr").append("cr").toString();
        System.out.println(str1.intern() == str1);
    }
}
