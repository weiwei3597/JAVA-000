package netty.inboundHandler;
/**
 *
 **/

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @Author weiwei
 * @Date 2020-11-01 15:45
 * @description
 **/
public class HttpOutboundHandler {


    private static Logger log = LoggerFactory.getLogger(HttpOutboundHandler.class);

    private String url;

    private CloseableHttpClient client ;

    private ExecutorService executorService;

    public HttpOutboundHandler (){

    }


    public HttpOutboundHandler (String url){
        this.url = url;
        executorService = Executors.newCachedThreadPool();
        client= HttpClientBuilder.create().build();
    }



    public void handle(ChannelHandlerContext ctx, FullHttpRequest req){
        final String url = this.url + req.uri();
        executorService.submit(()->task(req, ctx, url));
    }


    public void task( FullHttpRequest req,  ChannelHandlerContext ctx, final String url){
        FullHttpResponse response = null;
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet get = new HttpGet(url);
            get.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
            CloseableHttpResponse result = client.execute(get);
            HttpEntity httpEntity = result.getEntity();
            System.out.println("网关转发结束");
            assert httpEntity != null;
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer( EntityUtils.toByteArray(httpEntity)));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Length", Integer.parseInt(result.getFirstHeader("Content-Length").getValue()));
        } catch(Exception e) {
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HTTP_1_1, NO_CONTENT);
            exceptionCaught(ctx, e);
        } finally {
            ctx.write(response);
            ctx.flush();
        }

    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
