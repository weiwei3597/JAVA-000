package netty.inboundHandler.filter;
/**
 *
 **/

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @Author weiwei
 * @Date 2020-11-01 17:36
 * @description
 **/
public class CommonFilter implements HttpRequestFilter{

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().add("netty","weiwei");
    }

}
