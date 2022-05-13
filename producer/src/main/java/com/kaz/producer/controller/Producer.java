package com.kaz.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaz.producer.dto.MessageDto;
import com.kaz.producer.service.SendMessage;

@RestController
@RequestMapping("/producer")
public class Producer {
	
	@Autowired
	private SendMessage sendMessage;
	
	@CrossOrigin(origins = "*")
	@PostMapping("/message")
	public MessageDto sendMessage(@RequestBody MessageDto message) {
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			String messageAsJson = mapper.writeValueAsString(message);
            sendMessage.sendMessageToActiveMQ(messageAsJson);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return message;
		
	}

}
