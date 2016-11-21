package com.jt.manage.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CMS
 * 内容分类表
 * @author zain
 * 16/11/13
 */
@Table(name="tb_content_category")
public class ContentCategory extends BasePojo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Long parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;
    private Boolean isParent;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getParentId() {
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
    
    // 支持easyUI数据
    public String getText() {
        return this.getName();
    }
    public String getState() {
        return getIsParent() ? "closed" : "open";
    }
    
}
