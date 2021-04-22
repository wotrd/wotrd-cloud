package com.wotrd.dubbo.common.designmode.reflect;

import com.google.common.collect.Maps;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/4/22 23:37
 */
public class ReflectTest {

    @Resource
    private static Map<String, Factory> factoryMap;


    public static void main(String[] args) {
        factoryMap.get("A").create();

        Factory factory = FactoryEnum.valueByName("A");
        factory.create();
        Factory instance = Reflect.getInstance("com.wotrd.dubbo.common.designmode.reflect.AFactory");
        instance.create();
        Factory instance1 = Reflect.getInstance("com.wotrd.dubbo.common.designmode.reflect.BFactory");
        instance1.create();
    }
}
