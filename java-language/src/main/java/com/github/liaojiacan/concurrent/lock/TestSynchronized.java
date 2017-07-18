package com.github.liaojiacan.concurrent.lock;

/**
 * synchronized 锁的是 对象
 *
 *  线程 t1 执行m1方法后 就锁了对象，线程t2需要等待t1释放锁
 *
 * Created by liaojiacan on 2017/7/19.
 */
public class TestSynchronized {

    public  synchronized  void m1(){

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("m1 start!");
    }

    public synchronized  void m2(){

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("m2 start!");
    }

    public static void main(String[] args) {
        TestSynchronized t = new TestSynchronized();

        new Thread(()->t.m1(),"t1").start();
        new Thread(()->t.m2(),"t2").start();
    }
}
