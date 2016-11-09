package com.jt.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 处理redis服务
 * @author zain
 * 16/11/09
 */
@Service
public class RedisService {
    
    // 有的工程需要，有的工程不需要。设置required=false，有就注入，没有就不注入
    @Autowired(required=false)
    private ShardedJedisPool shardedJedisPool;
    
    private <T> T execute(Function<ShardedJedis, T> fucntion) {
        ShardedJedis shardedJedis = null;
        try {
            // 从连接池中获取到jedis分片对象
            shardedJedis = shardedJedisPool.getResource();
            return fucntion.execute(shardedJedis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(null != shardedJedis) {
                // 关闭，检测链接是否有效，有效则放回到连接池中，无效则重置状态
                shardedJedis.close();
            }
        }
        return null;
    }
    
    /**
     * 保存数据到redis中
     * @param key
     * @param value
     * @return
     */
    public String set(final String key, final String value) {
        return this.execute(new Function<ShardedJedis, String>() {
            @Override
            public String execute(ShardedJedis shardedJedis) {
                return shardedJedis.set(key, value);
            }
        });
    }
    
    /**
     * 保存数据到redis中，生存时间单位是：秒
     * @param key
     * @param value
     * @param seconds
     * @return
     */
    public String set(final String key, final String value, final Integer seconds) {
        return this.execute(new Function<ShardedJedis, String>() {
            @Override
            public String execute(ShardedJedis shardedJedis) {
                String result = shardedJedis.set(key, value);
                shardedJedis.expire(key, seconds);
                return result;
            }
        });
    }
    
    /**
     * 从redis中获取数据
     * @param key
     * @return
     */
    public String get(final String key) {
        return this.execute(new Function<ShardedJedis, String>() {
            @Override
            public String execute(ShardedJedis shardedJedis) {
                return shardedJedis.get(key);
            }
        });
    }
    
    /**
     * 设置key生存时间,单位是：秒
     * @param key
     * @param seconds
     * @return
     */
    public Long expire(final String key, final Integer seconds) {
        return this.execute(new Function<ShardedJedis, Long>() {
            @Override
            public Long execute(ShardedJedis shardedJedis) {
                return shardedJedis.expire(key, seconds);
            }
        });
    }
    
    /**
     * 从redis中删除数据
     * @param key
     * @return
     */
    public Long del(final String key) {
        return this.execute(new Function<ShardedJedis, Long>() {
            @Override
            public Long execute(ShardedJedis shardedJedis) {
                return shardedJedis.del(key);
            }
        });
    }
}
