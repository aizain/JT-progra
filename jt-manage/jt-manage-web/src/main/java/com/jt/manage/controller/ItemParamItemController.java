package com.jt.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.manage.service.ItemParamItemService;

/**
 * 商品规格参数
 * 
 * @author zain
 * 16/10/16
 */
@RequestMapping("/item/param/item")
@Controller
public class ItemParamItemController {

    @Autowired
    private ItemParamItemService itemParamItemService;
    
    /**
     * 根据商品的id查询某个商品的规格参数
     * 
     * @param itemId
     * @return
     */
    @RequestMapping("/query/{itemId}")
    @ResponseBody
    public SysResult queryItemParamItem(@PathVariable Long itemId) {
        return itemParamItemService.queryItemParamItem(itemId);
    }
}
