package com.wotrd.dubbo.common.designmode;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/4/18 20:22
 */
public class Test {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        String a = map.put("a", "a");
        String b = map.put("a", "b");
        System.out.println("test map");
        test(d -> System.out.println("hahah"));
    }


    public static void test(Consumer consumer) {
        consumer.accept(consumer);


    }
}
