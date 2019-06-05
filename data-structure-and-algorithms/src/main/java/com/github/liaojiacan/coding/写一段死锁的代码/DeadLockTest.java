package com.github.liaojiacan.coding.写一段死锁的代码;

/**
 * @author liaojiacan
 * @date 2019/6/5
 */
public class DeadLockTest {

    public static void main(String[] args) throws InterruptedException {

        Object lockA = new Object();
        Object lockB = new Object();


        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {

                synchronized (lockA) {
                    // do something;
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lockB) {

                    }
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockB) {
                    synchronized (lockA) {
                        // do something;
                    }
                }
            }
        });


        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }
}
