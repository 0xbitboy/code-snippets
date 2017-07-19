package com.github.liaojiacan.concurrent.synchronizationtools;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch 等待所有的事件发生
 * Created by liaojiacan on 2017/7/19.
 */
public class TestCountDownLatch {







    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(10);
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
                        System.out.println(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    latch.countDown();
                }
            }).start();
        }


        try {
            latch.await();
            System.out.println("All thread finish");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
