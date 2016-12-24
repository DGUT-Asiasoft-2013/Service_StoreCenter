package org.everyday2point5.fivestore.controller;

import javax.servlet.http.HttpServletRequest;

import org.everyday2point5.fivestore.entity.MyOrder;
import org.everyday2point5.fivestore.service.IOrderService;
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
		return orderService.findAll(page);
	}
	
	@RequestMapping(value="/sendGoods" ,method=RequestMethod.POST)
	public MyOrder sendGoods(
			@RequestParam String order_id){
			MyOrder order = orderService.findOneOrder(order_id);
			order.setStatus(2);
			return orderService.save(order);
		
	}
	
	
	@RequestMapping(value="/cancleOrder" ,method=RequestMethod.POST)
	public MyOrder cancleOrder(
			@RequestParam String order_id){
			MyOrder order = orderService.findOneOrder(order_id);
			order.setStatus(3);
			return orderService.save(order);
		
	}
}
