package org.everyday2point5.fivestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.everyday2point5.fivestore.entity.Comment;
import org.everyday2point5.fivestore.entity.Goods;
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
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IGoodsService goodsService;
	
	@Autowired
	ICommentService commentService;
	
	@Autowired
	IOrderService orderService;

	public User getCurrentUser(HttpServletRequest request){
		HttpSession session=request.getSession(true);
		Integer uid=(Integer) session.getAttribute("uid");
		return userService.findOne(uid);
	}
	
	@RequestMapping(value="/goods/{id}/comments/{page}",method=RequestMethod.GET)
	public Page<Comment> getCommentsOfGoods(
			@PathVariable int id,
			@PathVariable int page){
		return commentService.findCommentsOfGoods(id, page);
	}
	
	@RequestMapping(value="/goods/{id}/comments",method=RequestMethod.GET)
	public Page<Comment>getCommentOfGoods(
			@PathVariable int id){
		return commentService.findCommentsOfGoods(id, 0);
	}

	@RequestMapping(value="/goods/{goods_id}/{order_num}/comments",method=RequestMethod.POST)
	public Comment postComment(
			@PathVariable int goods_id,
			@PathVariable String order_num,
			@RequestParam String text,
			@RequestParam int id,
			HttpServletRequest request){
		User me=getCurrentUser(request);
		Comment comment=new Comment();
		comment.setAuthor(me);
		Goods goods = goodsService.findOne(goods_id);
		comment.setGoods(goods);
		comment.setText(text);
		comment.setOrder_num(order_num);
		return commentService.save(comment);
		
	}
}
