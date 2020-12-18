package com.ww.rpc.core.client;
/**
 *
 **/

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @Author weiwei
 * @Date 2020-12-14 15:13
 * @description
 **/
@Aspect
@Component
public class RpcClientAop {

    @Pointcut(value="execution(public * com.ww.rpc..*.*(..))")
    public void point(){

    }

    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        Object[] args = joinPoint.getArgs();
        String serviceClass = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String url = "http://localhost:8080/";
        return RpcClientInvoke.invoke(serviceClass,methodName,args, url);
    }
}
