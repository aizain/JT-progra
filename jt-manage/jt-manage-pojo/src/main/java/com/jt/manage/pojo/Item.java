package com.jt.manage.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品实体
 * 
 * @author zain
 * 16/10/05
 */

@Table(name="tb_item")
public class Item extends BasePojo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id; //商品id
    private String title; //商品表体
    @Column(name="sell_point")
    private String sellPoint; //商品卖点
    private Integer price; //价格 单位为：分
    private Integer num; //库存数量
    private String barcode; //条形码
    private String image; //图片 最多5张图片
    private Long cid; //所属分类（商品类别）
    private Integer status; //状态 默认值为1，可选值： 1正常，2下架，3删除
    
    
    @Override
    public String toString() {
        return "Item [id=" + id + ", title=" + title + ", sellPoint=" + sellPoint + ", price=" + price + ", num=" + num
                + ", barcode=" + barcode + ", image=" + image + ", cid=" + cid + ", status=" + status + "]";
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSellPoint() {
        return sellPoint;
    }
    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
    public String getBarcode() {
        return barcode;
    }
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public Long getCid() {
        return cid;
    }
    public void setCid(Long cid) {
        this.cid = cid;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
}
