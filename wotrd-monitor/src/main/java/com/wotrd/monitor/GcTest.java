package com.wotrd.monitor;



/**
 * @ClassName: GcTest
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/9/6 00:38
 */
public class GcTest {

    private static ThreadLocal<MonitorServiceApplication> threadLocal= new ThreadLocal<>();

    public static void main(String[] args) {
        MonitorServiceApplication application = null;
       threadLocal.set(application);
        Object o = threadLocal.get();
        System.out.println(o);
    }
}

