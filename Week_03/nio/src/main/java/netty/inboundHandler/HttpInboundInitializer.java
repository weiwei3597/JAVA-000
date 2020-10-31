package netty.inboundHandler;
/**
 *
 **/

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Author weiwei
 * @Date 2020-10-31 10:36
 * @description HttpInboundHandler
 **/
public class HttpInboundInitializer extends ChannelInitializer {

    private final String proxyServer;

    public HttpInboundInitializer(){
        this.proxyServer = "http://localhost:8081/";
    }

    public HttpInboundInitializer(String  proxyServer){
        this.proxyServer = proxyServer;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline p = channel.pipeline();
        /**
         * 解析请求头数据
         */
        p.addLast(new HttpServerCodec());
        /**
         * 解析请求体中数据
         * 说明：
         * 1、http数据在传输过程众是分段的,httpObjectAggregator可以将多个段聚合
         * 2、这就是为什么，当浏览器发送大量的数据时，就会发送多次http请求
         * 3、1024 * 1024最大请求内容长度
         */
        p.addLast(new HttpObjectAggregator(1024 * 1024));

        p.addLast(new HttpInboundHandler(this.proxyServer));
    }
}
