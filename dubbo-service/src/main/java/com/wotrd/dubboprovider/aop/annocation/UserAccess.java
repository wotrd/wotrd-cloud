package com.wotrd.dubboprovider.aop.annocation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserAccess {
    String desc() default "无信息";
  //  Class<?> obj(); 注入实体类
}
