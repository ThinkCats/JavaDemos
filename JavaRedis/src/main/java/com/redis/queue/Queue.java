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
            while (true) {
                /*List<String> all = jedis.lrange("queue", 0, -1);
                System.out.println("size:" + all.size());
                for (String s : all) {
                    System.out.println(s);
                }*/

                List<String> list = jedis.brpop(0,"queue","queue");
                System.out.println("brpop size:" + list.size());
                for (String s:list){
                    System.out.println("get value:"+s);
                }
            }
    }

    @Test
    public void lpp(){
        System.out.println( jedis.rpoplpush("queue", "queue"));
    }

    @Test
    public void addQueueSort(){
        jedis.lpush("queue","I","am","wanglei");
        jedis.lpush("queue","how","are","your");
    }

    @Test
    public void addQueueNoSort(){
        T1 t1=new T1();
        T2 t2=new T2();
        T3 t3=new T3();
        t1.start();
        t2.start();
        t3.start();
    }

    class T1 extends Thread{
        @Override
        public void run() {
            jedis.lpush("queue","c","d","e");
            System.out.println("push 2");
        }
    }

     class T2 extends Thread{
        @Override
        public void run() {
            jedis.lpush("queue","f","g");
            System.out.println("push 3");
        }
    }

         class T3 extends Thread{
        @Override
        public void run() {
            jedis.lpush("queue","h","j");
            System.out.println("push 4");
        }
    }
}
