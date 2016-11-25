package com.jt.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.service.ContentService;

/**
 * 内容管理
 * @author zain
 * 16/11/25
 */

@RequestMapping("/content/query")
@Controller
public class ContentController {
    
    @Autowired
    private ContentService contentService;
    
    // 内容列表
    @RequestMapping("/list")
    @ResponseBody
    public EasyUIResult list(Long categoryId, Integer page, Integer rows) {
        return contentService.queryListByCategoryId(categoryId, page, rows);
    }
}
