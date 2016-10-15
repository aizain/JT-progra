package com.jt.manage.mapper;

import java.util.List;

import com.jt.manage.mapper.base.mapper.SysMapper;
import com.jt.manage.pojo.Item;

/**
 * 商品
 * 映射mapper
 * 
 * @author zain
 * 16/10/15
 */
public interface ItemMapper extends SysMapper<Item> {
    
    /**
     * 查询商品列表
     * 全部
     * 
     * @return
     */
    public List<Item> queryItem();
}
