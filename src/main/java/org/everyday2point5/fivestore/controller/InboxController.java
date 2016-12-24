package org.everyday2point5.fivestore.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.everyday2point5.fivestore.entity.Goods;
import org.everyday2point5.fivestore.entity.Inbox;
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
			@RequestParam Date createDate,
			HttpServletRequest request
			){
		Inbox inbox=new Inbox();
		inbox.setInboxContent(content);
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
		
		if(user.getUser_name().compareTo(send_name)>0)
			inbox.setSign(send_name+"-"+user.getUser_name());
		else
			inbox.setSign(user.getUser_name()+"-"+send_name);
			
		return inboxService.save(inbox);
		
	}
	

	@RequestMapping(value="/inbox",method=RequestMethod.GET)
	public Page<Inbox> getInbox( HttpServletRequest request){
		return getInbox(0, request);

	}

	@RequestMapping(value="/inbox/{page}",method=RequestMethod.GET)
	public Page<Inbox> getInbox(
			@PathVariable int page,
			HttpServletRequest request
			){
		HttpSession session = request.getSession();
		Integer uid = (Integer) session.getAttribute("uid");
		User user =userService.findById(uid);

		return inboxService.findAllInbox(user.getUser_name(),page);

	}
	
	
	@RequestMapping(value="/inboxsearch", method=RequestMethod.POST)
	public Page<Inbox> searchInbox(
			@RequestParam  String text,
			HttpServletRequest request
			){
	
		return searchInbox(text,0,request);

	}

	@RequestMapping(value="/inboxsearch/{page}", method=RequestMethod.POST)
	public Page<Inbox> searchInbox(
			@RequestParam  String text,
			@PathVariable  int page,
			HttpServletRequest request
			){
		
		HttpSession session = request.getSession();
		Integer uid = (Integer) session.getAttribute("uid");
		
		
		User user =userService.findById(uid);
		
		if(user.getUser_name().compareTo(text)>0)
			text=text+"-"+user.getUser_name();
		else
			text=user.getUser_name()+"-"+text;
		System.out.println(text);
		return inboxService.searchInbox(text,page);
	}
}
