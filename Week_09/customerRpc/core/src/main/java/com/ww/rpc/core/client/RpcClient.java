package com.ww.rpc.core.client;
/**
 *
 **/

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.ww.rpc.core.domain.RpcRequest;
import com.ww.rpc.core.domain.RpcResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author weiwei
 * @Date 2020-12-14 10:43
 * @description
 **/
public class RpcClient {

    static {
        ParserConfig.getGlobalInstance().addAccept("com.ww.rpc.domain");
    }

    public static <T> T getService(final Class<T> serviceClass,final String url){
        return (T)Proxy.newProxyInstance(RpcClient.class.getClassLoader(),new Class[]{serviceClass},new RpcInvocationHandle(serviceClass,url));
    };




    @Data
    @AllArgsConstructor
    public static class RpcInvocationHandle implements InvocationHandler {


        public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

        private final Class<?> serviceClass;

        private final String url;

        private static OkHttpClient client = new OkHttpClient();




        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            RpcRequest req = new RpcRequest(this.serviceClass.getName(),method.getName(),args);
            RpcResponse resp = post(req);
            if(resp.getStatus()){
                return JSON.parse(resp.getResult().toString());
            }else {
                throw resp.getException();
            }
        }


        private RpcResponse post(RpcRequest req) throws IOException {
            String reqJson = JSON.toJSONString(req);
            System.out.println("reqJson: "+reqJson);
            final Request request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(JSONTYPE, reqJson))
                    .build();
            String respJson = client.newCall(request).execute().body().string();
            System.out.println("respJson: "+respJson);
            return JSON.parseObject(respJson,RpcResponse.class);
        }
    }
}
