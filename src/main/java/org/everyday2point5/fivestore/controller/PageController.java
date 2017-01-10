package org.everyday2point5.fivestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/page")
public class PageController {
	
	@RequestMapping("/goodsInfo")
	public String goodsInfo(){
		return "goodsInfo";
	}
}
