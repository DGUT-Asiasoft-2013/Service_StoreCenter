
package org.everyday2point5.fivestore.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.everyday2point5.fivestore.entity.Goods;
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
public class APIController {
	@Autowired
	IUserService userService;
	
	@Autowired
	IGoodsService goodsService;
	

	@RequestMapping("/")
	public String index(){
		return "it works";
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
	public Page<Goods> getGoods(){
		return getGoods(0);
		
	}
	
	@RequestMapping(value="/goods/{page}",method=RequestMethod.GET)
	public Page<Goods> getGoods(
			@PathVariable int page){
		return goodsService.findAllGoods(0);
		
	}
	
	
}
