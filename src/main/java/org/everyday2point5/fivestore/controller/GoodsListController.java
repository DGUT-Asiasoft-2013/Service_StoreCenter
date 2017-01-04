package org.everyday2point5.fivestore.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.everyday2point5.fivestore.entity.Goods;
import org.everyday2point5.fivestore.entity.GoodsList;
import org.everyday2point5.fivestore.entity.GoodsListWithItem;
import org.everyday2point5.fivestore.entity.InboxList;
import org.everyday2point5.fivestore.entity.User;
import org.everyday2point5.fivestore.service.IGoodsListService;
import org.everyday2point5.fivestore.service.IGoodsService;
import org.everyday2point5.fivestore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class GoodsListController {
	@Autowired
	IUserService userService;
	@Autowired
	IGoodsListService goodsListService;

	
	@RequestMapping(value="/addGoodsList",method=RequestMethod.POST)
	public GoodsList addGoodsList(
			@RequestParam  String name,
			@RequestParam 	String text,
			@RequestParam String item,
			MultipartFile goods_list_image,
			HttpServletRequest request){
		GoodsList goodsList=new GoodsList();
		goodsList.setGoods_list_name(name);
		goodsList.setGoods_list_text(text);
		goodsList.setGoods_list_item(item);
		goodsList.setCreateTime(new Date());
		HttpSession session = request.getSession();
		Integer uid = (Integer) session.getAttribute("uid");
		User user =userService.findOne(uid);
		
		goodsList.setSeller_id(user.getId());
		goodsList.setSeller_name(user.getUser_name());

		if (goods_list_image != null) {
			String realpath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
			try {
				FileUtils.copyInputStreamToFile(goods_list_image.getInputStream(),
						new File(realpath, name + "-" + user.getId() + "-" + "goodslist.png"));
				goodsList.setGoods_list_image("upload/" + name + "-" + user.getId() + "-" + "goodslist.png");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return goodsListService.save(goodsList);
	}
	
	@RequestMapping(value="/allGoodsList",method=RequestMethod.GET)
	public Page<GoodsList> allGoodsList( HttpServletRequest request){
		return getallGoodsList(0, request);

	}
	@RequestMapping(value="/allGoodsList/{page}",method=RequestMethod.GET)
	public Page<GoodsList> getallGoodsList(
			@PathVariable int page,
			HttpServletRequest request
			){

		
		return goodsListService.findAllGoodsList(page);

	}

	
	@RequestMapping(value="/sellerGoodsList",method=RequestMethod.GET)
	public Page<GoodsList> sellerGoodsList( HttpServletRequest request){
		return getSellerGoodsList(0, request);

	}
	@RequestMapping(value="/sellerGoodsList/{page}",method=RequestMethod.GET)
	public Page<GoodsList> getSellerGoodsList(
			@PathVariable int page,
			HttpServletRequest request
			){
		HttpSession session = request.getSession(true);
		Integer uid = (Integer) session.getAttribute("uid");
		User user =userService.findOne(uid);
		
		return goodsListService.findSellerGoodsList(user.getUser_name(),page);

	}
	
	@RequestMapping(value="/GoodsList/{id}",method=RequestMethod.GET)
	public GoodsListWithItem GoodsListItem( 
			@PathVariable int id,
			HttpServletRequest request){
		return getGoodsListItem(id,0, request);

	}
	
	@RequestMapping(value="/GoodsList/{id}/{page}",method=RequestMethod.GET)
	public GoodsListWithItem getGoodsListItem( 
			@PathVariable int id,
			@PathVariable int page,
			HttpServletRequest request){
		GoodsListWithItem goodsListWithItem = new GoodsListWithItem();
		GoodsList goodsList;
		goodsList=goodsListService.findGoodsListById(id);
		goodsListWithItem.setCreateTime(goodsList.getCreateTime());
		goodsListWithItem.setGoods_list_image(goodsList.getGoods_list_image());
		goodsListWithItem.setGoods_list_name(goodsList.getGoods_list_name());
		goodsListWithItem.setGoods_list_text(goodsList.getGoods_list_text());
		goodsListWithItem.setId(id);
		goodsListWithItem.setSeller_id(goodsList.getSeller_id());
		goodsListWithItem.setSeller_name(goodsList.getSeller_name());

		
		String s[]=goodsList.getGoods_list_item().split("-");
		int[] ids=new int[s.length];
		for(int i=0;i<s.length;i++){			
			ids[i]=Integer.parseInt(s[i]);
		}
		List<Goods>goodsItem=goodsListService.findGoodsInList(ids,page);
		goodsListWithItem.setGoods_list_item(goodsItem);
		return goodsListWithItem;

	}
}
