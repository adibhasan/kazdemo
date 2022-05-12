package com.kaz.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class GetMessage {
	
	private static final Logger logger = LoggerFactory.getLogger(GetMessage.class);
	
	@JmsListener(destination="kaz-message-queue")
	public void consumeMessage(String message) {
		logger.info("Message received from activemq queue---"+message);
	}
	
}
