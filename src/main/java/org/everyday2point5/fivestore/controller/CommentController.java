package org.everyday2point5.fivestore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.everyday2point5.fivestore.entity.Comment;
import org.everyday2point5.fivestore.entity.Goods;
import org.everyday2point5.fivestore.entity.User;
import org.everyday2point5.fivestore.service.ICommentDownsService;
import org.everyday2point5.fivestore.service.ICommentLikesService;
import org.everyday2point5.fivestore.service.ICommentService;
import org.everyday2point5.fivestore.service.IDownsService;
import org.everyday2point5.fivestore.service.IGoodsService;
import org.everyday2point5.fivestore.service.ILikesService;
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
	@Autowired
	ILikesService likesService;
	@Autowired
	IDownsService downService;

	@Autowired
	ICommentLikesService commentlikesSe;
	@Autowired
	ICommentDownsService commentdownSe;
	
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

	@RequestMapping(value="/goods/{order_num}/comments",method=RequestMethod.POST)
	public Comment postComment(
			@PathVariable String order_num,
			@RequestParam String text,
			@RequestParam int goods_id,
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
	

	@RequestMapping(value="comment/{id}/likes", method = RequestMethod.POST)
	public Integer changeCommentLikes(
			@PathVariable  int id,
			@RequestParam  boolean likes,
			HttpServletRequest request){
		User user = getCurrentUser(request);
		
		Comment comment = commentService.findOne(id);
		
		if(likes){
			commentlikesSe.addCommentLike(user, comment);
			commentdownSe.removeCommentDown(user, comment);
		}else{
			commentlikesSe.removeCommentLike(user, comment);
		}
		return commentlikesSe.commentLikeCount(id);
	}
	
	@RequestMapping(value="comment/{id}/likes", method = RequestMethod.GET)
	public int countLikes(
			@PathVariable  int id,
			HttpServletRequest request){
		
		return commentlikesSe.commentLikeCount(id);
	}
	
	@RequestMapping(value="comment/{id}/isLiked", method = RequestMethod.GET)
	public boolean checkLikes(
			@PathVariable  int id,
			HttpServletRequest request){
			User user= getCurrentUser(request);
			Comment comment  = commentService.findOne(id);
		return commentlikesSe.checkCommentLikesEixt(user.getId(), comment.getId());
		
	}

	
	@RequestMapping(value="comment/{id}/downs", method = RequestMethod.POST)
	public Integer changeDowns(
			@PathVariable  int id,
			@RequestParam  boolean downs,
			HttpServletRequest request){
		User user = getCurrentUser(request);
		
		Comment comment  = commentService.findOne(id);
		
		if(downs){
			commentdownSe.addCommentDown(user, comment);
			commentlikesSe.removeCommentLike(user, comment);
		}else{
			commentdownSe.removeCommentDown(user, comment);
		}
		return commentdownSe.commentDownsCount(id);
	}
	
	@RequestMapping(value="comment/{id}/downs", method = RequestMethod.GET)
	public int countDowns(
			@PathVariable  int id,
			HttpServletRequest request){
		
		return commentdownSe.commentDownsCount(id);
	}
	
	@RequestMapping(value="comment/{id}/isDowned", method = RequestMethod.GET)
	public boolean checkDowns(
			@PathVariable  int id,
			HttpServletRequest request){
			User user= getCurrentUser(request);
			Comment comment  = commentService.findOne(id);
		return commentdownSe.checkCommentDownsExit(user.getId(), comment.getId());
		
	}
	
	
	@RequestMapping(value="comment/{id}/getDownNum", method = RequestMethod.GET)
	public int getDownNum(
			@PathVariable  int id){
		return commentdownSe.commentDownsCount(id);
		
	}
	
	@RequestMapping(value="comment/{id}/getLikeNum", method = RequestMethod.GET)
	public int getLikeNum(
			@PathVariable  int id){
		return commentlikesSe.commentLikeCount(id);
		
	}
	
	
	
	
}
