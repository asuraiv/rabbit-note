package com.ntscorp.rnote.utils;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

import org.springframework.stereotype.Component;

/**
 *
 * @author Jupyo Hong
 *
 */
@Component
public class LoggedInUserManager {
	
	private static Map<String, Session> loggedInUserMap = new HashMap<String, Session>();
	
	public static void addUser(String userId, Session session) {
		if(loggedInUserMap.keySet().contains(userId)) {
			return ;
		}
		loggedInUserMap.put(userId, session);
	}
	
	public static void removeUser(String userId) {
		loggedInUserMap.remove(userId);
	}	
	
	public static Map<String, Session> getLoggedInUserMap() {
		return loggedInUserMap;
	}
}
