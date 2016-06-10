package com.ntscorp.rnote.mgr;

import java.io.IOException;
import java.util.Map;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.AMQP.Queue.BindOk;
import com.rabbitmq.client.AMQP.Queue.DeclareOk;
import com.rabbitmq.client.Channel;

/**
 * 
 * @author Jupyo Hong
 *
 */
@Component
public class QueueManager {
	
	private static final String USER_INBOXES_EXCHANGE = "user-inboxes";
	
	@Qualifier("mqFactory")
	@Autowired
	private CachingConnectionFactory factory;
	
	public Channel createChannel() {

		Connection connection = factory.createConnection();
		
		return connection.createChannel(false);
	}
	
	/**
	 *
	 * @param userId
	 * @return 
	 */
	public DeclareOk createUserMessgeQueue(String userId) {
		
		Channel channel = createChannel();
		
		String queueName = "user-inbox." + userId;
		boolean durable = true;
		boolean autoDelete = false;
		boolean exclusive = false;
		Map<String, Object> arguments = null;
		
		try {			
			DeclareOk declareOk = channel.queueDeclare(queueName, durable, autoDelete, exclusive, arguments);
			queueBindToExchange(queueName);
			
			return declareOk;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 *
	 * @param queueName
	 * @return 
	 * @throws IOException
	 */
	public BindOk queueBindToExchange(String queueName) throws IOException {
		
		Channel channel = createChannel();
		
		String routingKey = queueName;
		return channel.queueBind(queueName, USER_INBOXES_EXCHANGE, routingKey);
	}
}
