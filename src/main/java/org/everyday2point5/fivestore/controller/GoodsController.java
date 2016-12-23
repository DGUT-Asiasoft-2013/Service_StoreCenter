package org.everyday2point5.fivestore.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.everyday2point5.fivestore.entity.Comment;
import org.everyday2point5.fivestore.entity.Goods;
import org.everyday2point5.fivestore.entity.MyOrder;
import org.everyday2point5.fivestore.entity.User;
import org.everyday2point5.fivestore.service.ICommentService;
import org.everyday2point5.fivestore.service.IGoodsService;
import org.everyday2point5.fivestore.service.IOrderService;
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
import org.springframework.web.multipart.MultipartRequest;

@RestController
@RequestMapping("/api")
public class GoodsController {
	@Autowired
	IGoodsService goodsService;

	@Autowired
	ICommentService comentsService;
	@Autowired
	IUserService userService;
	@Autowired
	IOrderService orderService;

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
		User user = getCurrentUser(request);
		String  randomNum = String.valueOf(new Random().nextInt(1000000));
		String goods_id = new java.sql.Timestamp(System.currentTimeMillis()).toString()+randomNum;
		String sale_name = user.getUser_name();
		
		goods_id = goods_id.replace(":","").replace(".", "").replace(" ", "").replace("-", "");
		goods.setGoods_id(goods_id);
		goods.setSale_name(sale_name);
		if(user != null){
			goods.setUser(user);
		}
		if(goods_img != null){
			String realpath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
			try {
				FileUtils.copyInputStreamToFile(goods_img.getInputStream(), new File(realpath,title+".png"));
				goods.setGoods_img("upload/"+title+".png");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return goodsService.save(goods);

	}

	
	@RequestMapping("/feeds/{page}")
	public Page<Goods> getFeeds(@PathVariable int page){
		return goodsService.getFeeds(page);
	}
	@RequestMapping("/feeds")
	public Page<Goods> getFeeds(){
		return getFeeds(0);
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
	
	@RequestMapping(value="goods/{goods_id}/changeGoods", method=RequestMethod.POST)
	public Goods change(
			@RequestParam String title,
			@RequestParam String text,
			@RequestParam float price,
			@RequestParam Integer goods_count,
			@PathVariable Integer goods_id
			){
		
		Goods goods = goodsService.findOne(goods_id);
		goods.setTitle(title);
		goods.setText(text);
		goods.setPrice(price);
		goods.setGoods_count(goods_count);
		
		return goodsService.save(goods);
	}
	
	
	
	@RequestMapping(value="goods/{goods_id}/comments/", method=RequestMethod.GET)
	public Page<Comment> getFirstComments(
			@PathVariable int goods_id
			){
				return comentsService.getComments(goods_id, 0);
		
	}
	
	@RequestMapping(value="goods/{goods_id}/comments/{page}", method=RequestMethod.GET)
	public Page<Comment> getComments(
			@PathVariable int goods_id,
			@PathVariable int page){
				return comentsService.getComments(goods_id, page);
		
	}
	
	
	@RequestMapping(value = "/goods/{goods_id}/deleteGoods", method = RequestMethod.DELETE)
	public boolean deleteGoods(
			@PathVariable Integer goods_id){
		Goods goods = goodsService.findOne(goods_id);
		if(goods!=null){
			goodsService.delete(goods);
			return true;
		}else{
			return false;
		}
		
	}
	
	@RequestMapping(value="/buy/{goods_id}", method=RequestMethod.POST)
	public MyOrder buy(
			@RequestParam String name,
			@RequestParam String phone,
			@RequestParam String address,
			@RequestParam int amount,
			@PathVariable String goods_id,
			HttpServletRequest  request
			){
		MyOrder order = new MyOrder();
		order.setName(name);
		order.setAddress(address);
		order.setPhone(phone);
		User user = getCurrentUser(request);
		Integer user_id = user.getId();
		order.setGoods_id(goods_id);
		int randomNum = new Random().nextInt(1000000);
		String order_num = user_id+goods_id.substring(2, 10)+randomNum;
		order.setOrder_num(order_num);
		order.setStatus(1);//确认付款
		order.setAmount(amount);
		return orderService.save(order);
		
	}
	
	@RequestMapping(value="/order")
	public Page<MyOrder> getOrder0(){
		return getOrder(0);
		
		
	}
	
	@RequestMapping(value="/order/{page}")
	public Page<MyOrder> getOrder(
			@PathVariable int page){
		return orderService.findAll(page);
		
		
	}
}
