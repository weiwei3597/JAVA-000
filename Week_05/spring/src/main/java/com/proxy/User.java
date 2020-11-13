package com.proxy;
/**
 *
 **/

/**
 * @Author weiwei
 * @Date 2020-11-13 13:59
 * @description
 **/
public class User implements IUser{


    @Override
    public void doSomeThing() {
        System.out.println("doSomeThing");
    }

    @Override
    public void doThing() {
        System.out.println("doThing");
    }
}
