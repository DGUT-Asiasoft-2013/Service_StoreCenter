
package org.everyday2point5.fivestore.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.everyday2point5.fivestore.entity.Goods;
import org.everyday2point5.fivestore.entity.User;
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
	
	@Autowired
	IGoodsService goodsService;
	

	@RequestMapping("/")
	public String index(){
		return "it works";
	}
	
	@RequestMapping(value = "/hello", method=RequestMethod.GET)
	public @ResponseBody String hello(){
		return "HELLO WORLD";
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
					FileUtils.copyInputStreamToFile(avatar.getInputStream(), new File(realpath,avatar+".png"));
					user.setAvatar("upload/"+avatar+".png");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			return userService.save(user);
			
		}
		
		@RequestMapping(value = "/login", method=RequestMethod.POST)
		public @ResponseBody User login(
				@RequestParam String account,
				@RequestParam String passwordHash,
				HttpServletRequest request){
				 User obj = userService.findByAccount(account);
				 if( obj != null && obj.getPassword().equals(passwordHash)){
					 
					 HttpSession session = request.getSession(true);
					 session.setAttribute("uid", obj.getId());
					 return obj;
				 }else{
					 return null;
				 }
			
			
			
		}

	

	@RequestMapping(value="/addGoods", method=RequestMethod.POST)
	public Goods addGoods(
			@RequestParam  String title,
			@RequestParam String text,
			@RequestParam Integer goods_count,
			@RequestParam float price,
			 MultipartFile goods_img,
			 HttpServletRequest request
			){
		Goods goods = new Goods();
		goods.setTitle(title);
		goods.setText(text);
		goods.setPrice(price);
		goods.setGoods_count(goods_count);
		
		
		if(goods_img != null){
			String realpath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
			try {
				FileUtils.copyInputStreamToFile(goods_img.getInputStream(), new File(realpath,goods_img+".png"));
				goods.setGoods_img("upload/"+goods_img+".png");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return goodsService.save(goods);
		
	}
	
	@RequestMapping(value="/goods",method=RequestMethod.GET)
	public Page<Goods> getGoods( HttpServletRequest request){
		return getGoods(0, request);
		
	}
	
	@RequestMapping(value="/goods/{page}",method=RequestMethod.GET)
	public Page<Goods> getGoods(
			@PathVariable int page,
			 HttpServletRequest request
			 ){
		User user = getCurrentUser(request);
		
		return goodsService.findAllGoods(user.getId(),page);
		
	}


	@RequestMapping(value = "/me", method=RequestMethod.GET)
	public @ResponseBody User getCurrentUser(HttpServletRequest request){
		 HttpSession session = request.getSession();
		 Integer uid = (Integer) session.getAttribute("uid");
			return userService.findById(uid);
	}
	
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public Page<Goods> searchArticle(
			@RequestParam  String text
			){
		
		return searchArticle(text,0);
		
	}
	
	@RequestMapping(value="/search/{page}", method=RequestMethod.POST)
	public Page<Goods> searchArticle(
			@RequestParam  String text,
			@PathVariable  int page
			){
		return goodsService.searchText(text,page);
		
	}
	
}
