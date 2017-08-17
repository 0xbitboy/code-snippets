package com.github.liaojiacan.agg;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Collections;
import java.util.Set;

/**
 * redis 做一段时间 计数聚合
 * Created by liaojiacan on 2017/8/17.
 */
public class AggManager {


    private RedisTemplate<String,String> redisTemplate;
    private ZSetOperations<String,String> zSetOperations;



    public AggManager(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.zSetOperations = redisTemplate.opsForZSet();
    }


    public boolean counter(String counterName,long vaule){
        long ts  = System.currentTimeMillis();
        return zSetOperations.add(counterName,vaule+"",ts);
    }

    public long zCount(String counterName,long period){
        long now = System.currentTimeMillis();
        long tts = now - period;
        return  zSetOperations.count(counterName,tts+1,Long.MAX_VALUE);
    }

    public long zSum(String counterName,long period){
        long now = System.currentTimeMillis();
        long tts = now - period;
        Set<String> values = zSetOperations.rangeByScore(counterName, tts + 1, Long.MAX_VALUE);
        long sum  = 0;
        for(String value:values){
            sum+=Long.valueOf(value);
        }
        return  sum;
    }

    public void clear(String counterName,long expires){
        long now = System.currentTimeMillis();
        long tts = now - expires;
        zSetOperations.removeRangeByScore(counterName,0,tts);
    }





}
