package com.ww.rpc.core.client;
/**
 *
 **/

import com.alibaba.fastjson.JSON;
import com.ww.rpc.core.domain.RpcRequest;
import com.ww.rpc.core.domain.RpcResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;

/**
 * @Author weiwei
 * @Date 2020-12-14 15:31
 * @description
 **/
@Data
@AllArgsConstructor
public class RpcClientInvoke {

    public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");


    private static OkHttpClient client = new OkHttpClient();

    private  final String url;



    public static Object invoke(String serviceClass,String methodName,Object[] args,String url) throws Throwable{
        RpcRequest req = new RpcRequest(serviceClass,methodName,args);
        RpcResponse resp = post(req,url);
        if(resp.getStatus()){
            return JSON.parse(resp.getResult().toString());
        }else {
            throw resp.getException();
        }
    }


    private static RpcResponse post(RpcRequest req,String url) throws IOException {
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
