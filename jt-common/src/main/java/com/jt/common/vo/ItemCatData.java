package com.jt.common.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 前台获取类目json串
 * @author zain
 * 16/11/06
 */
public class ItemCatData {

	//序列化成json数据为u
	@JsonProperty("u")
	private String url;
	
	@JsonProperty("n")
	private String name;
	
	@JsonProperty("i")
	private List<?> items;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<?> getItems() {
		return items;
	}

	public void setItems(List<?> items) {
		this.items = items;
	}
}
