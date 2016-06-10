package com.ntscorp.rnote.user.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ntscorp.rnote.mgr.QueueManager;
import com.ntscorp.rnote.utils.LoggedInUserManager;

/**
 * 
 * @author Jupyo Hong
 *
 */
@Controller
public class UserController {
	
	@Autowired
	QueueManager queueManager;
	
	@Autowired
	LoggedInUserManager userManager;
	
	@RequestMapping(value = "/login")
	public String login(@RequestParam("userId") String userId, HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		session.setAttribute("userId", userId);
		
		queueManager.createUserMessgeQueue(userId);
		
		return "main";
	}
	
	@RequestMapping(value = "/getUserList")
	public @ResponseBody Map<String, Set<String>> getLoggedIndUserList() {
		
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
				
		synchronized (userManager) {
			map.put("userList", LoggedInUserManager.getLoggedInUserMap().keySet());
		}
		
		return map;
	}
}
