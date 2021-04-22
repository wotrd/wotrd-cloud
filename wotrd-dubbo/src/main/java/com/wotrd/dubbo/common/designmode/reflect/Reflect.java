package com.wotrd.dubbo.common.designmode.reflect;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/4/22 23:22
 */
public class Reflect {

    public static Factory getInstance(String name) {
        try {
            return (Factory) Class.forName(name).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
