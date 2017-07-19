package com.github.liaojiacan.concurrent.synchronizationtools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * CyclicBarrier 等待条件满足 "一起执行"
 *
 * Created by liaojiacan on 2017/7/19.
 */
public class TestCyclicBarrier {


    public final static Integer THREAD_NUM = 10;

    public static void main(String[] args) {
        //he command to execute when the barrier is tripped
        CyclicBarrier cb = new CyclicBarrier(THREAD_NUM, new Runnable() {
            @Override
            public void run() {
                // 在满足条件后 子线程执行前执行
                System.out.println("barrier is tripped！");
            }
        });

        for(int i=0;i<10;i++){

            new Thread(()->{
                System.out.println("Worker is waiting");
                try {
                    cb.await();
                    System.out.println(Thread.currentThread().getName()+"  Working");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }


    }


}
