package com.jt.manage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.service.ItemService;

/**
 * 商品操作
 * controller 层
 * 
 * @author zain
 * 16/10/07
 */
@Controller
@RequestMapping("/item")
public class ItemController extends BaseController {
    
    @Autowired
    private ItemService itemService;
    
    //商品新增方法
    @RequestMapping("/save")
    @ResponseBody
    public SysResult save(Item item, String desc, String itemParams) {
        itemService.save(item, desc, itemParams);
        return SysResult.ok();
    }
    
    //商品列表
    @RequestMapping("/query")
    @ResponseBody
    public EasyUIResult query(Integer page, Integer rows) {
        
        List<Item> items = itemService.queryList(page, rows); //总的结果集
        PageInfo<Item> pageInfo = new PageInfo<Item>(items); //Page是list子集
        
        return new EasyUIResult(pageInfo.getTotal(), items);
    }
    
    //商品修改
    @RequestMapping("/update")
    @ResponseBody
    public SysResult update(Item item, String desc, Long itemParamId, String itemParams) {
        return itemService.update(item, desc,itemParamId, itemParams);
    }
    
    //批量删除
    @RequestMapping("/delete")
    @ResponseBody
    public SysResult delete(Long[] ids) {
        return itemService.delete(ids);
    }
    
    //查询某个商品的描述信息
    @RequestMapping("/query/item/desc/{itemId}")
    @ResponseBody
    public SysResult queryItemDesc(@PathVariable Long itemId) {
        ItemDesc itemDesc = itemService.queryItemDesc(itemId);
        return SysResult.ok(itemDesc);
    }
    
}
