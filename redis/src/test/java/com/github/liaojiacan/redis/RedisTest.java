package com.github.liaojiacan.redis;

import com.alibaba.fastjson.JSON;
import com.github.liaojiacan.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Arrays;
import java.util.Set;

/**
 * Created by liaojiacan on 2017/8/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(classes = {AppConfig.class})
public class RedisTest {


    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void zSet(){
        ZSetOperations ops = redisTemplate.opsForZSet();
        for(int i=0;i<100000;i++){
            ops.add("test100000",i,i);
        }

    }


    @Test
    public void zRange(){

        Set<ZSetOperations.TypedTuple> values = redisTemplate.opsForZSet().reverseRangeByScoreWithScores("test100000", 0, 100000);

        System.out.println(JSON.toJSONString(values));

    }

    @Test
    public void bitmap(){

        redisTemplate.opsForValue().setBit("bitmap:test1",31,true);
        redisTemplate.opsForValue().setBit("bitmap:test1",1,true);
        redisTemplate.opsForValue().setBit("bitmap:test1",366,true);

        boolean flag= redisTemplate.opsForValue().getBit("bitmap:test1",1);

        byte[] bytes = redisTemplate.getConnectionFactory().getConnection().get("bitmap:test1".getBytes());

        System.out.println(toBinary(bytes));

    }

    String toBinary( byte[] bytes )
    {
        StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
        for( int i = 0; i < Byte.SIZE * bytes.length; i++ )
            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
        return sb.toString();
    }



}
