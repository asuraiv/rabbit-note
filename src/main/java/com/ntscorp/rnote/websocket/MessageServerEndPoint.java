package com.ntscorp.rnote.websocket;

import java.io.IOException;

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
	public void onMessage(String recipientId, Session session) throws IOException, InterruptedException {

		System.out.println("받는 사람: " + recipientId);
		
		Session recipientSession = LoggedInUserManager.getLoggedInUserMap().get(recipientId);
		
		recipientSession.getBasicRemote().sendText("message arrived");
	}

	@OnOpen
	public void onOpen(Session session) {
		// session객체와 함께 userId를 맵에 저장한다
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
