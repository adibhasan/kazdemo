package com.kaz.producer.dto;

import java.io.Serializable;

public class MessageDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String messageDataUrl;
	private String messageTitle;
	private String messageId;
	
	public String getMessageDataUrl() {
		return messageDataUrl;
	}

	public void setMessageDataUrl(String messageDataUrl) {
		this.messageDataUrl = messageDataUrl;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	} 

}
