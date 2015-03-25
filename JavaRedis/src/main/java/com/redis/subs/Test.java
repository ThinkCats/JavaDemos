package com.redis.subs;

import com.redis.RedisPool;
import org.junit.Before;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.io.*;

/**
 * Created by wang on 15-3-5.
 */
public class Test {

    private Jedis jedis;

    @Before
    public void setUp(){
        jedis= RedisPool.getJedis();
    }

    @org.junit.Test
    public void testPub(){
        Bean bean=new Bean();
        bean.setName("wang");
        ByteArrayOutputStream  baos=new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos=new ObjectOutputStream(baos);
            oos.writeObject(bean);
            String msg1=baos.toString("ISO-8859-1");
            jedis.publish("foo",msg1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testSub(){
        JedisPubSub  jedisPubSub=new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                try {
                    ByteArrayInputStream bais=new ByteArrayInputStream(message.getBytes("ISO-8859-1"));
                    try {
                        ObjectInputStream ois=new ObjectInputStream(bais);
                        try {
                            Bean bean= (Bean) ois.readObject();
                            System.out.println("begin subn");
                            System.out.println(bean.getName());
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        };

        System.out.println("sub...");
        jedis.subscribe(jedisPubSub,"foo");
    }
}
