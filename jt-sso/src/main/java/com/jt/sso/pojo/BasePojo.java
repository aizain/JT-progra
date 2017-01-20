package com.jt.sso.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 父实体类
 * 设置通用属性
 * 
 * @author zain
 * 16/10/05
 */
public class BasePojo implements Serializable {
	
	private Date created; //创建时间
	private Date updated; //修改时间
	
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
}
