package com.ntscorp.rnote.websocket;

import java.io.IOException;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.ntscorp.rnote.utils.LoggedInUserManager;

/**
 * 
 * @author Jupyo Hong
 *
 */
@ServerEndpoint("/message")
public class MessageServerEndPoint {
	
	@OnMessage
	public void onMessage(String recipientUserId, Session session) throws IOException, InterruptedException {

		System.out.println("�޴� ���: " + recipientUserId);
		
		Map<String, Session> loggedInUserMap = LoggedInUserManager.getLoggedInUserMap();
		
		for(String userId : loggedInUserMap.keySet()) {
			
			if(userId.equals(recipientUserId)) {
				
				Session recipientUserSession = loggedInUserMap.get(userId);
				
				// ������ session�� Ŭ���̾�Ʈ�� �޽��� ���� �˸��� push�Ѵ�.
				recipientUserSession.getBasicRemote().sendText("message arrived");
			}
		}		
	}

	@OnOpen
	public void onOpen(Session session) {
		// session��ü�� �Բ� userId�� �ʿ� �����Ѵ�.
		String userId = session.getRequestParameterMap().get("userId").get(0);
		LoggedInUserManager.addUser(userId, session);
	}

	@OnClose
	public void onClose(Session session) {
		// Ŀ�ؼ��� ����ɶ� �ش� user�� �ʿ��� �����Ѵ�
		String userId = session.getRequestParameterMap().get("userId").get(0);
		LoggedInUserManager.removeUser(userId);
	}
}
