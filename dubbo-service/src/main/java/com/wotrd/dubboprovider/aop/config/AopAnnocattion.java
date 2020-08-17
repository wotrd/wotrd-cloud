package com.wotrd.dubboprovider.aop.config;

import com.example.springbootwebdemo.aop.annocation.UserAccess;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopAnnocattion {
    @Around("@annotation(userAccess)")
    public Object around(ProceedingJoinPoint point, UserAccess userAccess){
        System.out.println("before"+userAccess.desc());
        try {
            Object proceed = point.proceed();
            System.out.println("after"+userAccess.desc());
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("after");
            return null;
        }
    }

}
