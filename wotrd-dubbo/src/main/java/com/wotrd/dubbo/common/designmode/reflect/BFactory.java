package com.wotrd.dubbo.common.designmode.reflect;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/4/22 23:32
 */
public class BFactory implements Factory{
    @Override
    public void create() {
        System.out.println("BFactory");
    }
}
