package com.jt.cart.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.cart.pojo.Cart;
import com.jt.cart.service.CartService;
import com.jt.common.vo.SysResult;

/**
 * 购物车货物相关
 * @author zain
 * 17/01/21
 */
@RequestMapping("/cart")
@Controller
public class CartController {
    @Autowired
    private CartService cartService;
    
    /**
     * 保存货物至购物车
     * @param cart
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public SysResult save(Cart cart) {
        return cartService.save(cart);
    }
    
    /**
     * 查询购物车商品
     * @param userId
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/query/{userId}")
    @ResponseBody
    public SysResult query(@PathVariable long userId, 
            @RequestParam(defaultValue="1") int page, 
            @RequestParam(defaultValue="20") int rows) {
        return cartService.query(userId, page, rows);
    }
    
    /**
     * 更新商品数量
     * @param cart
     * @return
     */
    @RequestMapping("/update/{userId}/{itemId}/{num}")
    @ResponseBody
    public SysResult update(@PathVariable long userId, @PathVariable long itemId, @PathVariable int num) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setItemId(itemId);
        cart.setNum(num);
        cart.setUpdated(new Date());
        return cartService.update(cart);
    }
    
    /**
     * 更新商品数量
     * @param cart
     * @return
     */
    @RequestMapping("/delete/{userId}/{itemId}")
    @ResponseBody
    public SysResult delete(@PathVariable long userId, @PathVariable long itemId) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setItemId(itemId);
        return cartService.delete(cart);
    }
}
