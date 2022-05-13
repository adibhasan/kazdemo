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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaz.consumer.dto.MessageDto;

@Service
public class ActiveMQPullService {
	
	private static final Logger logger = LoggerFactory.getLogger(ActiveMQPullService.class);
	
	/**
	 * Get Queue Message, parse string to Message Dto
	 * @param message
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	@JmsListener(destination="kaz-message-queue")
	public void consumeMessage(String message) throws JsonMappingException, JsonProcessingException {
		logger.info("Message received from activemq queue--- "+message);
		ObjectMapper objectMapper = new ObjectMapper();
		MessageDto messageDto = new MessageDto();
		messageDto = objectMapper.readValue(message, MessageDto.class);
		String endPoint = messageDto.getMessageDataUrl();
		logger.info("Extracted REST API URL from active mq message--- "+endPoint);
		String content = getDataFromThirdPartyRESTAPI(endPoint);
		writeAndSaveResponse(content,messageDto.getMessageTitle());
	}
	
	
	/**
	 * Call http request to given end point and returns json response
	 * @param endpoint
	 * @return
	 */
	private String getDataFromThirdPartyRESTAPI(String endpoint) {
		String response;
		RestTemplate restTemplate = new RestTemplate();
		try{
			response =  restTemplate.getForObject(endpoint, String.class);
		} catch (HttpClientErrorException e){
			response = e.getMessage();
			logger.info("Invalid end point error --- "+response);
		}
		return response;
	}
	
	/**
	 * Write string to file and save in folder
	 * @param content
	 * @param filePrefix
	 */
	private void writeAndSaveResponse(String content,String filePrefix) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		long currentTime = timestamp.getTime();
		
		Path path = Paths.get("jsonfiles/"+filePrefix+"-"+currentTime+".json");
		try {
			Files.writeString(path, content);
		} catch (IOException e) {
			logger.info("Exception for file write--- "+e.getMessage());
		}
	}
	
}
