package com.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by wanglei on 15-2-25.
 */
public class TestRedisPool {
    @Test
    public void testPool(){
        Jedis jedis=RedisPool.getJedis();
        jedis.set("pool","success");
        System.out.println(jedis.get("pool"));
    }
}
