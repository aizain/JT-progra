package com.jt.web.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.common.util.CookieUtils;
import com.jt.web.pojo.User;
import com.jt.web.service.CartService;

/**
 * 购物车相关
 * @author zain
 * 17/01/23
 */
@RequestMapping("/cart")
@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    
    /**
     * 加入购物车 http://www.jt.com/cart/add/562345.html
     * @param itemId
     * @return
     * @throws IOException 
     * @throws URISyntaxException 
     * @throws ClientProtocolException 
     */
    @RequestMapping("/add/{itemId}")
    public String add(@PathVariable long itemId, Model model, HttpServletRequest request) throws ClientProtocolException, URISyntaxException, IOException {
        // 读取当前User对象，到cookie中拿ticket，通过ticket去redis中查询(SSO)
        String cookieName = "JT_TICKET";
        String ticket = CookieUtils.getCookieValue(request, cookieName);
        User user = cartService.queryUserByticket(ticket);
        model.addAttribute("curUser", user);
        // 为页面组织列表数据 cartList
        model.addAttribute("cartList", cartService.queryCartByUserId(1L));
        return "cart";
    }
}
