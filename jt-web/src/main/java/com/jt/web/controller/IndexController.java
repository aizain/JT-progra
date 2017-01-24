package com.jt.web.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.util.CookieUtils;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
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
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    @RequestMapping("/index.html")
    public String index(HttpServletRequest request, Model model) throws JsonParseException, JsonMappingException, IOException {
        // 存放大广告位
        String result = service.queryIndexAD1();
        model.addAttribute("indexAD1", result);
        
        // 存放当前用户信息,从cookie中拿出ticket，通过ticket去redis中获取当前user对象
        String cookieName = "JT_TICKET";
        String ticket = CookieUtils.getCookieValue(request, cookieName);
        if (null !=ticket) {
            String jsonUser = service.getUserByTicket(ticket);
            // 获取当前用户对象
            if (StringUtils.isNotEmpty(jsonUser)) {
                // TODO formatToPojo
                User curUser = (User) SysResult.formatToPojo(jsonUser, User.class).getData();
                model.addAttribute("curUser", curUser);
            }
        }
        return "index";
    }
    
}
