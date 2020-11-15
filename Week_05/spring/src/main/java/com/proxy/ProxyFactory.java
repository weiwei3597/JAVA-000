package com.proxy;
/**
 *
 **/

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author weiwei
 * @Date 2020-11-13 14:03
 * @description 代理类工厂
 **/
public class ProxyFactory {


    private final Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), (proxy, method, args) -> {
            System.out.println("前置增强");
            //执行目标对象方法
            String name = method.getName();
            if (name.equals("doThing")) {
                System.out.println("doThing");
                args[0] = args[0] + "2";
            }
            Object returnValue = method.invoke(target, args);
            if (returnValue != null) {
                returnValue = returnValue + "1";
            }
            System.out.println("后置增强");
            return returnValue;
        });
    }
}
