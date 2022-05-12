package com.kaz.producer.service;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class SendMessage {
	
	private JmsTemplate jmsTemplate;
	
	@Autowired
	private Queue queue;
	
	@Autowired
	public SendMessage(JmsTemplate jmsTemplate) {
		
		this.jmsTemplate = jmsTemplate;
	}
	
	public void sendMessageToActiveMQ(String message) {
		
		jmsTemplate.convertAndSend(queue,message);
		
	}

}
