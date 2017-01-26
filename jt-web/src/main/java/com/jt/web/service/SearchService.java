package com.jt.web.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.service.HttpClientService;
import com.jt.common.spring.exetend.PropertyConfig;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Item;

/**
 * 前台搜索服务
 * @author zain
 * 17/01/26
 */
@Service
public class SearchService {
    
    @Autowired
    private HttpClientService httpClientService;
    @PropertyConfig
    private String SEARCH_URL;
    @PropertyConfig
    private String SEARCH_URL_COUNT;
    
    /**
     * 搜索
     * @param keyWords
     * @return
     * @throws IOException 
     * @throws ParseException 
     */
    public List<Item> search(String keyWords, int page, int rows) throws ParseException, IOException {
        String url = SEARCH_URL;
        Map<String, String> params = new HashMap<>();
        params.put("keyWords", keyWords);
        params.put("page", page + "");
        params.put("rows", rows + "");
        
        String jsonData = httpClientService.doPost(url, params);
        List<Item> itemList = (List<Item>) SysResult.formatToList(jsonData, Item.class).getData();
        return itemList;
    }
        
}
