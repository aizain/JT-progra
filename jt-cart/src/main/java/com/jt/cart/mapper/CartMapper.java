package com.jt.cart.mapper;

import java.util.Map;

import com.jt.cart.mapper.base.mapper.SysMapper;
import com.jt.cart.pojo.Cart;

/**
 * 购物车获取相关
 * @author zain
 * 17/01/21
 */
public interface CartMapper extends SysMapper<Cart> {
    /**
     * 查询货物根据用户id和货物id
     * @param map
     * @return
     */
    public Integer queryByUserIdItemId(Map<String, Long> map);
    
    /**
     * 更新购物车信息
     * @param cart
     */
    public void update(Cart cart);
    
    /**
     * 删除
     * @param cart
     */
    public void deleteItem(Cart cart);
}
