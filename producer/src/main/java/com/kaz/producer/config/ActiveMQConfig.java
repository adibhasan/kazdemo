package com.kaz.producer.config;

import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class ActiveMQConfig {

	@Value("${activemq.broker.url}")
	private String brokerUrl;

	/**
     * setting the stand alone queue name
     * @return
     */
	@Bean
	public Queue queue() {

		return new ActiveMQQueue("kaz-message-queue");
		
	}
	
	/**
    *
    * Create and set broker url to the active mq connection factory.
    * @return
    */
	
	@Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
		
        ActiveMQConnectionFactory activeMQConnectionFactory =  new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl);
        return activeMQConnectionFactory;
        
    }
	
	@Bean
    public JmsTemplate jmsTemplate(){
        return new JmsTemplate(activeMQConnectionFactory());
    }
}
