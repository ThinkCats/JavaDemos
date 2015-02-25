package com.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wanglei on 15-2-25.
 */
public class RedisMain {
   private Jedis jedis;

    @Before
    public void setUp(){
        jedis=new Jedis("localhost",6379);
    }

    @Test
    public void testString(){
        //append a string
        jedis.append("foo"," world");
        System.out.println(jedis.get("foo"));

        jedis.mset("bar","nice work","test","testString");
        System.out.println(jedis.get("bar"));
        System.out.println(jedis.get("test"));
    }

    @Test
    public void testMap(){
        Map<String,String > map=new HashMap<String, String>();
        map.put("name","wang");
        map.put("age","22");
        map.put("email","624435@qq.com");
        jedis.hmset("wang", map);
        List<String> list=jedis.hmget("wang","name","age","email");
        for (String s:list){
            System.out.println(s);
        }

        //del a map key
        jedis.hdel("wanglei","name","age","email");
    }

}
