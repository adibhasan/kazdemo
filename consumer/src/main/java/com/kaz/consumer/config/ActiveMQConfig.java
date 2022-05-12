package com.kaz.consumer.config;

import javax.jms.Queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class ActiveMQConfig {

	
	@Value("${activemq.broker.url}")
	private String brokerUrl;
	
	@Value("${activemq.user}")
	private String brokerUser;
	
	@Value("${activemq.password}")
	private String brokerPassword;
	
	/**
    *
    * Create and set broker url to the active mq connection factory.
    * @return
    */
	
	@Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
		
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
	    connectionFactory.setBrokerURL(brokerUrl);
	    connectionFactory.setPassword(brokerUser);
	    connectionFactory.setUserName(brokerPassword);
	    return connectionFactory;
        
    }
	
	@Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
      DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
      factory.setConnectionFactory(activeMQConnectionFactory());
      factory.setConcurrency("1-9");
      return factory;
    }
}

