package Entities;

import java.io.Serializable;

public class Message implements Serializable{

	private static final long serialVersionUID = 1L;
	private MessageType messageType;
	private Object messageData;
	
	public Message(MessageType messageType,Object messageData) {
		this.messageType = messageType;
		this.messageData = messageData;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public Object getMessageData() {
		return messageData;
	}
	
	public String toString() {
		return "MESSAGE";
	}	
}