package http;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Author weiwei
 * @Date 2020-10-27 22:25
 * @description
 **/
public class HttpClientDemo {

    public static void main(String[] args) {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("http://localhost:8801/");

        try {
            CloseableHttpResponse result = client.execute(get);
            HttpEntity httpEntity = result.getEntity();
            if(httpEntity !=null){
                System.out.println(EntityUtils.toString(httpEntity));
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
