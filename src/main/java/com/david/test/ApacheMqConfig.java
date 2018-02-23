package com.david.test;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.core.JmsTemplate;

//@Configuration
@PropertySource(value="classpath:mq.properties")
@AutoConfigureAfter(value=MyBatisConfig.class)
public class ApacheMqConfig {
	
	@Value("${brokerUrl}")
	private String brokenUrl;
	
	@Value("${queueName}")
	private String queueName;
	
	
	@Bean(destroyMethod="stop")
	@ConditionalOnMissingBean
	public PooledConnectionFactory activeMQConnectionFactory(){
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokenUrl);
		PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
		pooledConnectionFactory.setConnectionFactory(connectionFactory);
		return pooledConnectionFactory;
	}
	
	@Bean
	@ConditionalOnMissingBean
	public JmsTemplate jmsTemplate(PooledConnectionFactory pooledConnectionFactory){
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(pooledConnectionFactory);
		return jmsTemplate;
	}

}
