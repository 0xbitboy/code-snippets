package com.github.liaojiacan.coding.多线程循环打印;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 *  使用 ReentrantLock 的多Condition 。
 * @author liaojiacan
 * @date 2019/6/21
 */
public class Solution2 {


    private static class PrintTask implements Runnable{

        private String letter;
        private ReentrantLock lock;
        private Condition myCondition;
        private Condition nextCondition;

        public PrintTask(String letter,ReentrantLock lock, Condition myCondition, Condition nextCondition) {
            this.letter = letter;
            this.lock = lock;
            this.myCondition = myCondition;
            this.nextCondition = nextCondition;
        }

        @Override
        public void run() {
            for(;;){
                lock.lock();
                try {
                    System.out.println(letter);
                    nextCondition.signal();
                    myCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }

        }
    }

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        Condition c3 = lock.newCondition();

        new Thread(new PrintTask("1",lock,c1,c2),"t1").start();
        new Thread(new PrintTask("2",lock,c2,c3),"t2").start();
        new Thread(new PrintTask("3",lock,c3,c1),"t2").start();


    }
}
