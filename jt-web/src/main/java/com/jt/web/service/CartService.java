package com.jt.web.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.spring.exetend.PropertyConfig;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;
import com.jt.web.pojo.Item;
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
    @PropertyConfig
    private String MANAGE_URL;
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
        SysResult result = SysResult.formatToPojo(json, User.class);
        User user = (User) result.getData();
        return user;
    }
    
    /**
     * 保存货物信息
     * @param cart
     * @throws IOException 
     * @throws ParseException 
     * @throws URISyntaxException 
     */
    public void saveItem(Cart cart) throws ParseException, IOException, URISyntaxException {
        
        // 从后台系统中，查询某个item的信息
        String url = MANAGE_URL + "/web/item/" + cart.getItemId();
        String jsonItem = httpClientService.doGet(url);
        Item item = MAPPER.readValue(jsonItem, Item.class);
        
        url = CART_URL + "/cart/save";
        Map<String, String> map = new HashMap<>();
        map.put("userId", cart.getUserId() + "");
        map.put("itemId", cart.getItemId() + "");
        map.put("itemTitle", cart.getItemTitle());
        if (null != item.getImages() && item.getImages().length > 0) {
            String[] images = item.getImage().split(",");
            map.put("itemImage", images[0]); // 取第一张图片
        }
        map.put("itemPrice", item.getPrice() + "");
        map.put("num", "1");
        httpClientService.doPost(url, map, "UTF-8");
    }

    /**
     * 删除商品
     * @param id
     * @param itemId
     * @throws IOException 
     * @throws ParseException 
     */
    public void deleteItem(long userId, long itemId) throws ParseException, IOException {
        String url = CART_URL + "/cart/delete/" + userId + "/" + itemId;
        httpClientService.doPost(url);
    }

    /**
     * 更新商品数量
     * @param id
     * @param itemId
     * @param num
     * @throws IOException 
     * @throws ParseException 
     */
    public void updateItem(long userId, long itemId, int num) throws ParseException, IOException {
        String url = CART_URL + "/cart/update/" + userId + "/" + itemId + "/" + num;
        httpClientService.doPost(url);
    }
    
}
