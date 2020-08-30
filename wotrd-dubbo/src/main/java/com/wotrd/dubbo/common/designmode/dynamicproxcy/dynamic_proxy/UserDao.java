package com.wotrd.dubbo.common.designmode.dynamicproxcy.dynamic_proxy;

/**
 * Created by wkj_pc on 2017/6/4.
 */
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("开始保存");
        System.out.println("提交保存");

    }
}
