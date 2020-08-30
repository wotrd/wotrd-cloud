package com.wotrd.dubbo.common.designmode.dynamicproxcy.static_proxy;

/**
 * Created by wkj_pc on 2017/6/5.
 */
public class UserDaoProxy implements IUserDao{
    private IUserDao target;
    public UserDaoProxy(IUserDao target){
        this.target=target;
    }

    @Override
    public void save() {
        System.out.println("准备保存！");
    }
}
