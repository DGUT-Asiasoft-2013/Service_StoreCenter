package org.everyday2point5.fivestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

	public User getCurrentUser(HttpServletRequest request){
		HttpSession session=request.getSession(true);
		Integer uid=(Integer) session.getAttribute("uid");
		return userService.findById(uid);
	}
	
	@RequestMapping(value="/goods/{goods_id}/comments/{page}",method=RequestMethod.GET)
	public Page<Comment> getCommentsOfGoods(
			@PathVariable String goods_id,
			@PathVariable int page){
		return commentService.findCommentsOfGoods(goods_id, page);
	}
	
	@RequestMapping(value="/goods/{goods_id}/comments",method=RequestMethod.GET)
	public Page<Comment>getCommentOfGoods(
			@PathVariable String  goods_id){
		return commentService.findCommentsOfGoods(goods_id, 0);
	}

	@RequestMapping(value="/goods/{goods_id}/comments",method=RequestMethod.POST)
	public Comment postComment(
			@PathVariable String goods_id,
			@RequestParam String text,
			HttpServletRequest request){
		User me=getCurrentUser(request);
		Goods goods=goodsService.findOne(goods_id);
		Comment comment=new Comment();
		comment.setAuthor(me);
		comment.setGoods(goods);
		comment.setText(text);
		return commentService.save(comment);
		
	}
}
