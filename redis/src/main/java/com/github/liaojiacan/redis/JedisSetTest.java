package com.github.liaojiacan.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Created by liaojiacan on 2017/5/8.
 */
public class JedisSetTest {

    public static void main(String[] args) {


        //1.连接redis
        Jedis jedis = new Jedis("localhost");

        //查看服务是否运行
        System.out.println("Server is running: "+jedis.ping());

        //清空数据
        jedis.del("myset");

        //添加元素
        jedis.sadd("myset","1");
        jedis.sadd("myset","2");
        jedis.sadd("myset","3");
        jedis.sadd("myset","3");

        //判断某个值是否存在
        System.out.println("exits ->"+jedis.sismember("myset","3"));

        //删除指定元素
        System.out.println("del->"+jedis.srem("myset","3"));

        //出栈
        System.out.println("pop->"+jedis.spop("myset"));
        System.out.println("now->"+jedis.smembers("myset"));


    }
}
