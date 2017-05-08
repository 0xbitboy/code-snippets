package com.github.liaojiacan.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by liaojiacan on 2017/5/8.
 */
public class JedisStringTest {

    public static void main(String[] args) {


        //1.连接redis
        Jedis jedis = new Jedis("localhost");

        //查看服务是否运行
        System.out.println("Server is running: "+jedis.ping());

        //2.添加字符串（String）数据 NX:不存在这个key的时候才设置，EX：单位是秒
        jedis.set("name","liaojiacan","NX","EX",10);

        System.out.println("key:name ,value:"+jedis.get("name"));


    }
}
