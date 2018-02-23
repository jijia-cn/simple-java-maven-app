package com.david.test.system.mq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsTemplate;

public class ApacheMqTopicReceiver implements MessageListener{

	private static String topicName = "topic-test";
	
	private JmsTemplate jmsTemplate;
	private Connection connection = null;
	private Session session = null;
	private MessageConsumer consumer = null;
	
	//区别consumer
	private String name;
	
	public ApacheMqTopicReceiver(JmsTemplate jmsTemplate,String name) {
		this.jmsTemplate = jmsTemplate;
		this.name = name;
	}

	public void init(){
		try {
			connection = jmsTemplate.getConnectionFactory().createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = new ActiveMQTopic(topicName);
			consumer =session.createConsumer(destination);
			consumer.setMessageListener(this);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void destroy(){
		if(session != null){
			try {
				session.close();
				session = null;
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(connection != null){
			try {
				connection.close();
				connection = null;
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		try {
			System.out.println(this.name+" receive message:"+((TextMessage)message).getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
