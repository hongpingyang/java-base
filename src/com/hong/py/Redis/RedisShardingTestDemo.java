package com.hong.py.Redis;

import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件描述
 *
 * @ProductName: HONGPY
 * @ProjectName: 01-java-base
 * @Package: com.hong.py.Redis
 * @Description: note
 * @Author: hongpy21691
 * @CreateDate: 2019/10/14 11:16
 * @UpdateUser: hongpy21691
 * @UpdateDate: 2019/10/14 11:16
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>
 * Copyright © 2019 hongpy Technologies Inc. All Rights Reserved
 **/
public class RedisShardingTestDemo {

    public static void main(String[] args) {

        ShardedJedis shardedJedis = getShardedJedisPool().getResource();


        shardedJedis.set("president", "Obama");
        String president = shardedJedis.get("president");
        System.out.println(president);
        shardedJedis.del("president");

    }

    public static Jedis getJedis() {
        Jedis jedis = new Jedis(); //默认是localhost，端口6379
        return jedis;
    }


    public static JedisPoolConfig getConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }

    public static ShardedJedisPool getShardedJedisPool() {

        JedisPoolConfig JedisPoolConfig=getConfig();

        List<JedisShardInfo> list = new ArrayList<>();

        JedisShardInfo jedisShardInfo1 = new JedisShardInfo(
                "192.168.0.1",
                6379,
                30,
                 "test1");
        list.add(jedisShardInfo1);
        JedisShardInfo jedisShardInfo2 = new JedisShardInfo(
                "192.168.0.2",
                6379,
                30,
                "test2");
        list.add(jedisShardInfo2);
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(JedisPoolConfig,list);
        return shardedJedisPool;
    }


}
