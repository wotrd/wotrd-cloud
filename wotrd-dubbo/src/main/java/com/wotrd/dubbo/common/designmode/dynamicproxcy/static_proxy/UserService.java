package com.wotrd.dubbo.common.designmode.dynamicproxcy.static_proxy;

/**
 * Created by wkj_pc on 2017/6/5.
 */
public class UserService {
    public static void main(String [] args){
        UserDao userDao=new UserDao();
        userDao.save();
        IUserDao userDao1=new UserDaoProxy(userDao);
        userDao1.save();
    }
}
