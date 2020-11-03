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
import netty.NettyGateway;
import netty.inboundHandler.filter.CommonFilter;
import netty.inboundHandler.filter.HttpRequestFilter;
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
import java.util.List;

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
    private HttpOutboundHandler handler;

    public HttpInboundHandler(){
        this.proxyServer = "http://localhost:8081/";
    }

    public HttpInboundHandler(String  proxyServer){
        this.proxyServer = proxyServer;
        handler = new HttpOutboundHandler(this.proxyServer);
    }


    // 当通道初始化时添加了handler时调用
    @Override
    public void handlerAdded(ChannelHandlerContext ctx)  {
        log.info("HttpInboundHandler 被调用：" + ctx.channel().id().asLongText());
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        FullHttpRequest req=(FullHttpRequest) msg;
        //执行过滤器
        List<HttpRequestFilter> filterList = NettyGateway.filterList;
        filterList.forEach(filter->{
            filter.filter(req,ctx);
        });
        handler.handle(ctx,req);
    }

}
