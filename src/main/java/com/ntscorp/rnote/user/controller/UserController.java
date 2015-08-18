package com.ntscorp.rnote.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@RequestMapping(value = "/login.nts")
	public String login(@RequestParam("userId") String userId, HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		session.setAttribute("userId", userId);
		
		// 사용자 메시지함 생성
		queueManager.createUserMessgeQueue(userId);
		
		// 로그인한 사용자 목록에 추가
		userManager.addUser(userId);
		
		return "main";
	}
	
	@RequestMapping(value = "/getUserList.nts")
	public @ResponseBody Map<String, List<String>> getLoggedIndUserList() {
		
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		
		synchronized (userManager) {
			map.put("userList", userManager.getLoggedInUserList());
		}
		
		return map;
	}
}
