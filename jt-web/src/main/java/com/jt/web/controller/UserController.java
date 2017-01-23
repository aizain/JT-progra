package com.jt.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.util.CookieUtils;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
import com.jt.web.service.UserService;

/**
 * 登录操作
 * @author zain
 * 17/01/20
 */
@RequestMapping("/user")
@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    /**
     * 转向登录页面
     * @return
     */
    @RequestMapping("/login.html")
    public String login() {
        return "login";
    }
    
    /**
     * 执行登录
     * @param username
     * @param password
     * @return
     * @throws IOException 
     * @throws ParseException 
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public SysResult doLogin(HttpServletRequest request, HttpServletResponse response, 
            String username, String password) throws ParseException, IOException {
        // 验证登录，调用SSO登录方法
        String cookieName = "JT_TICKET";
        String ticket = userService.doLogin(username, password);
        if(ticket == null) {
            return SysResult.build(201, "此用户不存在！", false);
        }
        // 存入cookie中,设置过期时间为1天
        CookieUtils.setCookie(request, response, cookieName, ticket, 60*60*24*1000);
        return SysResult.ok();
    }
    
    /**
     * 转向注册页面
     * @return
     */
    @RequestMapping("/register")
    public String register() {
        return "register";
    }
    
    /**
     * 注册
     * @param user
     * @return
     * @throws IOException 
     * @throws ParseException 
     */
    @RequestMapping("/doRegister")
    @ResponseBody
    public SysResult doRegister(User user) throws ParseException, IOException {
        return userService.doRegister(user);
    }
    
    
}
