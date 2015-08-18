package com.ntscorp.rnote.utils;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * 로그인한 유저들을 메모리에 저장
 * 
 * @author Jupyo Hong
 *
 */
@Component
public class LoggedInUserManager {
	
	private static List<String> loggedInUserList = new ArrayList<String>();
	
	public void addUser(String userId) {
		if(loggedInUserList.contains(userId)) {
			return ;
		}
		loggedInUserList.add(userId);
	}
	
	public void removeUser(String userId) {
		loggedInUserList.remove(userId);
	}	
	
	public List<String> getLoggedInUserList() {
		return loggedInUserList;
	}
}
