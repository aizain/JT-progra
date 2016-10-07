package com.jt.manage.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品类别实体
 * 
 * @author zain
 * 16/10/05
 */
@Table(name="tb_item_cat") //表映射
public class ItemCat extends BasePojo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id; //商品类别id
	
	private String name; //商品类别名称
	
	@Column(name="parent_id")
	private Long parentId; //商品父类别id 自关联
	
	private Integer status; //商品类别状态 默认值为1 可选值: 1正常 2删除
	
	@Column(name="sort_order")
	private Integer sortOrder; //排序号
	
	// 数据库会自动转换 1(非0):true 0:false
	@Column(name="is_parent")
	private Boolean isParent; //是否为父节点
	
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	
	//为 easyui tree 准备数据 
	//节点名
	public String getText() {
		return this.getName();
	}
	//节点状态
	public String getState() {
		return getIsParent() ? "closed" : "open";
	}
	
	
}
