package com.ww.rpc.core.service;
/**
 *
 **/


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ww.rpc.core.domain.RpcRequest;
import com.ww.rpc.core.domain.RpcResolve;
import com.ww.rpc.core.domain.RpcResponse;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author weiwei
 * @Date 2020-12-14 11:07
 * @description
 **/
@AllArgsConstructor
public class RpcService {


    private RpcResolve rpcResolve;



     public RpcResponse invoke(RpcRequest req)  {
         RpcResponse response = new RpcResponse();
         String serviceClass = req.getServiceClass();
         String methodName = req.getMethod();
         Object[] param = req.getParam();
         Object service = rpcResolve.resolve(serviceClass);
         try {
             Method method = service.getClass().getDeclaredMethod(methodName, paramToClassArr(param));
             Object result = method.invoke(service, param);
             //SerializerFeature.WriteClassName 自省功能优化 序列化时带上序列化类型
             response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
             response.setStatus(true);
             return response;
         }catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e ){
             e.printStackTrace();
             response.setException(e);
             response.setStatus(false);
             return response;
         }
     }


     private Class[] paramToClassArr(Object[] param){
         Class[] result = new Class[param.length];
         for (int i = 0; i < param.length; i++) {
             result[i] = param[i].getClass();
         }
         return result;
     }
}
