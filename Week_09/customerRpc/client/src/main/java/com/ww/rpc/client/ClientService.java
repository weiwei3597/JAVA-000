package com.ww.rpc.client;
/**
 *
 **/

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author weiwei
 * @Date 2020-12-14 15:47
 * @description
 **/
@Component
public class ClientService implements ApplicationContextAware {


    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }


    public ApplicationContext getApplicationContext(){
        return applicationContext;
    }

}
