package com.jt.web.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.common.service.HttpClientService;
import com.jt.common.spring.exetend.PropertyConfig;
import com.jt.common.vo.EasyUIResult;
import com.jt.web.pojo.Content;

@Service
public class IndexService {
    
    @Autowired
    HttpClientService httpClientService;
    
    @PropertyConfig
    private String MANAGE_URL; // 后台域名地址
    @PropertyConfig
    private String INDEX_AD1; // 大广告位访问链接
    
    public String queryIndexAD1() {
        String json = "";
        StringBuilder builder = null;
        // 构建大广告位的数据，然后写入model对象中，最终传递到页面，页面通过jstl进行解析
        /*
         * 步骤：
         * 1）从数据库中获取数据，通过httpClient获取json串，将这个串转换为java对象
         * 2）将数据拼接为json串
         * 3）存入model对象中
         */
        try {
            
            String url = MANAGE_URL + INDEX_AD1;
            json = httpClientService.doGet(url);
            if(StringUtils.isEmpty(json)) {
                return "";
            }
            // 返回值就是EasyUIResult结构的json串
            EasyUIResult result = EasyUIResult.formatToList(json, Content.class);
            List<Content> contentList = (List<Content>) result.getRows();
            System.err.println(contentList);
            
            builder = new StringBuilder();
            builder.append("[");
            Content content = null;
            for(int i=0; i<contentList.size(); i++) {
                content = contentList.get(i);
                builder.append("{");
                builder.append("width: 730,").append("height: 454");
                builder.append("hred:\"").append(content.getUrl()).append("\",");
                builder.append("alt: \"\",");
                builder.append("src:\"").append(content.getPic()).append("\",");
                builder.append("ext1: \"\",");
                builder.append("index: \"").append(i + 1).append("\",");
                builder.append("widthB: 510,").append("heightB: 454");
                builder.append("srcB:\"").append(content.getPic2()).append("\",");
                builder.append("},");
            }
            builder.delete(builder.length() - 1, builder.length());
            builder.append("]");
        } catch (Exception e) {
            e.printStackTrace();
        } 
        
        return builder.toString();
    }
}
