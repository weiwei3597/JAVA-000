package com.ww.rpc.service.resolver;
/**
 *
 **/

import com.ww.rpc.core.domain.RpcResolve;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author weiwei
 * @Date 2020-12-14 11:23
 * @description
 **/
public class ServiceResolve implements RpcResolve, ApplicationContextAware {


    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object resolve(String serviceClass) {
        return applicationContext.getBean(serviceClass);
    }
}
