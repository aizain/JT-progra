package com.jt.web.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.spring.exetend.PropertyConfig;

/**
 * 登录相关服务
 * @author zain
 * 17/01/20
 */
@Service
public class UserService {
    @Autowired
    private HttpClientService httpClientService;
    @PropertyConfig
    private String SSO_URL;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    /**
     * 执行登录
     * @param username
     * @param password
     * @throws IOException 
     * @throws ParseException 
     */
    public String doLogin(String username, String password) throws ParseException, IOException {
        // 访问单点登录方法
        String url = SSO_URL + "/user/login";
        Map<String, String> params = new HashMap<>();
        params.put("u", username);
        params.put("p", password);
        String jsonData = httpClientService.doPost(url, params); // 返回json串
        if (jsonData == null) {
            return null;
        }
        JsonNode jsonNode = MAPPER.readTree(jsonData); // 将json串转换为jsonNode对象
        JsonNode data = jsonNode.get("data"); // 从jsonNode对象中获取data属性对象
        String ticket = data.asText(); // 转换为字符串
        return ticket;
    }
}
