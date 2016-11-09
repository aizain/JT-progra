package com.jt.manage.controller.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.ItemCatResult;
import com.jt.manage.service.ItemCatService;

/**
 * 前台系统访问
 * 
 * @author zain
 * 16/11/06
 */
@RequestMapping("/web/itemcat")
@Controller
public class WebItemCatController {
    
    @Autowired
    private ItemCatService itemCatService;
    
    //查询所有的正常的分类
    @RequestMapping("/all")
    @ResponseBody
    public ItemCatResult queryItemCat() {
        return itemCatService.queryWebAll();
    }
}
