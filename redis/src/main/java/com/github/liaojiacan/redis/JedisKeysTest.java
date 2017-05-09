package com.github.liaojiacan.redis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * Created by liaojiacan on 2017/5/8.
 */
public class JedisKeysTest {

    public static void main(String[] args) {


        //1.连接redis
        Jedis jedis = new Jedis("localhost");

        //查看服务是否运行
        System.out.println("Server is running: "+jedis.ping());

        Set<String> keys = jedis.keys("*");

        for(String key:keys){
            System.out.println("key:"+key);
        }

    }
}
