package com.github.liaojiacan.pagation;

import com.alibaba.fastjson.JSON;
import com.github.liaojiacan.agg.AggManager;
import com.github.liaojiacan.config.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by liaojiacan on 2017/8/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(classes = {AppConfig.class})
public class PagationTest {



    @Autowired
    private StringRedisTemplate redisTemplate;



    @Test
    public void createData(){

        for(int i=1;i<=40;i++){
            redisTemplate.opsForZSet().add("page", String.valueOf(i),i);
        }

    }

    @Test
    public void pagation(){
        int size = 21;
        Set<String> set = redisTemplate.opsForZSet().reverseRange("page", 0, size-1);
        System.out.println(JSON.toJSONString(set));
        int pos = set.size()-1-1;
        System.out.println(pos);
       set = redisTemplate.opsForZSet().reverseRange("page", pos+1, pos+size);
        System.out.println(set);
        pos = pos+set.size();
        set = redisTemplate.opsForZSet().reverseRange("page", pos+1, pos+size);
        System.out.println(set);


    }



}
