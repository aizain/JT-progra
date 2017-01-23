package com.jt.web.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.spring.exetend.PropertyConfig;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;
import com.jt.web.pojo.User;

/**
 * 购物车相关
 * @author zain
 * 17/01/23
 */
@Service
public class CartService {
    @Autowired
    private HttpClientService httpClientService;
    @PropertyConfig
    private String CART_URL;
    @PropertyConfig
    private String SSO_URL;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    /**
     * 查询购物车货物
     * @param userId
     * @return
     * @throws IOException 
     * @throws URISyntaxException 
     * @throws ClientProtocolException 
     */
    public List<Cart> queryCartByUserId(long userId) throws ClientProtocolException, URISyntaxException, IOException {
        String url = CART_URL + "/cart/query/" + userId;
        String jsonData = httpClientService.doGet(url);
        JsonNode jsonNode = MAPPER.readTree(jsonData);
        JsonNode jsonNodeData = jsonNode.get("data");
        JsonNode jsonCartList = jsonNodeData.get("list");
        List<Cart> cartList = null;
        if(jsonCartList.isArray() && jsonCartList.size() > 0) {
            cartList = MAPPER.readValue(jsonCartList.traverse(), 
                    MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class));
        }
        return cartList;
    }

    /**
     * 去SSO获取用户信息
     * @param ticket
     * @return
     * @throws IOException 
     * @throws URISyntaxException 
     * @throws ClientProtocolException 
     */
    public User queryUserByticket(String ticket) throws ClientProtocolException, URISyntaxException, IOException {
        String url = SSO_URL + "/user/query/" + ticket;
        String json = httpClientService.doGet(url);
        SysResult result = SysResult.formatToPojo(json, SysResult.class);
        User user = (User) result.getData();
        return user;
    }
    
}
