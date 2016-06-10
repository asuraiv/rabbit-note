package com.ntscorp.rnote.message.bo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntscorp.rnote.message.model.MessageModel;
import com.ntscorp.rnote.mgr.QueueManager;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;

/**
 * 
 * @author Jupyo Hong
 *
 */
@Service
public class MessageBO {
	
	private static final String USER_INBOXES_EXCHANGE = "user-inboxes";
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@Autowired
	QueueManager queueManager;
	
	/**
	 *
	 * @param sender
	 * @param recipient
	 * @param message
	 * @return
	 */
	public String sendMessage(String sender, String recipient, String message) {
				
		Map<String, String> contents = new HashMap<String, String>();
		contents.put("sender", sender);
		contents.put("message", message);
		
		BasicProperties props = new BasicProperties().builder()
				.contentType("application/json")
				.contentEncoding("UTF-8")
				.messageId(UUID.randomUUID().toString())
				.deliveryMode(2)
				.build();
		
		Channel channel = queueManager.createChannel();
		
		try {
			channel.basicPublish(USER_INBOXES_EXCHANGE, "user-inbox." + recipient, props, MAPPER.writeValueAsString(contents).getBytes("UTF-8"));
			return "{\"result\" : \"success\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"result\" : \"fail\"}";
		}
	}
	
	/**
	 *
	 * @param userId
	 * @return
	 */
	public MessageModel getMessage(String userId) {
		
		Channel channel = queueManager.createChannel();
		
		try {
						
			GetResponse response = channel.basicGet("user-inbox." + userId, true);
			
			String contents = null;
			MessageModel messageModel = null;
			
			if(response != null) {
				contents = new String(response.getBody(), "UTF-8");				
				messageModel = MAPPER.readValue(contents, MessageModel.class);
			}						
			
			return messageModel;			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return null;
	}	
}
