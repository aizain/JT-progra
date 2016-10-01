package com.jt.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {
	
	//通用转向方法
	@RequestMapping("/{pageName}")
	public String index(@PathVariable("pageName") String pageName) {
		return pageName;
	}
}
