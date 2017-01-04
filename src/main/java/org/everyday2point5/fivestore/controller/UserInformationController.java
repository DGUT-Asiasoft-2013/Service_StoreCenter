package org.everyday2point5.fivestore.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.everyday2point5.fivestore.entity.GoodsList;
import org.everyday2point5.fivestore.entity.User;
import org.everyday2point5.fivestore.entity.UserInformation;
import org.everyday2point5.fivestore.service.IUserInformationService;
import org.everyday2point5.fivestore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class UserInformationController {

	@Autowired
	IUserService userService;
	@Autowired
	IUserInformationService userInformationService;
	
	@RequestMapping(value="/changeInformation",method=RequestMethod.POST)
	public UserInformation changeInformation(
			@RequestParam String sex,
			@RequestParam String birth,
			@RequestParam String place,
			@RequestParam String whats_up,
			HttpServletRequest request){

		HttpSession session = request.getSession();
		Integer uid = (Integer) session.getAttribute("uid");
		User user =userService.findOne(uid);
		UserInformation userInf=getInformation(user.getId());
		
		userInf.setSex(sex);
		userInf.setBirth(birth);
		userInf.setPlace(place);
		userInf.setWhats_up(whats_up);
		
		
		return userInformationService.save(userInf);
	}
	
	@RequestMapping(value = "/getInformation", method = RequestMethod.GET)
	public UserInformation getInformation(
			@PathVariable int user_id
			) {
		UserInformation userInf=userInformationService.getInformation(user_id);
		if(userInf==null){
			userInf=new UserInformation();
			userInf.setUser_id(user_id);
			userInf.setSex("male");
			return userInformationService.save(userInf);
		}else{
			return userInf;
		}


	}
}
