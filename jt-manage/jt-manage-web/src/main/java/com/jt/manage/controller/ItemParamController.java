package com.jt.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.service.ItemParamService;

/**
 * 商品规格模版
 * 
 * @author zain
 * 16/10/16
 */
@RequestMapping("/item/param")
@Controller
public class ItemParamController {
    
    @Autowired
    private ItemParamService itemParamService;
    
    /**
     * 查询模版列表
     * 
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public EasyUIResult list(Integer page, Integer rows) {
        return itemParamService.queryList(page, rows);
    }
    
    /**
     * 查询商品模版的分类
     * 按商品分类id进行查询，结果封装到data中
     * 
     * @param id
     * @return
     */
    @RequestMapping("/query/itemcatid/{id}")
    @ResponseBody
    public SysResult queryItemCatById(@PathVariable Long id) {
        return itemParamService.queryItemCat(id);
    }
    
   
    /**
     * 规格参数保存
     * 同时使用RESTFul和ajax
     * 
     * @param itemCatId
     * @param paramData
     * @return
     */
    @RequestMapping("/save/{cid}")
    @ResponseBody
    public SysResult save(@PathVariable("cid") Long itemCatId, String paramData) {
         return itemParamService.save(itemCatId, paramData);
    }
    
    //删除
    @RequestMapping("/delete")
    @ResponseBody
    public SysResult delete(Long[] ids) {
        return itemParamService.delete(ids);
    }
    
}
