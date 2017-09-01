package com.github.liaojiacan.counter;

import com.alibaba.fastjson.JSON;
import com.github.liaojiacan.agg.AggManager;
import com.github.liaojiacan.config.AppConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
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
public class CounterTest {

    private final static String LIVE_ROOM_PREFIX="live_room:";


    //假设有1000个房间
    private final static  int ROOM_NUM = 20;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private AggManager aggManager;

    @Before
    public void init(){
        aggManager = new AggManager(redisTemplate);
    }

    @Test
    public void createData(){

        for(int i=1;i<=ROOM_NUM;i++){

            int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        System.out.println(finalI);
                        //UUID.randomUUID().toString() 为 交易id，
                        aggManager.counter(getLiveRoomXBigGiftNumKey(finalI +""), UUID.randomUUID().toString());
                        aggManager.counter(getLiveRoomXGoldCoinNumKey(finalI +""), UUID.randomUUID().toString()+"@"+new
                        Random(1000).nextLong());
                        aggManager.counter(getLiveRoomXOtherGiftNumKey(finalI +""), UUID.randomUUID().toString());
                        aggManager.counter(getLiveRoomXCommentNumKey(finalI +""), UUID.randomUUID().toString());
                       // redisTemplate.opsForValue().increment(getLiveRoomShareNumKey(finalI+""),1);
                        redisTemplate.opsForValue().increment(getLiveRoomMemberNumKey(finalI+""),1);
                        aggManager.counter(getLiveRoomXPraiseNumKey(finalI +""), UUID.randomUUID().toString());
                        //uuid 为 用户id
                        aggManager.counter(getLiveRoomXMemberNumKey(finalI +""), UUID.randomUUID().toString());

                        try {
                            long sleep = new Random(1000).nextLong();
                            Thread.sleep(sleep<=0?1000:sleep);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }).start();


        }

        while (true){

        }


    }



    @Test
    public void setTop(){

        redisTemplate.opsForZSet().add("zset12","ID1",1);
        redisTemplate.opsForZSet().add("zset12","ID2",5);
        redisTemplate.opsForZSet().add("zset12","ID3",3);


//        Set<String> set = redisTemplate.opsForZSet().reverseRange("zset12", 0, -1);
        Set<String> set = redisTemplate.opsForZSet().reverseRange("zset112", 0, -1);

        Double score = redisTemplate.opsForZSet().score("zset12", "ID3");
        System.out.println(JSON.toJSONString(score));
    }


    @Test
    public  void getLiveRoomXBigGiftNumTest(){

        System.out.println(getLiveRoomXBigGiftNum("2", TimeUnit.MINUTES.toMillis(5)));
    }


    private   Long getLiveRoomXBigGiftNum(String roomId,long period){

        return  aggManager.zCount(getLiveRoomXBigGiftNumKey(roomId),period);

    }


    private String getLiveRoomXBigGiftNumKey(String roomId){
        return LIVE_ROOM_PREFIX+roomId+":x_big_gift_num";
    }

    private String getLiveRoomXGoldCoinNumKey(String roomId){
        return LIVE_ROOM_PREFIX+roomId+":x_gold_coin_num";
    }

    private String getLiveRoomXOtherGiftNumKey(String roomId){
        return LIVE_ROOM_PREFIX+roomId+":x_other_gift_num";
    }

    private String getLiveRoomXCommentNumKey(String roomId){
        return LIVE_ROOM_PREFIX+roomId+":x_comment_num";
    }

    private String getLiveRoomShareNumKey(String roomId){
        return LIVE_ROOM_PREFIX+roomId+":share_num";
    }

    private String getLiveRoomXPraiseNumKey(String roomId){
        return LIVE_ROOM_PREFIX+roomId+":x_praise_num";
    }

    private String getLiveRoomXMemberNumKey(String roomId){
        return LIVE_ROOM_PREFIX+roomId+":x_member_num";
    }

    private String getLiveRoomMemberNumKey(String roomId){
        return LIVE_ROOM_PREFIX+roomId+":member";
    }







}
