package com.jt.web.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.web.pojo.Cart;
import com.jt.web.pojo.User;
import com.jt.web.service.CartService;
import com.jt.web.threadLocal.UserThreadLocal;

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
//        // 读取当前User对象，到cookie中拿ticket，通过ticket去redis中查询(SSO)
//        String cookieName = "JT_TICKET";
//        String ticket = CookieUtils.getCookieValue(request, cookieName);
//        User user = cartService.queryUserByticket(ticket);
        
        // 从本地线程中拿出User
        User user = UserThreadLocal.get();
        if (null == user) { // 如果用户没有登录，就转向登录页面
            return "login";
        }
        
        /*
         *  将用户选择的商品加入到购物车中
         *  1）查询用户加入的商品是否已经在购物车中，
         *  如果存在，数量增加1
         *  如果不存在，这个商品的信息就添加到购物车中
         */
        Cart param = new Cart();
        param.setUserId(user.getId());
        param.setItemId(itemId);
        cartService.saveItem(param);
        
        
        model.addAttribute("curUser", user);
        // 为页面组织列表数据 cartList
        model.addAttribute("cartList", cartService.queryCartByUserId(user.getId()));
        return "cart";
    }
    
    /**
     * 从购物车中删除商品 /cart/delete/545252.html
     * @param itemId
     * @param model
     * @return
     * @throws IOException 
     * @throws ParseException 
     * @throws URISyntaxException 
     */
    @RequestMapping("/delete/{itemId}")
    public String delete(@PathVariable long itemId,  Model model) throws ParseException, IOException, URISyntaxException {
        // 从本地线程中拿出User
        User user = UserThreadLocal.get();
        if (null == user) { // 如果用户没有登录，就转向登录页面
            return "login";
        }
        cartService.deleteItem(user.getId(), itemId);
        // 为页面组织列表数据 cartList
        model.addAttribute("cartList", cartService.queryCartByUserId(user.getId()));
        return "cart";
    }
    
    /**
     *  更新商品数量
     * @param itemId
     * @param num
     * @return
     * @throws ParseException
     * @throws IOException
     */
    @RequestMapping("/update/num/{itemId}/{num}")
    public String update(@PathVariable long itemId, @PathVariable int num) throws ParseException, IOException {
     // 从本地线程中拿出User
        User user = UserThreadLocal.get();
        if (null == user) { // 如果用户没有登录，就转向登录页面
            return "login";
        }
        cartService.updateItem(user.getId(), itemId, num);
        return null;
    }
}
