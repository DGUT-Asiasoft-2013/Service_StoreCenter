package org.everyday2point5.fivestore.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.everyday2point5.fivestore.entity.Inbox;
import org.everyday2point5.fivestore.entity.InboxList;
import org.everyday2point5.fivestore.entity.User;
import org.everyday2point5.fivestore.service.IInboxService;
import org.everyday2point5.fivestore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
			HttpServletRequest request
			){
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间

		Inbox inbox=new Inbox();
		inbox.setInboxContent(content);
		inbox.setSend_name(send_name);
		inbox.setIsread(false);
		inbox.setCreateDate(curDate);

		
		HttpSession session = request.getSession();
		Integer uid = (Integer) session.getAttribute("uid");
		
		
		User user =userService.findById(uid);
		if(user!=null){
			inbox.setRec_name(user.getUser_name());
		}
		String sign;
		if(user.getUser_name().compareTo(send_name)>0)
			sign=send_name+"-"+user.getUser_name();
		else
			sign=user.getUser_name()+"-"+send_name;
		inbox.setSign(sign);
		
		Inbox return_inbox = inboxService.save(inbox);
		InboxList inboxList=inboxService.findBySign(sign);
		if (inboxList==null) {
			inboxList=new InboxList();
			inboxList.setLast_inbox(inbox);
			inboxList.setSend_name(send_name);
			inboxList.setRec_name(user.getUser_name());
			inboxList.setSign(sign);
			inboxList.setCreateDate(curDate);
			inboxService.save(inboxList);
		} else {
			inboxList.setLast_inbox(inbox);
			inboxList.setCreateDate(curDate);
			inboxService.save(inboxList);
		}
		return return_inbox;
		
	}
	

	@RequestMapping(value="/inbox",method=RequestMethod.GET)
	public Page<InboxList> getInbox( HttpServletRequest request){
		return getInbox(0, request);

	}

	@RequestMapping(value="/inbox/{page}",method=RequestMethod.GET)
	public Page<InboxList> getInbox(
			@PathVariable int page,
			HttpServletRequest request
			){
		HttpSession session = request.getSession();
		Integer uid = (Integer) session.getAttribute("uid");
		User user =userService.findById(uid);
		
		return inboxService.findInboxList(user.getUser_name(),page);

	}
	
	
	@RequestMapping(value="/inboxgetchat", method=RequestMethod.POST)
	public Page<Inbox> inboxGetChat(
			@RequestParam  String text,
			HttpServletRequest request
			){
		
		return inboxGetChat(text,0,request);

	}

	@RequestMapping(value="/inboxgetchat/{page}", method=RequestMethod.POST)
	public Page<Inbox> inboxGetChat(
			@RequestParam  String name,
			@PathVariable  int page,
			HttpServletRequest request
			){
		
		HttpSession session = request.getSession();
		Integer uid = (Integer) session.getAttribute("uid");
		
		
		User user =userService.findById(uid);
		String sign;
		if(user.getUser_name().compareTo(name)>0)
			sign=name+"-"+user.getUser_name();
		else
			sign=user.getUser_name()+"-"+name;
		return inboxService.inboxGetChat(sign,page);
	}
	

	
}
