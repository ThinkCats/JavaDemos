package com.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wanglei on 15-2-25.
 */
public class JedisMethod {

    private Jedis jedis;

    @Before
    public void setUp(){
        jedis=new Jedis("localhost",6379);
    }

    @Test
    // about 1.811 seconds
    public void test1Normal(){
        long start=System.currentTimeMillis();
        for (int i=0;i< 100000;i++){
            String result=jedis.set("n"+i,"n"+i);
        }
        long end=System.currentTimeMillis();
        System.out.println("Simple SET:"+((end-start)/1000.0)+" seconds");
        jedis.disconnect();
    }

    @Test
    //about 0.765 seconds
    public void test2Trans(){
        Transaction tx=jedis.multi();
        long start=System.currentTimeMillis();
        for (int i=0;i<100000;i++){
            tx.set("t"+i,"t"+i);
        }
        List<Object> result=tx.exec();
         long end=System.currentTimeMillis();
        System.out.println("Transaction SET:"+((end-start)/1000.0)+" seconds");
        jedis.disconnect();
    }

   @Test
   //about 0.699 seconds
    public void test3Pipe(){
       Pipeline pipeline=jedis.pipelined();
       long start=System.currentTimeMillis();
       for(int i=0;i<100000;i++){
           pipeline.set("p"+i,"p"+i);
       }
       List<Object> result=pipeline.syncAndReturnAll();
        long end=System.currentTimeMillis();
        System.out.println("PIPE SET:"+((end-start)/1000.0)+" seconds");
        jedis.disconnect();
   }

    @Test
    // THIS WILL CAUSE StackOverflowError
    public void test4PipeTrans(){
        long start=System.currentTimeMillis();
        Pipeline pipeline=jedis.pipelined();
        pipeline.multi();
        for (int i=0;i<100000;i++){
            pipeline.set(""+i,""+i);
        }
        pipeline.exec();
        List<Object> result=pipeline.syncAndReturnAll();
         long end=System.currentTimeMillis();
        System.out.println("PIPE TRANS SET:"+((end-start)/1000.0)+" seconds");
        jedis.disconnect();
    }

    @Test
    //about 2.549 seconds ,but this can not add 100000 data ,just about  49000
    //ready to find why....
    public void test5shardNormal(){
        List<JedisShardInfo> shardInfos= Arrays.asList(new JedisShardInfo("localhost",6379),new JedisShardInfo("localhost",6380));
        ShardedJedis sharding=new ShardedJedis(shardInfos);
        long start=System.currentTimeMillis();
        for (int i=0;i<100000;i++){
            String result=sharding.set("s"+i,"s"+i);
        }
        long end=System.currentTimeMillis();
        System.out.println("Simple Sharding SET:" + ((end - start) / 1000.0) + " seconds");
        sharding.disconnect();
    }

    @Test
    //about 1.259 seconds
    //but this can not add 1000000 datas
    public void test6shardPipe(){
        List<JedisShardInfo> shardInfos= Arrays.asList(new JedisShardInfo("localhost",6379),new JedisShardInfo("localhost",6380));
        ShardedJedis sharding=new ShardedJedis(shardInfos);
        ShardedJedisPipeline pipeline=sharding.pipelined();
        long start=System.currentTimeMillis();
        for (int i=0;i<100000;i++){
            pipeline.set("sp"+i,"sp"+i);
        }
        List<Object> result=pipeline.syncAndReturnAll();
        long end=System.currentTimeMillis();
        System.out.println("Shard Pipe SET:"+((end-start)/1000.0)+" seconds");
        sharding.disconnect();
    }
}
