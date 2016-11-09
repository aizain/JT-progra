package jedis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class TestJedis {

    @Test
    public void jedis() {
        // 连接到redis server: ip port
        // 测试set、get方法
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.flushDB();
        jedis.set("jt-manage", "123"); // set值
        String result = jedis.get("jt-manage");
        System.out.println(result);
        jedis.close();
    }
    
    @Test
    public void jedisPool() {
        // 配置
        JedisPoolConfig config = new JedisPoolConfig(); // 配置对象
        config.setMaxTotal(50); // 最大连接数
        
        // 池化
        JedisPool pool = new JedisPool(config, "127.0.0.1", 6379);
        Jedis jedis = pool.getResource(); // 从池中获取实例
        
        jedis.flushDB();
        jedis.set("jt-manage", "123"); // set值
        String result = jedis.get("jt-manage");
        System.out.println(result);
        
        pool.returnResource(jedis); // 将资源还回池子
    }
    
    /**
     *  分片，
     *  将有个服务对开发人员是透明的，
     *  开发人员根本不关心数据在哪个server中，
     *  只要能设置、能取到就OK
     */
    @Test 
    public void shard() {
        // 配置
        JedisPoolConfig config = new JedisPoolConfig(); // 配置对象
        config.setMaxTotal(50); // 最大连接数
        
        List<JedisShardInfo> list = new ArrayList<>();
        JedisShardInfo info = new JedisShardInfo("127.0.0.1", 6379);
        JedisShardInfo info2 = new JedisShardInfo("127.0.0.1", 6380);
        list.add(info);
        list.add(info2);
        
        // 分片
        ShardedJedisPool sPool = new ShardedJedisPool(config, list);
        ShardedJedis jedis = sPool.getResource();
        
        jedis.set("t33", "aaaa"); // set值
        String result = jedis.get("t33");
        System.out.println(result);
        
        // 100值，会平均分配到服务器中，大致平分
        for(int i=0; i<100; i++) {
            jedis.set("t_" + i, String.valueOf(i)); // set值
        }
        
        for(int i=0; i<100; i++) {
            System.out.println(jedis.getShardInfo("t_" + i));
        }
        
        sPool.returnResource(jedis); // 将资源还回池子
    }
    
}
