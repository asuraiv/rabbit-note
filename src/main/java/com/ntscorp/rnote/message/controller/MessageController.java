package com.ntscorp.rnote.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ntscorp.rnote.message.bo.MessageBO;
import com.ntscorp.rnote.message.model.MessageModel;

/**
 * 
 * @author Jupyo Hong
 *
 */
@Controller
public class MessageController {
	
	@Autowired
	MessageBO messageBO;
	
	@RequestMapping(value = "/sendMessage")
	public @ResponseBody String sendMessage(@RequestParam("sender") String sender, 
							@RequestParam("recipient") String recipient, 
							@RequestParam("message") String message) {
		return messageBO.sendMessage(sender, recipient, message);
	}
	
	@RequestMapping(value = "/getMessage")
	public @ResponseBody MessageModel getMessage(@RequestParam("userId") String userId) {
		return messageBO.getMessage(userId);
	}
	
	@RequestMapping(value = "/openSendPopup")
	public String openSendPopup(@RequestParam("sender") String sender, 
								@RequestParam("recipient") String recipient, Model model) {
		
		model.addAttribute("sender", sender);
		model.addAttribute("recipient", recipient);
		
		return "sendMessagePopup";
	}
	
	@RequestMapping(value = "/openIncommingPopup")
	public String openIncommingPopup(@RequestParam("sender") String sender,
								     @RequestParam("message") String message, Model model) {
		
		model.addAttribute("sender", sender);
		model.addAttribute("message", message);
		
		return "incommingMessagePopup";
	}
	
}
