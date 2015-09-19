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

		System.out.println("받는 사람: " + recipientUserId);
		
		Map<String, Session> loggedInUserMap = LoggedInUserManager.getLoggedInUserMap();
		
		for(String userId : loggedInUserMap.keySet()) {
			
			if(userId.equals(recipientUserId)) {
				
				Session recipientUserSession = loggedInUserMap.get(userId);
				
				// 수신자 session의 클라이언트에 메시지 도착 알림을 push한다.
				recipientUserSession.getBasicRemote().sendText("message arrived");
			}
		}		
	}

	@OnOpen
	public void onOpen(Session session) {
		// session객체와 함께 userId를 맵에 저장한다.
		String userId = session.getRequestParameterMap().get("userId").get(0);
		LoggedInUserManager.addUser(userId, session);
	}

	@OnClose
	public void onClose(Session session) {
		// 커넥션이 종료될때 해당 user를 맵에서 제거한다
		String userId = session.getRequestParameterMap().get("userId").get(0);
		LoggedInUserManager.removeUser(userId);
	}
}
