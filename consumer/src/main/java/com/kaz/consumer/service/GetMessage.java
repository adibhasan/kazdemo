package com.kaz.consumer.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaz.consumer.dto.MessageDto;

@Service
public class GetMessage {
	
	private static final Logger logger = LoggerFactory.getLogger(GetMessage.class);
	
	@JmsListener(destination="kaz-message-queue")
	public void consumeMessage(String message) throws JsonMappingException, JsonProcessingException {
		logger.info("Message received from activemq queue---"+message);
		ObjectMapper objectMapper = new ObjectMapper();
		MessageDto messageDto = new MessageDto();
		messageDto = objectMapper.readValue(message, MessageDto.class);
		logger.info("Message DTO received from activemq queue---"+messageDto.getMessageDataUrl());
		getDataFromThirdPartyRESTAPI(messageDto.getMessageDataUrl());
		
	}
	
	public void getDataFromThirdPartyRESTAPI(String endpoint) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		long currentTime = timestamp.getTime();
		String url = endpoint;
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(url, String.class);
		
		Path path = Paths.get("jsonfiles/"+currentTime+".json");
		try {
			Files.writeString(path, result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
