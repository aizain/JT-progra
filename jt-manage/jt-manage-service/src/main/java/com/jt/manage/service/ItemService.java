package com.jt.manage.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.jt.common.service.RedisService;
import com.jt.common.vo.SysResult;
import com.jt.manage.mapper.ItemDescMapper;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.mapper.ItemParamItemMapper;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.pojo.ItemParamItem;

/**
 * 商品service层
 * 
 * @author zain
 * 16/10/07
 */
@Service
public class ItemService {
    
    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;
    @Autowired
    private ItemParamItemMapper itemParamItemMapper;
    @Autowired
    private RedisService redisService;
    @Autowired // spring 注入MQ模版
    private RabbitTemplate rabbitTemplate;
    
    /**
     * 保存一条数据
     * 状态、创建/修改时间有默认值
     * 
     * @param item
     * @param desc
     * @param itemParams 
     */
    public void save(Item item, String desc, String itemParams) {
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
        
        //保存商品规格模版参数
        ItemParamItem itemParamItem = new ItemParamItem();
        itemParamItem.setItemId(item.getId());
        itemParamItem.setParamData(itemParams);
        itemParamItem.setUpdated(item.getUpdated());
        itemParamItem.setCreated(item.getCreated());
        itemParamItemMapper.insert(itemParamItem);
        
        // 将商品信息放入redis
        redisService.set("item_" + item.getId(), item.getId() + "");
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
     * @param itemParamId 
     * @param itemParams 
     * @return
     */
    public SysResult update(Item item, String desc, Long itemParamId, String itemParams) {
        itemMapper.updateByPrimaryKeySelective(item);
        
        ItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(item.getId());
        itemDesc.setItemDesc(desc);
        itemDesc.setUpdated(new Date());
        itemDescMapper.updateByPrimaryKeySelective(itemDesc);
        
        ItemParamItem itemParamItem = itemParamItemMapper.selectByPrimaryKey(itemParamId);
        if(itemParamItem != null) {
            itemParamItem.setParamData(itemParams);
            itemParamItem.setUpdated(itemDesc.getUpdated());
            itemParamItemMapper.updateByPrimaryKeySelective(itemParamItem);
        }
        
        // 修改成功后，设置发送商品修改的消息，消息应该存放什么内容？
        // 将消息队列的内容设置为商品的id
        String routingKey = "update"; // 路由key
        // 发送消息到MQ服务器端
        rabbitTemplate.convertAndSend(routingKey, item.getId());
        
        return SysResult.ok();
    }

    /**
     * 级联删除
     * 
     * @param ids
     */
    public SysResult delete(Long[] ids) {
        //删除规格信息
        for(Long itemId : ids) { // 此处因为简单做所以加循环，不建议这么做，可以自己写映射zain 16/10/16
            ItemParamItem itemParamItem = new ItemParamItem();
            itemParamItem.setItemId(itemId);
            itemParamItemMapper.delete(itemParamItem);
        }
        
        //删除商品描述
        itemDescMapper.deleteByIDS(ids);
        //删除多个商品
        itemMapper.deleteByIDS(ids);
        
        // TODO 删除图片，从商品中查询到它的所有的图片的链接 此处不再补全 zain 16/10/16
        
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

    /**
     * 获取商品对象
     * 根据id
     * @param id
     * @return
     */
    public Item get(Long id) {
        Item item = null;
        
        // 先从缓存中，读取信息，如果缓存中不存在，则继续执行
        String jsonData = redisService.get(id + "");
        if (StringUtils.isNotEmpty(jsonData)) {
            try {
                // 将json串转换为java对象
                item = MAPPER.readValue(jsonData, Item.class);
                return item;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        // 从数据库进行读取
        item = itemMapper.selectByPrimaryKey(id);
        // 将商品信息进行缓存
        // 将商品对象转换为json串
        try {
            redisService.set(id + "", MAPPER.writeValueAsString(item));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        return item;
    }
    
}
