package com.github.liaojiacan.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by liaojiacan on 2017/5/8.
 */
public class JedisHashTest {

    public static void main(String[] args) {


        //1.连接redis
        Jedis jedis = new Jedis("localhost");

        //查看服务是否运行
        System.out.println("Server is running: "+jedis.ping());

        //清空数据
        jedis.del("myhash");

        //添加数据
        jedis.hset("myhash","name","liaojiacan");
        jedis.hset("myhash","nickname","灿");
        jedis.hset("myhash","qq","912536370");


        // 判断某个值是否存在
        System.out.println(jedis.hexists("myhash", "name"));

        // 获取所有的keys
        System.out.println(jedis.hkeys("myhash"));
        // 获取所有的values
        System.out.println(jedis.hvals("myhash"));


    }
}
