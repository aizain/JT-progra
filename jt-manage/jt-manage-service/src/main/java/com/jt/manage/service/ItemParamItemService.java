package com.jt.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.vo.SysResult;
import com.jt.manage.mapper.ItemParamItemMapper;
import com.jt.manage.pojo.ItemParamItem;

/**
 * 商品规格参数
 * 
 * @author zain
 * 16/10/16
 */
@Service
public class ItemParamItemService {
    @Autowired
    private ItemParamItemMapper itemParamItemMapper;

    /**
     * 查询商品规格参数
     * 
     * @param itemId
     * @return
     */
    public SysResult queryItemParamItem(Long itemId) {
        ItemParamItem itemParamItem = new ItemParamItem();
        itemParamItem.setItemId(itemId);
        List<ItemParamItem> itemParamItemList = itemParamItemMapper.select(itemParamItem);
        
        if(null != itemParamItemList && itemParamItemList.size() > 0) {
            return SysResult.ok(itemParamItemList.get(0));
        }
        //用一个新用法，返回错误状态
        return SysResult.build(201, "没找到商品的规格参数！");
    }
}
