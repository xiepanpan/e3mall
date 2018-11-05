package cn.e3mall.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class ActiveMqTest {

	/**
	 * 点对点形式发送消息
	 * <p>Title: testQueueProducer</p>
	 * <p>Description: </p>
	 * @throws Exception
	 */
	@Test
	public void testQueueProducer() throws Exception {
		//创建连接工厂对象 需要指定服务的ip及端口
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.130:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		//不开启事务 应答模式：自动应答
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//创建Destination对象 两种形式的queue topic 
		Queue queue = session.createQueue("test-queue");
		MessageProducer messageProducer = session.createProducer(queue);
		TextMessage textMessage = session.createTextMessage("hello activemq");
		messageProducer.send(textMessage);
		messageProducer.close();
		session.close();
		connection.close();
		
	}
	
	/**
	 * 点对点接收消息 接收后 生产的消息就不存在了
	 * <p>Title: testQueueConsumer</p>
	 * <p>Description: </p>
	 * @throws Exception
	 */
	@Test
	public void testQueueConsumer() throws Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.130:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//从队列接受消息
		Queue queue = session.createQueue("test-queue");
		//创建消费者对象
		MessageConsumer messageConsumer = session.createConsumer(queue);
		messageConsumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				TextMessage textMessage = (TextMessage)message;
				String text;
				try {
					text = textMessage.getText();
					System.out.println(text);
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		//不按回车键不执行下面的代码
		System.in.read();
		messageConsumer.close();
		session.close();
		connection.close();
	}
	
	/**
	 * 广播形式 生产者
	 * <p>Title: testTopicProducer</p>
	 * <p>Description: </p>
	 * @throws Exception
	 */
	@Test
	public void testTopicProducer() throws Exception {
		//创建连接工厂对象 需要指定服务的ip及端口
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.130:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		//不开启事务 应答模式：自动应答
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//创建Destination对象 两种形式的queue topic 
		// topic 没人消费者接收 消息丢失
		Topic topic = session.createTopic("test-queue");
		MessageProducer messageProducer = session.createProducer(topic);
		TextMessage textMessage = session.createTextMessage("topic message");
		messageProducer.send(textMessage);
		messageProducer.close();
		session.close();
		connection.close();
		
	}
	
	/**
	 * 广播形式 消费者 运行多次 产生多个消费者
	 * <p>Title: testTopicConsumer</p>
	 * <p>Description: </p>
	 * @throws Exception
	 */
	@Test
	public void testTopicConsumer() throws Exception {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.25.130:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//从队列接受消息
		Topic topic = session.createTopic("test-queue");
		//创建消费者对象
		MessageConsumer messageConsumer = session.createConsumer(topic);
		messageConsumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				TextMessage textMessage = (TextMessage)message;
				String text;
				try {
					text = textMessage.getText();
					System.out.println(text);
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		//不按回车键不执行下面的代码
		System.out.println("topic消费者3启动...");
		System.in.read();
		messageConsumer.close();
		session.close();
		connection.close();
	}
}
