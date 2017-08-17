package com.github.liaojiacan.redis;

import com.alibaba.fastjson.JSON;
import com.github.liaojiacan.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

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

        for(int i=0;i<10;i++){
            ops.add("count1",i,i);
        }

    }


    @Test
    public void zRange(){

        Set values = redisTemplate.opsForZSet().rangeByScore("count1", 0, 6);

        System.out.println(JSON.toJSONString(values));

    }


}
