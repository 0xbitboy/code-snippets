package com.github.liaojiacan.limitedSale;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * redis 解决超买问题，先把库存放在 队列中 ，用户抢购只能按顺序来获取。获取到则抢购成功。
 * Created by liaojiacan on 2017/7/23.
 */
public class LimitedSaleManager {

    private  Jedis jedis;

    LimitedSaleManager(Jedis jedis){
        this.jedis=jedis;
    }

    /**
     * 初始化 抢购数据
     * @param goodId
     * @param num
     */
    public void initGood(int goodId,int num){
        for(int i =0;i<num;i++)
            jedis.lpush("good:"+goodId, String.valueOf(1));
    }


    public boolean tryRequest(int goodId,long uid){
        Object obj = jedis.lpop("good:"+goodId);
        if(obj!=null){
            jedis.lpush("order:"+goodId,uid+"");
            return  true;
        }
        return  false;
    }


    public List<String> listUids(int goodId){
        return jedis.lrange("order:"+goodId,0,jedis.llen("order:"+goodId));
    }




    public static void main(String[] args) {

        Jedis jedis = new Jedis("localhost", 6379);

        LimitedSaleManager limitedSaleManager = new LimitedSaleManager(jedis);

        limitedSaleManager.initGood(1,10);

        ExecutorService executor = Executors.newFixedThreadPool(1000);

        final boolean[] finish = {false};

        while (!finish[0]){

            executor.submit(()->{
                boolean success = limitedSaleManager.tryRequest(1, Thread.currentThread().getId());
                if(success)
                     System.out.println(Thread.currentThread().getName()+":success!");

                finish[0] =false;
            });
        }


    }

}
