package com.jt.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.ContentCategory;
import com.jt.manage.service.ContentCategoryService;

/**
 * 查询商品分类Controller
 * @author zain
 * 16/11/13
 */
@RequestMapping("/content/category")
@Controller
public class ContentCategoryController {
    
    @Autowired
    private ContentCategoryService contentCategoryService; 
    
    /**
     * 查询全部商品分类
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<ContentCategory> list(@RequestParam(defaultValue="0") Long id) {
        List<ContentCategory> list = contentCategoryService.queryList(id);
        return list;
    }
    
    /**
     * 新增一条数据
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    public SysResult create(ContentCategory contentCategory) {
        return contentCategoryService.create(contentCategory);
    }
    
    /**
     * 修改一条数据
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public SysResult update(ContentCategory contentCategory) {
        return contentCategoryService.update(contentCategory);
    }
    
    /**
     * 删除一条数据
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public SysResult delete(ContentCategory contentCategory) {
        return contentCategoryService.delete(contentCategory);
    }
}
