package com.proxy;
/**
 *
 **/

/**
 * @Author weiwei
 * @Date 2020-11-13 13:59
 * @description
 **/
public class User implements IUser {


    @Override
    public void doSomeThing() {
        System.out.println("doSomeThing");
    }

    @Override
    public String doThing(String s1) {
        System.out.println("doThing" + s1);
        return "result";
    }
}
