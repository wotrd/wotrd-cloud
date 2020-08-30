package com.wotrd.dubbo.common.designmode.singleinstance;

/**
 * 懒汉式模式
 */
public class SingleInstance1 {
    private static SingleInstance1 instance;
    private SingleInstance1(){}
    public static SingleInstance1 getInstance(){
        if (null == instance){
            synchronized (instance){
                if (null== instance){
                    instance=new SingleInstance1();
                }
            }
        }
        return instance;
    }

}
