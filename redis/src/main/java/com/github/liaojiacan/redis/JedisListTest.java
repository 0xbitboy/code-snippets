package com.github.liaojiacan.redis;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by liaojiacan on 2017/5/8.
 */
public class JedisListTest {

    public static void main(String[] args) {


        //1.连接redis
        Jedis jedis = new Jedis("localhost");

        //查看服务是否运行
        System.out.println("Server is running: "+jedis.ping());
        jedis.del("list");
        jedis.lpush("list","1");
        jedis.lpush("list","2");
        jedis.lpush("list","3");

        List<String>  list = jedis.lrange("list",0,jedis.llen("list"));

        for(int i=0; i<list.size(); i++) {
            System.out.println("Stored string in redis:: "+list.get(i));
        }

    }
}
