package com.wotrd.dubbo.common.enums;

/**
 * @ClassName: StrategyTest
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/8/29 21:13
 */
public class StrategyTest {

    public static void main(String[] args) {
        Strategy execute = StrategyEnum.HIGH_STRATEGY.execute(1);
        System.out.println(execute.getClass().getName());
    }
}
