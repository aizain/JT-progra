package com.jt.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.pojo.ItemCat;

@Service
public class ItemCatService {
	
	@Autowired
	private ItemCatMapper itemCatMapper;
	
	public List<ItemCat> queryItemCat(Long parentId) {
		//return itemCatMapper.queryItemCat(parentId);
		ItemCat itemCat = new ItemCat();
		itemCat.setParentId(parentId);
		return itemCatMapper.select(itemCat);
	}
	
}
