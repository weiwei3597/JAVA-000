package netty.inboundHandler;
/**
 *
 **/

import base.BaseGateway;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.ReferenceCountUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static io.netty.handler.codec.http.HttpResponseStatus.NO_CONTENT;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @Author weiwei
 * @Date 2020-10-31 10:44
 * @description HttpInboundHandler
 **/
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private static Logger log = LoggerFactory.getLogger(HttpInboundHandler.class);
    private  String proxyServer;

    public HttpInboundHandler(){
        this.proxyServer = "http://localhost:8081/";
    }

    public HttpInboundHandler(String  proxyServer){
        this.proxyServer = proxyServer;
    }


    // 当通道初始化时添加了handler时调用
    @Override
    public void handlerAdded(ChannelHandlerContext ctx)  {
        log.info("HttpInboundHandler 被调用：" + ctx.channel().id().asLongText());
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        FullHttpResponse response = null;
        try {
            log.info(String.format("接收到客户端socket消息：%s", msg));
            CloseableHttpClient client = HttpClientBuilder.create().build();
            HttpGet get = new HttpGet(proxyServer);
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
