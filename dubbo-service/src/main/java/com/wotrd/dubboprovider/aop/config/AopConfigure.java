package com.wotrd.dubboprovider.aop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 除了@Around外，每个方法里都可以加或者不加参数JoinPoint，如果有用JoinPoint的地方就加，
 * 不加也可以，JoinPoint里包含了类名、被切面的方法名，参数等属性，可供读取使用。
 * @Around参数必须为ProceedingJoinPoint，pjp.proceed相应于执行被切面的方法。
 * @AfterReturning方法里，可以加returning = “XXX”，
 * XXX即为在controller里方法的返回值，本例中的返回值是“first controller”。
 * @AfterThrowing方法里，可以加throwing = "XXX"，
 *
 * 一般常用的有before和afterReturn组合，或者单独使用Around，即可获取方法开始前和结束后的切面
 */
//@Configuration
@Aspect
public class AopConfigure {
    @Pointcut("execution(* com.example.springbootwebdemo.aop..*(..))")
    //..代表匹配多个包名，第二个代表任意参数
    //除了execution()，还有target()，@annotation()，within()
    //within()通过类名指定切点
    //@annotation()表示标注了指定注解的目标类方法
    //target()通过类名指定，同时包含所有子类
    //表达式可由多个切点函数通过逻辑运算组成
    public void  sayHello(){}

    @Before("sayHello()")
    public void sayBefore(JoinPoint joinPoint){
        //接收到请求，处理请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String name = joinPoint.getTarget().getClass().getName();
        System.out.println("类名字"+name);
        String name1 = joinPoint.getSignature().getName();
        System.out.println("方法名"+name1);
        String kind = joinPoint.getKind();
        System.out.println("kind="+kind);
        Object[] args = joinPoint.getArgs();
        System.out.println("args="+Arrays.toString(args));
    }
    @After("sayHello()")    //final增强，不管是抛出异常或者正常退出都会执行
    public void sayAfter(JoinPoint joinPoint){
        String name = joinPoint.getTarget().getClass().getName();
        System.out.println("after类名字"+name);
        String name1 = joinPoint.getSignature().getName();
        System.out.println("after方法名"+name1);
        String kind = joinPoint.getKind();
        System.out.println("after kind="+kind);
        Object[] args = joinPoint.getArgs();
        System.out.println("after args="+Arrays.toString(args));
    }
    @AfterReturning("sayHello()")
    public void sayAfterReturning(JoinPoint joinPoint){
        String name = joinPoint.getTarget().getClass().getName();
        System.out.println("afterreturnning类名字"+name);
        String name1 = joinPoint.getSignature().getName();
        System.out.println("afterreturnning方法名"+name1);
        String kind = joinPoint.getKind();
        System.out.println("afterreturnning kind="+kind);
        Object[] args = joinPoint.getArgs();
        System.out.println("afterreturnning args="+Arrays.toString(args));
    }
    @AfterThrowing(value = "sayHello()",throwing = "error")
    public void sayAfterThrowing(JoinPoint joinPoint,Error error){
        String name = joinPoint.getTarget().getClass().getName();
        System.out.println("AfterThrowing类名字"+name);
        String name1 = joinPoint.getSignature().getName();
        System.out.println("AfterThrowing方法名"+name1);
        String kind = joinPoint.getKind();
        System.out.println("AfterThrowing kind="+kind);
        Object[] args = joinPoint.getArgs();
        System.out.println("AfterThrowing args="+Arrays.toString(args));
    }
    @Around("sayHello()")//环绕通知,环绕增强，相当于MethodInterceptor
    public Object around(ProceedingJoinPoint point){
        System.out.println("before");
        try {
            return point.proceed();

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("after");
        return null;
    }
}
