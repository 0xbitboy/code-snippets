package com.github.liaojiacan.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by liaojiacan on 2017/5/8.
 */
public class JedisZsetTest {

    public static void main(String[] args) {


        //1.连接redis
        Jedis jedis = new Jedis("localhost");

        //查看服务是否运行
        System.out.println("Server is running: "+jedis.ping());

        //清空数据
        jedis.del("myzset");

        //添加元素
        jedis.zadd("myzset",1,"1");
        jedis.zadd("myzset",2,"2");
        jedis.zadd("myzset",3,"3");
        jedis.zadd("myzset",4,"4");
        // 元素个数
        System.out.println(jedis.zcard("myzset"));
        //整个集合
        System.out.println(jedis.zrange("myzset", 0, -1));


    }
}
