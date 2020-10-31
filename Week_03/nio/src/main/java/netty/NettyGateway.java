package netty;
/**
 *
 **/

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import netty.inboundHandler.HttpInboundHandler;
import netty.inboundHandler.HttpInboundInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @Author weiwei
 * @Date 2020-10-31 10:11
 * @description netty网关
 **/
public class NettyGateway {

    private static Logger log = LoggerFactory.getLogger(NettyGateway.class);


    private final String webSocketPath = "/webSocket";

    public static void main(String[] args) throws InterruptedException {
        String proxyServer = "http://localhost:8081/";
        String proxyPort = "8080";

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroop = new NioEventLoopGroup(16);
        // 启动netty服务
        ServerBootstrap bootstrap = new ServerBootstrap();
                  //Socket参数，服务端接受连接的队列长度，如果队列已满，客户端连接将被拒绝,默认值，Windows为200，其他为128。
        bootstrap.option(ChannelOption.SO_BACKLOG, 128)
                //TCP参数，立即发送数据，默认值为Ture,该值设置Nagle算法的启用
                .option(ChannelOption.TCP_NODELAY, true)
                //心跳功能 默认值为False。 默认的心跳间隔是7200s
                .option(ChannelOption.SO_KEEPALIVE, true)
                //地址复用，默认值False。
                .option(ChannelOption.SO_REUSEADDR, true)
                //TCP数据接收缓冲区大小
                .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                //TCP数据发送缓冲区大小。
                .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                //linux的地址复用
                .option(EpollChannelOption.SO_REUSEPORT, true)
                //childOption针对工作组配置
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                //ByteBuf的分配器
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        // boosGroup辅助客户端的tcp连接请求，workGroup负责与客户端之前的读写操作
        bootstrap.group(bossGroup, workGroop)
                // 是指通道类型为NIO
                .channel(NioServerSocketChannel.class)
                // 设置监听端口
                .localAddress(new InetSocketAddress(Integer.parseInt(proxyPort)));
        // 连接数达到是会创建一个通道
        bootstrap.childHandler(new HttpInboundInitializer(proxyServer));
        ChannelFuture channelFuture = bootstrap.bind().sync();
        log.info("Netty启动");
        channelFuture.channel().closeFuture().sync();

    }
}
