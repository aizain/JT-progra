package com.jt.manage.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.service.ItemService;

/**
 * 为前台系统访问准备的
 * @author zain
 * 17/01/03
 */
@RequestMapping("/web")
@Controller
public class WebItemController {
    
    @Autowired
    private ItemService itemService;
    
    /**
     * 为前台系统返回商品对象
     * @param id
     * @return
     */
    @RequestMapping("/item/{id}")
    @ResponseBody
    public Item get(@PathVariable Long id) {
        return itemService.get(id);
    }
    
    /**
     * 为前台系统返回商品描述
     * @param id
     * @return
     */
    @RequestMapping("/item/desc/{id}")
    @ResponseBody
    public ItemDesc getItemDesc(@PathVariable Long id) {
        return itemService.queryItemDesc(id);
    }
}
