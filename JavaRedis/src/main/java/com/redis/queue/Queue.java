package com.redis.queue;

import com.redis.RedisPool;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by wang on 15-3-4.
 */
public class Queue {

    private Jedis jedis;

    @Before
    public void setUp(){
        jedis= RedisPool.getJedis();
    }

    @Test
    public void  testQueue(){
        while (true){
            if (jedis.brpop(100000,"queue").size() > 0){
                List<String> list=jedis.brpop(100000,"queue");
                for (String value:list){
                    System.out.println("get key queue value:"+value);
                }
            }else {
                System.out.println("there is no queue");
            }
        }
    }

    @Test
    public void addQueue(){
        jedis.lpush("queue","wang","ttt");
    }
}
