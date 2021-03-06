
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
	IUserService userService;
	@Autowired
	IOrderService orderService;
	@Autowired
	ILikesService likesService;
	@Autowired
	IDownsService downService;
	
	@RequestMapping(value="/addGoods", method=RequestMethod.POST)
	public Goods addGoods(
			@RequestParam  String title,
			@RequestParam String text,
			@RequestParam Integer goods_count,
			@RequestParam float price,
			@RequestParam String sort,
			MultipartFile goods_img,
			HttpServletRequest request
			){
		Goods goods = new Goods();
		goods.setTitle(title);
		goods.setText(text);
		goods.setPrice(price);
		goods.setGoods_count(goods_count);
		
		User user = getCurrentUser(request);
		String  randomNum = String.valueOf(new Random().nextInt(1000));
		String goods_id = new java.sql.Timestamp(System.currentTimeMillis()).toString()+randomNum;
		String sale_name = user.getUser_name();
		
		goods_id = goods_id.replace(":","").replace(".", "").replace(" ", "");
		goods_id = goods_id.replace("-", "");
		goods.setGoods_id(goods_id);
		goods.setSale_name(sale_name);
		goods.setStatus(0); //未購買
		goods.setSort(sort);
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
		
		HttpSession session = request.getSession(true);
		Integer uid = (Integer) session.getAttribute("uid");
		Page<Goods> g = goodsService.findAllGoods(uid ,page);
		return g;

	}
	
	@RequestMapping(value="/goodsById/{id}",method=RequestMethod.GET)
	public Page<Goods> getGoodsById( 
			@PathVariable int id,
			HttpServletRequest request){
		return getGoodsById(0,id,request);
	}

	@RequestMapping(value="/goodsById/{id}/{page}",method=RequestMethod.GET)
	public Page<Goods> getGoodsById(
			@PathVariable int page,
			@PathVariable int id,
			HttpServletRequest request
			){
		
		Page<Goods> g = goodsService.findAllGoods(id ,page);
		return g;

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
	
	@RequestMapping(value="goods/{id}/changeGoods", method=RequestMethod.POST)
	public Goods change(
			@RequestParam String title,
			@RequestParam String text,
			@RequestParam float price,
			@RequestParam Integer goods_count,
			@PathVariable int id
			){
		
		Goods goods = goodsService.findOne(id);
		goods.setTitle(title);
		goods.setText(text);
		goods.setPrice(price);
		goods.setGoods_count(goods_count);
		
		return goodsService.save(goods);
	}
	
	
	
	@RequestMapping(value = "/goods/{id}/deleteGoods", method = RequestMethod.DELETE)
	public boolean deleteGoods(
			@PathVariable int id){
		Goods goods = goodsService.findOne(id);
		if(goods!=null){
			goods.setStatus(2); //已下架
			goodsService.save(goods);
			return true;
		}else{
			return false;
		}
	}
	
	@RequestMapping(value="/buy/{goods_id}/{id}", method=RequestMethod.POST)
	public MyOrder buy(
			@RequestParam String name,
			@RequestParam String phone,
			@RequestParam String address,
			@RequestParam int amount,
			@RequestParam float price,
			@PathVariable String goods_id,
			@PathVariable int id,
			HttpServletRequest  request
			){
		MyOrder order = new MyOrder();
		order.setName(name);
		order.setAddress(address);
		order.setPhone(phone);

		User user =  getCurrentUser(request);
		Goods goods =  goodsService.findOne(id);
		Integer user_id = user.getId();
		if (goods != null){
			order.setGoods(goods);
		}else{
			System.out.println("goods is null!");
		}
	
		
		order.setSale_id(goods.getUser().getId());
		
		goods.setStatus(1);//已購買
		goodsService.save(goods);
		
		int randomNum = new Random().nextInt(100);
		String order_num = user_id+goods_id.substring(2, goods_id.length()-1)+randomNum;
	
		order.setBuyer_id(user_id);
		order.setOrder_num(order_num);
		order.setStatus(1);//确认付款
		order.setAmount(amount);
		order.setPrice(price*amount);
		goodsAmountChange(amount, goods);
		moneyChange(price*amount, request);
		
		return orderService.save(order);
		
	}
	
	public User moneyChange(float f,HttpServletRequest  request) {
		HttpSession session = request.getSession(true);
		Integer uid = (Integer) session.getAttribute("uid");
		
		User user =userService.findOne(uid);
		user.setMoney((user.getMoney()-f));
		return userService.save(user);
	}


	public Goods goodsAmountChange(int amount, Goods goods) {
		goods.setGoods_count(goods.getGoods_count()-amount);
		return goodsService.save(goods);
	}


	@RequestMapping(value="goods/{id}", method=RequestMethod.GET)
	public Goods findOne(
			@PathVariable int id){
		return goodsService.findOne(id);
	}
	

	@RequestMapping(value="goods/sort/{sortType}", method=RequestMethod.GET)
	public Page<Goods>  sort(
			@PathVariable String sortType
		){
		return sort(sortType, 0);
	}
	
	@RequestMapping(value="goods/sort/{sortType}/{page}", method=RequestMethod.GET)
	public Page<Goods> sort(
			@PathVariable String sortType,
			@PathVariable int page ){
		return goodsService.sortList(sortType, page);
	}
	
	
	
	@RequestMapping(value="goods/{id}/likes", method = RequestMethod.POST)
	public Integer changeLikes(
			@PathVariable  int id,
			@RequestParam  boolean likes,
			HttpServletRequest request){
		User user = getCurrentUser(request);
		
		Goods goods = goodsService.findOne(id);
		
		if(likes){
			likesService.addLike(user, goods);
			downService.removeDown(user, goods);
		}else{
			likesService.removeLike(user, goods);
		}
		return likesService.likeCount(id);
	}
	
	@RequestMapping(value="goods/{id}/likes", method = RequestMethod.GET)
	public int countLikes(
			@PathVariable  int id,
			HttpServletRequest request){
		User user = getCurrentUser(request);
		
		return likesService.likeCount(id);
	}
	
	@RequestMapping(value="goods/{id}/isLiked", method = RequestMethod.GET)
	public boolean checkLikes(
			@PathVariable  int id,
			HttpServletRequest request){
			User user= getCurrentUser(request);
			Goods goods  = goodsService.findOne(id);
		return likesService.checkLikesExit(user.getId(), goods.getId());
		
	}

	
	@RequestMapping(value="goods/{id}/downs", method = RequestMethod.POST)
	public Integer changeDowns(
			@PathVariable  int id,
			@RequestParam  boolean downs,
			HttpServletRequest request){
		User user = getCurrentUser(request);
		
		Goods goods = goodsService.findOne(id);
		
		if(downs){
			downService.addDown(user, goods);
			likesService.removeLike(user, goods);
		}else{
			downService.removeDown(user, goods);
		}
		return downService.downsCount(id);
	}
	
	@RequestMapping(value="goods/{id}/downs", method = RequestMethod.GET)
	public int countDowns(
			@PathVariable  int id,
			HttpServletRequest request){
		User user = getCurrentUser(request);
		
		return downService.downsCount(id);
	}
	
	@RequestMapping(value="goods/{id}/isDowned", method = RequestMethod.GET)
	public boolean checkDowns(
			@PathVariable  int id,
			HttpServletRequest request){
			User user= getCurrentUser(request);
			Goods goods  = goodsService.findOne(id);
		return downService.checkDownsExit(user.getId(), goods.getId());
		
	}
	

	private User getCurrentUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Integer uid = (Integer) session.getAttribute("uid");
		User user = userService.findOne(uid);
		return user;
	}
	
	
	@RequestMapping(value="goods/{id}/getDownNum", method = RequestMethod.GET)
	public int getDownNum(
			@PathVariable  int id){
		return downService.downsCount(id);
		
	}
	
	@RequestMapping(value="goods/{id}/getLikeNum", method = RequestMethod.GET)
	public int getLikeNum(
			@PathVariable  int id){
		return likesService.likeCount(id);
		
	}
	
	

}
