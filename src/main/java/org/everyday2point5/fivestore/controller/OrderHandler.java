package org.everyday2point5.fivestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.everyday2point5.fivestore.entity.Goods;
import org.everyday2point5.fivestore.entity.MyOrder;
import org.everyday2point5.fivestore.entity.User;
import org.everyday2point5.fivestore.service.IGoodsService;
import org.everyday2point5.fivestore.service.IOrderService;
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
public class OrderHandler {
	@Autowired
	IOrderService orderService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IGoodsService goodsService;
	

	
	@RequestMapping(value="/sendGoods" ,method=RequestMethod.POST)
	public MyOrder sendGoods(
			@RequestParam String order_id){
			MyOrder order = orderService.findOneOrder(order_id);
			order.setStatus(2);
			return orderService.save(order);
		
	}
	
	@RequestMapping(value="/confirmGoods" ,method=RequestMethod.POST)
	public MyOrder confirmGoods(
			@RequestParam String order_id,
			@RequestParam int sale_id,
			@RequestParam int amount,
			@RequestParam Float price){
			MyOrder order = orderService.findOneOrder(order_id);
			order.setStatus(0);
			moneyChange(price,sale_id);
			return orderService.save(order);
		
	}
	
	public User moneyChange(float f,int uid) {
		
		User user =userService.findOne(uid);
		user.setMoney((user.getMoney()+f));
		return userService.save(user);
	}
	
	
	
	@RequestMapping(value="/cancleOrder" ,method=RequestMethod.POST)
	public MyOrder cancleOrder(
			@RequestParam String order_id){
			MyOrder order = orderService.findOneOrder(order_id);
			order.setStatus(3);
			return orderService.save(order);
		
	}
	
	
	@RequestMapping(value="/order",method=RequestMethod.GET)
	public Page<MyOrder> getOrder(
			HttpServletRequest request){
		return getOrder(0, request);
	}
	
	@RequestMapping(value="/order/{page}",method=RequestMethod.GET)
	public Page<MyOrder> getOrder(
			@PathVariable int page,
			HttpServletRequest request
			){
		HttpSession session = request.getSession(true);
		Integer sale_id = (Integer) session.getAttribute("uid");
		return orderService.findAll(page, sale_id);
	}
	
	
	@RequestMapping(value="/order/myDeal",method=RequestMethod.GET)
	public Page<MyOrder> getMyDeal(
			HttpServletRequest request){
		return getMyDeal(0, request);
	}
	
	@RequestMapping(value="/order/myDeal/{page}",method=RequestMethod.GET)
	public Page<MyOrder> getMyDeal(
			@PathVariable int page,
			HttpServletRequest request
			){
		
		HttpSession session = request.getSession(true);
		Integer uid = (Integer) session.getAttribute("uid");
		
		return orderService.findAllMyDeal(uid, page);
	}

	@RequestMapping(value="/order/status/{status}",method=RequestMethod.GET)
	public Page<MyOrder> getStatusOrder(
			@PathVariable int status,
			HttpServletRequest request
			){
		return getStatusOrder(status,  0,request);
	}
	
	@RequestMapping(value="/order/status/{status}/{page}",method=RequestMethod.GET)
	public Page<MyOrder> getStatusOrder(
			@PathVariable int status,
			@PathVariable int page,
			HttpServletRequest request
			){
		HttpSession session = request.getSession(true);
		Integer sale_id = (Integer) session.getAttribute("uid");
		Page<MyOrder> o =orderService.findStatusOrder(status, sale_id, page);
		return o;
	}
}

