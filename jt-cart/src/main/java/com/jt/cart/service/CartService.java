package com.jt.cart.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.cart.mapper.CartMapper;
import com.jt.cart.pojo.Cart;
import com.jt.common.vo.SysResult;

/**
 * 购物车服务层
 * @author zain
 * 17/01/21
 */
@Service
public class CartService {
    @Autowired
    private CartMapper cartMapper;
    
    /**
     * 将某人的商品加入购物车
     * @param cart
     * @return
     */
    public SysResult save(Cart cart) {
        try {
            // 判断，这个货物是否存在在本购物车中
            Map<String, Long> mapParam = new HashMap<>();
            mapParam.put("userId", cart.getUserId());
            mapParam.put("itemId", cart.getItemId());
            Integer count = cartMapper.queryByUserIdItemId(mapParam);
            if(count > 0) {
                List<Cart> cartList = cartMapper.select(cart);
                if (null != null) {
                    Cart oldCar = cartList.get(0);
                    oldCar.setNum(oldCar.getNum()+1);
                    this.update(oldCar);
                }
                return SysResult.build(202, "该商品已经存在在购物车中！");
            } else {
                cart.setCreated(new Date());
                cart.setUpdated(cart.getCreated());
                cartMapper.insert(cart);
            }
            return SysResult.ok();
        } catch(Exception e) {
            return SysResult.build(201, "添加商品到购物车失败！");
        }
    }
    
    /**
     * 查询某个人的购物车数据
     * @param userId
     * @return
     */
    public SysResult query(long userId, int page, int rows) { 
        Cart param = new Cart();
        param.setUserId(userId);
        
        PageHelper.startPage(page, rows);
        List<Cart> cartList = cartMapper.select(param);
        PageInfo<Cart> info = new PageInfo<>(cartList);
        
        return SysResult.ok(info); // TODO
    }
    
    /**
     * 更新购物车货物
     * @param cart
     * @return
     */
    public SysResult update(Cart cart) {
        try {
            cartMapper.update(cart); 
            return SysResult.ok();
        } catch(Exception e) {
            return SysResult.build(201, "更新商品错误！" + cart.getItemId());
        }
    }

    /**
     * 删除某人的某个货物
     * @param cart
     * @return
     */
    public SysResult delete(Cart cart) {
        try {
            cartMapper.delete(cart);
            return SysResult.ok();
        } catch(Exception e) {
            return SysResult.build(201, "删除商品错误！" + cart.getItemId());
        }
    }
}
