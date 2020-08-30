package com.wotrd.dubbo.common.designmode.singleinstance;

/**
 * 饿汉式模式
 */
public class SingleInstance {
    private static SingleInstance instance=new SingleInstance();
    private SingleInstance(){}
    public static SingleInstance getInstance(){
        return instance;
    }

}
