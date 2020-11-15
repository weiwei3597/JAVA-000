package com.proxy;
/**
 *
 **/

import com.bean.Student;

import java.lang.reflect.Proxy;

/**
 * @Author weiwei
 * @Date 2020-11-13 13:52
 * @description
 **/
public class ProxyDemo {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        IUser user = new User();
        ProxyFactory factory = new ProxyFactory(user);
        IUser proxyInstance = (IUser) factory.getProxyInstance();
        proxyInstance.doSomeThing();
        String aa = proxyInstance.doThing("aa");
        System.out.println(aa);
    }
}
