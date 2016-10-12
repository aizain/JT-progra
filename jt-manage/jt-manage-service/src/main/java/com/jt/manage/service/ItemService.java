package com.jt.manage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.jt.common.vo.SysResult;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.pojo.Item;

/**
 * 商品service层
 * 
 * @author zain
 * 16/10/07
 */
@Service
public class ItemService {
    
    @Autowired
    private ItemMapper itemMapper;
    
    public void save(Item item) {
        //设置默认值：状态 默认值为1，可选值： 1正常，2下架，3删除
        item.setStatus(1);
        item.setUpdated(new Date());
        item.setCreated(new Date());
        itemMapper.insert(item);
        
        System.out.println(item);
    }

    public List<Item> queryList(Integer page, Integer rows) {
        //分页 执行拦截器，在sql执行之前嵌入分页参数
        PageHelper.startPage(page, rows); //只有本次查询有效，查询返回Page对象
        //List<Item> items = itemMapper.select(null); 
        List<Item> items = itemMapper.queryItem();
        return items;
    }

    public SysResult update(Item item) {
        itemMapper.updateByPrimaryKeySelective(item);
        return SysResult.ok();
    }
    
}
