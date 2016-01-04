package com.dreamiii.nettyserver.handler;

import com.dreamiii.model.Header;
import com.dreamiii.model.MessageType;
import com.dreamiii.model.NettyMessage;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class LoginAuthReqHandler extends ChannelHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(buildLoginReq());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		// TODO Auto-generated method stub
		super.channelRead(ctx, msg);
	}
	
	private NettyMessage buildLoginReq(){
		NettyMessage msg = new NettyMessage();
		Header header = new Header();
		header.setType(MessageType.LOGIN_REQ.getValue());
		msg.setHeader(header);
		return msg;
	}
}
