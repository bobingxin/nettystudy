package com.dreamiii.model;

public enum MessageType {
	
	LOGIN_REQ((byte)0),LOGIN_RESP((byte)1);
	
	private byte value;
	
	private MessageType(byte value){
		this.value = value;
	}
	
	public byte getValue(){
		return value;
	}
}
