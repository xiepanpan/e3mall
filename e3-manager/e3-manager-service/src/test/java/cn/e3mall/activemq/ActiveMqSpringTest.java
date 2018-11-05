package cn.e3mall.activemq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javassist.expr.NewArray;

public class ActiveMqSpringTest {
	
//	@Test
//	public void sendMessage() {
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
//		//根据类型取对象
//		JmsTemplate jmsTemplate = applicationContext.getBean(JmsTemplate.class);
//		//根据id取对象
//		Destination destination = (Destination)applicationContext.getBean("queueDestination");
//		jmsTemplate.send(destination,new MessageCreator() {
//			@Override
//			public Message createMessage(Session session) throws JMSException {
//				return session.createTextMessage("send activemq message");
//			}
//		});
//	}
}
