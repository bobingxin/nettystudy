package com.dreamiii.model;

import java.util.List;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

public class NettyEncoder extends MessageToMessageEncoder<NettyMessage> {

	@Override
	protected void encode(ChannelHandlerContext ctx, NettyMessage msg,
			List<Object> out) throws Exception {
		
		if(msg == null || msg.getHeader() == null){
			throw new Exception("the encode message is null");
		}
		
		ByteBuf sendBuf = Unpooled.buffer();
		sendBuf.writeInt(msg.getHeader().getCrcCode());//magic+大版本+小版本_2+1+1
		sendBuf.writeInt(msg.getHeader().getLength());//整个消息的长度包括消息头_4
		sendBuf.writeLong(msg.getHeader().getSessionID());
		sendBuf.writeByte(msg.getHeader().getType());
		sendBuf.writeByte(msg.getHeader().getPriority());
		sendBuf.writeByte(msg.getHeader().getAttachment().size());//附件的个数
		String key = null;
		byte[] keyArray = null;
		Object value = null;
		for(Map.Entry<String,Object> param : msg.getHeader().getAttachment().entrySet()){
			key = param.getKey();
			keyArray = key.getBytes("UTF-8");
			sendBuf.writeInt(keyArray.length);
			sendBuf.writeBytes(keyArray);
			value = param.getValue();
		}
	}

}
