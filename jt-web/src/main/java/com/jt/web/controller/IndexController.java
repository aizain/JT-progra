package com.jt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前台主页
 * 
 * @author zain
 * 16/10/22
 */
@Controller
public class IndexController {
    
    @RequestMapping("/index.html")
    public String index() {
        return "index";
    }
}
