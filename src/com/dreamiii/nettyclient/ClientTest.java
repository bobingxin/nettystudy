package com.dreamiii.nettyclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientTest {
	private static final String IP = "192.168.31.119";
	private static final int PORT = 1314;
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		EventLoopGroup worker = new NioEventLoopGroup();//用来循环处理接收到得netty消息
		try {
			Bootstrap boot = new Bootstrap();//客户端服务的启动类
			boot.group(worker);
			boot.channel(NioSocketChannel.class);
			boot.option(ChannelOption.SO_KEEPALIVE, true);
			boot.handler(new ChannelInitializer<SocketChannel>() {
	
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new ClientTestHandler());
				}
			});
			ChannelFuture cf = boot.connect(IP, PORT).sync();
			cf.channel().closeFuture().sync();
		}finally{
			worker.shutdownGracefully();
		}
	}

}
