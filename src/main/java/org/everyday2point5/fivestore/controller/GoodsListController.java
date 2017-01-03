package org.everyday2point5.fivestore.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.everyday2point5.fivestore.entity.GoodsList;
import org.everyday2point5.fivestore.entity.User;
import org.everyday2point5.fivestore.service.IGoodsListService;
import org.everyday2point5.fivestore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
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
		System.out.println(name+"+"+text+"+"+item+"+");
		GoodsList goodsList=new GoodsList();
		goodsList.setGoods_list_name(name);
		goodsList.setGoods_list_text(text);
		goodsList.setGoods_list_item(item);
		goodsList.setCreateTime(new Date());
		HttpSession session = request.getSession();
		Integer uid = (Integer) session.getAttribute("uid");
		User user =userService.findOne(uid);
		
		goodsList.setUser(user);

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
		
}
