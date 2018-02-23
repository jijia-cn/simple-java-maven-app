package com.david.test.controller;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApacheMqTestController {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	private static String queueName = "queue-test";
	
	
	@RequestMapping(value="mqsend/{message}")
	@ResponseBody
	public String sendMsg(@PathVariable("message") String message){
		Connection connection = null;
		Session session = null;
		Destination destination = null;
		MessageProducer producer = null;
		try {
			connection = jmsTemplate.getConnectionFactory().createConnection();
			connection.start();
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue(queueName);
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			TextMessage textMessage = session.createTextMessage("ActiveMq 从controller发送的消息:"+message);
			producer.send(textMessage);
			session.commit();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
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
		return "发送成功";
	}
	
	@RequestMapping(value="mqrev")
	@ResponseBody
	public String receiveMsg(){
		Connection connection = null;
		Session session = null;
		StringBuffer stringBuffer = new StringBuffer();
		try {
			connection = jmsTemplate.getConnectionFactory().createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(queueName);
			MessageConsumer consumer =session.createConsumer(destination);
			while (true) {
                //设置接收者接收消息的时间，为了便于测试，这里谁定为100s
                TextMessage message =(TextMessage) consumer.receive(20);
                if (null != message) {
                	stringBuffer.append("收到消息" + message.getText());
                } else {
                    break;
                }
            }
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
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
		return stringBuffer.toString();
	}
}
