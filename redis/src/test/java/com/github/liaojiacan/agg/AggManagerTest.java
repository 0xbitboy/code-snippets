package com.github.liaojiacan.agg;


import com.github.liaojiacan.config.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.concurrent.TimeUnit;

/**
 * Created by liaojiacan on 2017/8/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(classes = {AppConfig.class})
public class AggManagerTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private AggManager aggManager;

    @Before
    public void init(){
        aggManager = new AggManager(redisTemplate);
    }

    @Test
    public void counter() throws Exception {

        for(int i=0;i<1000;i++){
            aggManager.counter("count1",1);
            Thread.sleep(100);
        }


    }

    @Test
    public void zCount() throws Exception {

//        System.out.println(aggManager.zCount("count1", TimeUnit.MINUTES.toMillis(10)));
    }

    public void zSum() throws Exception {

    }

}