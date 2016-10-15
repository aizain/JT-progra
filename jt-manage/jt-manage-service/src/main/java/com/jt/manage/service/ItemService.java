package com.jt.manage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.jt.common.vo.SysResult;
import com.jt.manage.mapper.ItemDescMapper;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;

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
    @Autowired
    private ItemDescMapper itemDescMapper;
    
    /**
     * 保存一条数据
     * 状态、创建/修改时间有默认值
     * 
     * @param item
     */
    public void save(Item item, String desc) {
        //设置默认值：状态 默认值为1，可选值： 1正常，2下架，3删除
        item.setStatus(1);
        item.setUpdated(new Date());
        item.setCreated(new Date());
        itemMapper.insert(item);
        //mybatis机制，依托mysql获取共执行完新增对象的ID，last_id
        //利用mysql函数，直接获取到刚插入的数据的主键，返回给对象
        System.out.println(item);
        
        //保存商品描述信息
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        itemDesc.setUpdated(item.getUpdated());
        itemDesc.setCreated(item.getCreated());
        itemDescMapper.insert(itemDesc);
    }

    /**
     * 查询列表
     * 分页
     * 
     * @param page 当前页面
     * @param rows 页面条目数限制
     * @return
     */
    public List<Item> queryList(Integer page, Integer rows) {
        //分页 执行拦截器，在sql执行之前嵌入分页参数
        PageHelper.startPage(page, rows); //只有本次查询有效，查询返回Page对象
        //List<Item> items = itemMapper.select(null); 
        List<Item> items = itemMapper.queryItem();
        return items;
    }

    /**
     * 修改一条数据
     * 根据主键
     * 根据不为null的字段
     * 
     * @param item
     * @param desc 
     * @return
     */
    public SysResult update(Item item, String desc) {
        itemMapper.updateByPrimaryKeySelective(item);
        
        ItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(item.getId());
        itemDesc.setItemDesc(desc);
        itemDescMapper.updateByPrimaryKeySelective(itemDesc);
        
        return SysResult.ok();
    }

    /**
     * 批量删除
     * 根据ids
     * 
     * @param ids
     */
    public SysResult delete(Long[] ids) {
        itemMapper.deleteByIDS(ids);
        return SysResult.ok();
    }

    /**
     * 查询商品描述
     * 根据id
     * 
     * @param itemId
     * @return
     */
    public ItemDesc queryItemDesc(Long itemId) {
        return itemDescMapper.selectByPrimaryKey(itemId);
    }
    
}
