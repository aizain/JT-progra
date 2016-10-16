package com.jt.manage.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品规格模版
 * 
 * @author zain
 * 16/10/16
 */
@Table(name="tb_item_param")
public class ItemParam extends BasePojo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="item_cat_id")
    private Long itemCatId;
    //@Column(name="param_data")
    //测试是否会自动转换
    private String paramData;
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getItemCatId() {
        return itemCatId;
    }
    public void setItemCatId(Long itemCatId) {
        this.itemCatId = itemCatId;
    }
    public String getParamData() {
        return paramData;
    }
    public void setParamData(String paramData) {
        this.paramData = paramData;
    }
    
}
