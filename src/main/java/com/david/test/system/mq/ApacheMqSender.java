package com.david.test.system.mq;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;

/**
 * mq消息发送者
 * @author jia ji
 *
 */
public class ApacheMqSender implements Runnable{
	private static String queueName = "queue-test";
	
	private JmsTemplate jmsTemplate;
	private Connection connection = null;
	private Session session = null;
	private Destination destination = null;
	private MessageProducer producer = null;
	
	
	public ApacheMqSender(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void init(){
		System.out.println("mq sender init..");
		try {
			connection = jmsTemplate.getConnectionFactory().createConnection();
			connection.start();
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue(queueName);
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread thread = new Thread(this);
		thread.setDaemon(true);
		thread.start();
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
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Double msgSize = (Double)Math.random()*10;
				for(int i=0;i<msgSize.intValue();i++){
					Double randomId = (Double)Math.random()*1000;
					TextMessage textMessage = session.createTextMessage("ActiveMq 从controller发送的消息:"+randomId.intValue());
					producer.send(textMessage);
				}
				session.commit();
				System.out.println("sender:发送成功! 发送数据量:"+msgSize.intValue()+"个");
				Thread.sleep(4000);
			} catch (JMSException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
