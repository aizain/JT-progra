package com.jt.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 封装前台获取类目json串
 * @author zain
 * 16/11/06
 */
public class ItemCatResult {
	
    @JsonProperty("data") // json序列化时指定字段名称
	private List<ItemCatData> itemCatDatas = new ArrayList<>();

	public List<ItemCatData> getItemCatDatas() {
		return itemCatDatas;
	}

	public void setItemCatDatas(List<ItemCatData> itemCatDatas) {
		this.itemCatDatas = itemCatDatas;
	}
}
