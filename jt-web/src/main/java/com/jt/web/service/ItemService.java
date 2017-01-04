package com.jt.web.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.spring.exetend.PropertyConfig;
import com.jt.web.pojo.Item;
import com.jt.web.pojo.ItemDesc;

/**
 * 商品前台服务层
 * 调用后台controller
 * @author zain
 * 17/01/04
 */
@Service
public class ItemService {
    
    private static ObjectMapper MAPPER = new ObjectMapper();
    @Autowired
    private HttpClientService httpClientService;
    @PropertyConfig
    private String MANAGE_URL;
    
    /**
     * 获取商品
     * @param id
     * @return
     */
    public Item get(Long id) {
        // 利用httpClient访问后台
        String url = MANAGE_URL + "/web/item/" + id;
        String jsonData = "";
        
        try {
            jsonData = httpClientService.doGet(url);
            return MAPPER.readValue(jsonData, Item.class);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取商品描述
     * @param id
     * @return
     */
    public ItemDesc getItemDesc(Long id) {
        // 利用httpClient访问后台
        String url = MANAGE_URL + "/web/item/desc/" + id;
        String jsonData = "";
        
        try {
            jsonData = httpClientService.doGet(url);
            return MAPPER.readValue(jsonData, ItemDesc.class);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
