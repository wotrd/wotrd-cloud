package com.wotrd.dubbo.common.designmode.dynamicproxcy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wkj_pc on 2017/6/5.
 */
public class UserDaoFactory implements  InvocationHandler{
    private Object target;
    public UserDaoFactory(Object target){
        this.target=target;
    }
    public Object getInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxy,args);
    }
}
