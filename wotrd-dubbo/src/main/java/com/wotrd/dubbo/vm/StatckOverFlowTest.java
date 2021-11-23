package com.wotrd.dubbo.vm;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/11/17 23:46
 */
public class StatckOverFlowTest {

    private static int stackLenth;

    /**
     * The stack size specified is too small, Specify at least 160k
     * VM args -Xss188K
     */
    private static void addLenth(){
        stackLenth++;
        addLenth();
    }

    public static void main(String[] args) {
        addLenth();
    }
}
