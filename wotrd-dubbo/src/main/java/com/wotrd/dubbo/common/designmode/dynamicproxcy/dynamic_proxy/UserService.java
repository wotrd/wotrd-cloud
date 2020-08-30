package com.wotrd.dubbo.common.designmode.dynamicproxcy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wkj_pc on 2017/6/5.
 */
interface Action {
    public void doSomething();
}

class RealObject implements Action{

    public void doSomething() {
        System.out.println("do something");
    }
}

class DynamicProxyHandler implements InvocationHandler {
    private Object realObject;

    public DynamicProxyHandler(Object realObject) {
        this.realObject = realObject;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //代理扩展逻辑
        System.out.println("proxy do");

        return method.invoke(realObject, args);
    }
}

public class UserService {
    public static void main(String [] args){
        RealObject realObject = new RealObject();
        Action proxy = (Action) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), RealObject.class.getInterfaces(),
                new DynamicProxyHandler(realObject));
        proxy.doSomething();

    }
}
