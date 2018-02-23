package com.david.test.system.mq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

//@Configuration
public class ApacheMqSystemService {
	//p2p
//	@Bean(initMethod="init",destroyMethod="destroy")
//	public ApacheMqSender apacheMqSender(JmsTemplate jmsTemplate){
//		return new ApacheMqSender(jmsTemplate);
//	}
//	
//	@Bean(initMethod="init",destroyMethod="destroy")
//	public ApacheMqReceiver apacheMqReceiver1(JmsTemplate jmsTemplate){
//		return new ApacheMqReceiver(jmsTemplate,"consumer1");
//	}
//	
//	@Bean(initMethod="init",destroyMethod="destroy")
//	public ApacheMqReceiver apacheMqReceiver2(JmsTemplate jmsTemplate){
//		return new ApacheMqReceiver(jmsTemplate,"consumer2");
//	}
	
	//topic
	@Bean(initMethod="init",destroyMethod="destroy")
	public ApacheMqTopicSender apacheMqTopicSender1(JmsTemplate jmsTemplate){
		return new ApacheMqTopicSender(jmsTemplate);
	}
	
	@Bean(initMethod="init",destroyMethod="destroy")
	public ApacheMqTopicSender apacheMqTopicSender2(JmsTemplate jmsTemplate){
		return new ApacheMqTopicSender(jmsTemplate);
	}
	
	@Bean(initMethod="init",destroyMethod="destroy")
	public ApacheMqTopicReceiver apacheMqTopicReceiver1(JmsTemplate jmsTemplate){
		return new ApacheMqTopicReceiver(jmsTemplate,"consumer1");
	}
	
	@Bean(initMethod="init",destroyMethod="destroy")
	public ApacheMqTopicReceiver apacheMqTopicReceiver2(JmsTemplate jmsTemplate){
		return new ApacheMqTopicReceiver(jmsTemplate,"consumer2");
	}
}
