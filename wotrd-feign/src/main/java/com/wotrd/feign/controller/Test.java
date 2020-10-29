package com.wotrd.feign.controller;

import lombok.extern.slf4j.Slf4j;
import org.omg.SendingContext.RunTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BinaryOperator;

/**
 * @ClassName: Test
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/10/28 19:36
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
//        String a = null;
//        String s = Optional.ofNullable(a).orElse("");
//        System.out.println(String.format("s = %s", s));
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        int sum = list.stream().mapToInt(Integer::intValue).sum();
        System.out.println("sum=" + sum);
        Long num = null;
        assert num == null;
        System.out.println("test asset");

//        AtomicReference<Integer> reference = new AtomicReference<>();
//        boolean b = reference.compareAndSet(1, 3);
//        Integer integer = reference.get();
//        boolean b1 = reference.compareAndSet(1, 5);
//        Integer andAccumulate = reference.getAndAccumulate(1, new BinaryOperator<Integer>() {
//            @Override
//            public Integer apply(Integer integer, Integer integer2) {
//                return integer+integer2;
//            }
//        });

        log.error("hello, {}", "exception", new RuntimeException() {
        });
        Optional.ofNullable(null).ifPresent(e -> {
        });


    }
}
