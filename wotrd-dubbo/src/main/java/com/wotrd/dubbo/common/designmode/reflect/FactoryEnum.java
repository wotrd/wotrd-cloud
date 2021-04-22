package com.wotrd.dubbo.common.designmode.reflect;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/4/22 23:40
 */
public enum FactoryEnum {

    A_FACTORY_ENUM("A", new AFactory()),
    B_FACTORY_ENUM("B", new BFactory());

    private String name;

    private Factory factory;

    FactoryEnum(String name, Factory factory) {
        this.name = name;
        this.factory = factory;
    }

    public static Factory valueByName(String name) {
        for (FactoryEnum factoryEnum : FactoryEnum.values()) {
            if (factoryEnum.name.endsWith(name)) {
                return factoryEnum.factory;
            }
        }
        return null;
    }
}
