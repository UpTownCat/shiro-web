package com.example.test.aspect;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.omg.CORBA.Object;

/**
 * Created by Administrator on 2017/8/8.
 */
@Aspect
public class TransactionAspect {
    @Pointcut("within(com.example.test.service..*)")
    public void firstLayer(){}

    @Before("firstLayer()")
    public void before(JoinPoint joinPoint) {
        System.out.println("aop before advice...");
    }

    @Around("firstLayer()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("aop around before...");
        pjp.proceed();
        System.out.println("aop around after...");
    }
    @AfterReturning(pointcut = "firstLayer()", returning = "returnVal")
    public void afterReturning(JoinPoint joinPoint, Object returnVal) {
        System.out.println("after returning, the value is " + returnVal);
    }

    @After("firstLayer()")
    public void after(JoinPoint joinPoint) {
        System.out.println("after");
    }
}
