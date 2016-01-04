package com.dreamiii.model;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {

	public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset,
			int lengthFieldLength) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in)
			throws Exception {
		ByteBuf frame = (ByteBuf) super.decode(ctx, in);
		if(frame == null){
			return null;
		}
		
		NettyMessage msg = new NettyMessage();
		Header header = new Header();
		ctx.fireChannelRead(msg);
		return msg;
	}
	
	
}
