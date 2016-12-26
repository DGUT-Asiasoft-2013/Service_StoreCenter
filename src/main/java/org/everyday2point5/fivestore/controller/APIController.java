
package org.everyday2point5.fivestore.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.everyday2point5.fivestore.entity.Comment;
import org.everyday2point5.fivestore.entity.Goods;
import org.everyday2point5.fivestore.entity.User;
import org.everyday2point5.fivestore.service.ICommentService;
import org.everyday2point5.fivestore.service.IGoodsService;
import org.everyday2point5.fivestore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api")
public class APIController {
	@Autowired
	IUserService userService;

	

	@RequestMapping("/")
	public String index(){
		return "it works";
	}

	@RequestMapping(value = "/hello", method=RequestMethod.GET)
	public @ResponseBody String hello(){
		return "HELLO WORLD";
	}

	
		
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public @ResponseBody User login(
			@RequestParam String account,
			@RequestParam String passwordHash,
			HttpServletRequest request){
		User user = userService.findByAccount(account);
		if( user != null && user.getPassword().equals(passwordHash)){

			HttpSession session = request.getSession(true);
			session.setAttribute("uid", user.getId());
			return user;
		}else{
			return null;
		}
	}

	@RequestMapping(value = "/register", method=RequestMethod.POST)
	public @ResponseBody User register(
			@RequestParam String name,
			@RequestParam String account,
			@RequestParam String email,
			@RequestParam String passwordHash,
			MultipartFile avatar,
			HttpServletRequest request
			){
		
		User user = new User();
		
		user.setUser_name(name);
		user.setAccount(account);
		user.setEmail(email);
		user.setPassword(passwordHash);
		
		
		if(avatar != null){
			String realpath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
			try {
				FileUtils.copyInputStreamToFile(avatar.getInputStream(), new File(realpath,account+".png"));
				user.setAvatar("upload/"+account+".png");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return userService.save(user);
		
	}


}
