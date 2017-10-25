package com.github.liaojiacan.redis;

import com.alibaba.fastjson.JSON;
import com.github.liaojiacan.config.AppConfig;
import com.github.liaojiacan.geo.PeopleNearByDto;
import com.github.liaojiacan.geo.PeopleNearByService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;
import java.util.Set;

/**
 * Created by liaojiacan on 2017/8/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(listeners = { DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(classes = {AppConfig.class})
public class NearByGeoTest {


    @Autowired
    private PeopleNearByService peopleNearByService;


    @Test
    public void  initData(){

        peopleNearByService.recordCoord("嘉灿",113.288495,23.132132);
        peopleNearByService.recordCoord("国劲",113.44316,23.16803);
        peopleNearByService.recordCoord("宇韬",113.368639,23.152836);

    }

    @Test
    public void listNearBy(){

        List<PeopleNearByDto> list = peopleNearByService.findNearBy(113.288495, 23.132132, 50.0 * 1000);

        System.out.println(JSON.toJSONString(list));

    }

}
