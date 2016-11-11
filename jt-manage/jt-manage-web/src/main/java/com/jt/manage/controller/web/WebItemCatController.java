package com.jt.manage.controller.web;


import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.RedisService;
import com.jt.common.vo.ItemCatResult;
import com.jt.manage.service.ItemCatService;

/**
 * 前台系统访问
 * 
 * 临时访问：http://localhost:8081/web/itemcat/all?callback=aaa
 * 
 * @author zain
 * 16/11/06
 */
@RequestMapping("/web/itemcat")
@Controller
public class WebItemCatController {
    
    @Autowired
    private ItemCatService itemCatService;
    @Autowired
    private RedisService redisService;
    private static ObjectMapper MAPPER = new ObjectMapper();
    
    private static final String ITEM_WEB_ALL = "ITEM_WEB_ALL"; // 分类key
    
    //查询所有的正常的分类
    @RequestMapping("/all")
    @ResponseBody
    public ItemCatResult queryItemCat() {
        ItemCatResult result = null;
        
        // 读取redis中的数据，必须在业务层之前
        String json = redisService.get(ITEM_WEB_ALL);
        if(StringUtils.isNoneEmpty(json)) {
            try {
                // 将json串转换为java对象
                result = MAPPER.readValue(json, ItemCatResult.class);
            } catch (Exception e) {
                e.printStackTrace();
            } 
            return result;
        }
        
        result = itemCatService.queryWebAll();
        
        // 设置redis缓存 redisService
        // 如何将java对象转换成json串
        try {
            json = MAPPER.writeValueAsString(result);
            redisService.set(ITEM_WEB_ALL, json); // 将结果集转换为json串
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        return result;
    }
}
