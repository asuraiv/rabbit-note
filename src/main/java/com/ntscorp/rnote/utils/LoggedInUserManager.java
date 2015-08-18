package com.ntscorp.rnote.utils;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * �α����� �������� �޸𸮿� ����
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
