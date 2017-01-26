package com.jt.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jt.common.service.RedisService;

/**
 * 处理消息队列
 * @author zain
 * 17/01/24
 */
@Component // 组件
public class RabbitService {
    
    @Autowired
    private RedisService redisService;
    
    /**
     * 更新缓存
     * 方法名要和配置文件中的名称一致
     * @param itemId
     */
    public void update(long itemId) {
        redisService.del(itemId + "");
    }
}
