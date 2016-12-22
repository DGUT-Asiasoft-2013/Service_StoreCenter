package org.everyday2point5.fivestore.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.everyday2point5.fivestore.entity.Inbox;
import org.everyday2point5.fivestore.entity.User;
import org.everyday2point5.fivestore.service.IInboxService;
import org.everyday2point5.fivestore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InboxController {
	
	
	@Autowired
	IInboxService inboxService;
	
	@Autowired
	IUserService userService;
	
	@RequestMapping(value="/addInbox", method=RequestMethod.POST)
	public Inbox addInbox(
			@RequestParam String content,
			@RequestParam String send_name,	
			@RequestParam Date createDate,
			HttpServletRequest request
			){
		Inbox inbox=new Inbox();
		inbox.setContent(content);
		inbox.setSend_name(send_name);
		inbox.setIsread(false);
		inbox.setCreateDate(createDate);
		System.out.print(content+send_name+createDate);
		
		HttpSession session = request.getSession();
		Integer uid = (Integer) session.getAttribute("uid");
		
		
		User user =userService.findById(uid);
		if(user!=null){
			inbox.setRec_name(user.getUser_name());
		}
			
		
		return inboxService.save(inbox);
		
	}
	

	
	


}
