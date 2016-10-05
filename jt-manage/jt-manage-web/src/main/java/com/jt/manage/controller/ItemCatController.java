package com.jt.manage.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manage.pojo.ItemCat;
import com.jt.manage.service.ItemCatService;

@RequestMapping("/item/cat")
@Controller
public class ItemCatController extends BaseController {
	@Autowired
	private ItemCatService itemCatService;
	
	//页面
	@RequestMapping("/list")
	@ResponseBody
	public List<ItemCat> queryItemCat(@RequestParam(defaultValue="0") Long id) {
		List<ItemCat> ItemCats = itemCatService.queryItemCat(id);
		return ItemCats;
	}
	
}
