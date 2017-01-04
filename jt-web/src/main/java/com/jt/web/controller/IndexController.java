package com.jt.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.web.service.IndexService;

/**
 * 前台主页
 * 
 * @author zain
 * 16/10/22
 */
@Controller
public class IndexController {
    
    @Autowired
    private IndexService service;
    
    @RequestMapping("/index.html")
    public String index(Model model) {
        String result = service.queryIndexAD1();
        System.err.println(result);
        model.addAttribute("indexAD1", result);
        return "index";
    }
    
}
