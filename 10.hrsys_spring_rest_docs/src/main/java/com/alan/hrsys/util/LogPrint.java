package com.alan.hrsys.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogPrint {
    @Before("execution(* com.alan.hrsys.service.impl.*.*(..))")
    public void methodBegin(JoinPoint joinPoint){
        System.out.println("方法开始了");
    }
    @After("execution(* com.alan.hrsys.service.impl.*.*(..))")
    public void methodEnd(){
        System.out.println("方法结束了");
    }
    @Before("execution(* com.alan.hrsys.service.impl.*.*(..))")
    public void methodBegin1(JoinPoint joinPoint){
        System.out.println(joinPoint.getTarget()+" "+joinPoint.getSignature().getName()+"方法开始了");
    }
}
