package com.jt.sso.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.RedisService;
import com.jt.common.vo.SysResult;
import com.jt.sso.pojo.User;
import com.jt.sso.service.UserService;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * sso检查登录
 * @author zain
 * 17/01/08
 */
@RequestMapping("/user")
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static Map<Integer, String> PARAM_TYPE;
    
    // 利用静态块初始化参数
    static {
        PARAM_TYPE = new HashMap<>();
        PARAM_TYPE.put(1, "username");
        PARAM_TYPE.put(2, "phone");
        PARAM_TYPE.put(3, "email");
    }
    
    /**
     * 检查注册的类型对应的值是否存在
     * @param param
     * @param type
     * @return
     */
    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public SysResult check(@PathVariable("param") String param, 
                           @PathVariable("type") Integer type) {
        Map<String, String> map = new HashMap<>();
        map.put("name", PARAM_TYPE.get(type));
        map.put("val", param);
        
        return userService.check(map);
    }
    
    /**
     * 注册用户
     * @param user
     * @return
     */
    @RequestMapping(value="/register", method=RequestMethod.POST)
    @ResponseBody
    public SysResult register(User user) {
        return userService.register(user);
    }
    
    /**
     * 用户进行登录
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="/login", method=RequestMethod.POST)
    @ResponseBody
    public SysResult login(@RequestParam("u") String username, 
                           @RequestParam("p") String password) {
        User _user = userService.login(username);
        String pagePassword = DigestUtils.md5Hex(password); // 利用commons工具类md5Hex
        if(pagePassword.equals(_user.getPassword())) {
            // 登录成功 ticket唯一性，同一个用户在不同时期登录它的值也不同
            String ticket = "JT_TICKET" + System.currentTimeMillis() + _user.getId(); 
            ticket = DigestUtils.md5Hex(ticket); // 加密，不好破解
            // 登录成功将当前的user对象放入redis缓存中，必须设置过期时间，一天
            try {
                // 将对象转换为字符串
                redisService.set(ticket, MAPPER.writeValueAsString(_user));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return SysResult.build(201, "Redis转换错误", false);
            } 
            return SysResult.ok(ticket);
        } else {
            return SysResult.build(201, "登录失败，请检查用户名、密码是否正确", false);
        }
    }
    
    /**
     * 通过ticket查询用户信息
     * @param ticket
     * @return
     */
    @RequestMapping(value="/query/{ticket}", method=RequestMethod.POST)
    @ResponseBody
    public SysResult queryByTicket(@PathVariable("ticket") String ticket) {
        // 从redis中获取用户信息
        String jsonUser = redisService.get(ticket);
        // 如果redis中不存在
        if(StringUtils.isEmpty(jsonUser)) {
            return null;
        }
        return SysResult.ok(jsonUser);
    }
}
