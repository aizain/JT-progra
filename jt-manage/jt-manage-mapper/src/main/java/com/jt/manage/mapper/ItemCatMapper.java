package com.jt.manage.mapper;

import java.util.List;

import com.jt.manage.mapper.base.mapper.SysMapper;
import com.jt.manage.pojo.ItemCat;

public interface ItemCatMapper extends SysMapper<ItemCat> {
	public List<ItemCat> queryItemCat(Long parentId);
}
