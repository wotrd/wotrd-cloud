package com.wotrd.feign.controller;


import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName: Test
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/10/28 19:36
 */
@Slf4j
public class Test {
    public static void main(String[] args) {



    }

    private static String format() {
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        int sum = list.stream().mapToInt(Integer::intValue).sum();
        Set<String> set = new HashSet<>();
//        set.add("tom");
//        set.add("bom");
//        set.add("校长");
//        set.add("校长");
        return StringUtils.collectionToDelimitedString(set, "、");
    }

    private static void jsonTest() {
        List<Test1> list = new ArrayList<>();
        Test1 test1 = new Test1();
        test1.setMap(new HashMap<>());
        List<Long> objects = Lists.newArrayList();
        objects.add(112L);
        test1.setIds(objects);
        list.add(test1);

        String s = JSONObject.toJSONString(list);
        System.out.println("s=" + s);
        List<com.wotrd.feign.controller.Test.Test1> list1 = JSONObject.parseArray(s, Test1.class);
        System.out.println("list1=" + list1);
    }


    @Data
    static class Test1 {
        Map<String, List> map;

        List<Long> ids;
    }

    private static void jsonFormatMap() {
        Test1 test1 = new Test1();
        Map<String, List> map = new HashMap<>();
        map.put("123", new ArrayList());
        test1.setMap(map);
        System.out.println(JSONObject.toJSON(test1));

    }

    private static void atomicTest() {
        AtomicInteger atomicInteger = new AtomicInteger(10);
        int i = atomicInteger.incrementAndGet();
        int andIncrement = atomicInteger.getAndIncrement();

//        AtomicReference<Integer> reference = new AtomicReference<>();
//        boolean b = reference.compareAndSet(1, 3);
//        Integer integer = reference.get();
//        boolean b1 = reference.compareAndSet(1, 5);
//        Integer andAccumulate = reference.getAndAccumulate(1, new BinaryOperator<Integer>() {
//            @Override
//            public Integer apply(Integer integer, Integer integer2) {
//                return integer + integer2;
//            }
//        });
    }
}
