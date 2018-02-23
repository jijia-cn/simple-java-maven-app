package com.david.test.system.mq;

import javax.jms.Connection;
import javax.jms.ConnectionMetaData;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.QueueReceiver;
import javax.jms.Session;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.jmx.BrokerViewMBean;
import org.apache.activemq.broker.jmx.QueueViewMBean;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

public class ApacheMqReceiverManager {
	public static void main(String[] args) throws Exception {
		testTwo();
	}

	public static void testTwo() throws Exception {
		JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://192.168.0.68:2011/jmxrmi");
		JMXConnector connector = JMXConnectorFactory.connect(url, null);
		connector.connect();
		MBeanServerConnection connection = connector.getMBeanServerConnection();

		// 需要注意的是，这里的my-broker必须和上面配置的名称相同
		ObjectName name = new ObjectName("my-broker:BrokerName=localhost,Type=Broker");
		BrokerViewMBean mBean = (BrokerViewMBean) MBeanServerInvocationHandler.newProxyInstance(connection, name,
				BrokerViewMBean.class, true);
		// System.out.println(mBean.getBrokerName());

		for (ObjectName queueName : mBean.getQueues()) {
			QueueViewMBean queueMBean = (QueueViewMBean) MBeanServerInvocationHandler.newProxyInstance(connection,
					queueName, QueueViewMBean.class, true);
			System.out.println("\n------------------------------\n");

			// 消息队列名称
			System.out.println("States for queue --- " + queueMBean.getName());

			// 队列中剩余的消息数
			System.out.println("Size --- " + queueMBean.getQueueSize());

			// 消费者数
			System.out.println("Number of consumers --- " + queueMBean.getConsumerCount());

			// 出队数
			System.out.println("Number of dequeue ---" + queueMBean.getDequeueCount());
		}
	}

	public static void testOne() throws Exception {
		String brokenUrl = "failover:(tcp://192.168.0.68:61616,tcp://192.168.0.68:61617)";
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokenUrl);
		PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
		pooledConnectionFactory.setConnectionFactory(connectionFactory);
		JmsTemplate jmsTemplate = new JmsTemplate(pooledConnectionFactory);
		Connection connector = jmsTemplate.getConnectionFactory().createConnection();
		connector.start();
		Session session = connector.createSession(false, Session.AUTO_ACKNOWLEDGE);
		ConnectionMetaData connectionMetaData = connector.getMetaData();
		System.out.println(connector.getMetaData());
		System.out.println(connectionMetaData.getJMSProviderName());
		Destination destination = session.createQueue("queue-test");
		MessageConsumer consumer = session.createConsumer(destination);
		System.out.println(((QueueReceiver) consumer));
		System.out.println(((Queue) destination));
		connector.close();
		/*
		 * MBeanServerConnection conn = connector.getMBeanServerConnection(); //
		 * 这里brokerName的b要小些，大写会报错 // ObjectName name = new
		 * ObjectName("myDomain:brokerName=broker,type=Broker"); ObjectName name
		 * = new ObjectName("queue-test"); BrokerViewMBean mBean =
		 * (BrokerViewMBean) MBeanServerInvocationHandler.newProxyInstance(conn,
		 * name, BrokerViewMBean.class, true); for (ObjectName na :
		 * mBean.getQueues()) { QueueViewMBean queueBean = (QueueViewMBean)
		 * MBeanServerInvocationHandler.newProxyInstance(conn, na,
		 * QueueViewMBean.class, true);
		 * System.out.println("******************************");
		 * System.out.println("队列的名称：" + queueBean.getName());
		 * System.out.println("队列中剩余的消息数：" + queueBean.getQueueSize());
		 * System.out.println("消费者数：" + queueBean.getConsumerCount());
		 * System.out.println("出队列的数量：" + queueBean.getDequeueCount()); }
		 */
	}
}
