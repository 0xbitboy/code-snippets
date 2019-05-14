package com.github.liaojiacan.thread;

/**
 * 实现2个线程交替打印
 * @author liaojiacan
 * @date 2019/5/10
 */
public class MultiThreadPrint {

    public static int count ;


    /**
     * 利用 ObjectMonitor 的 condition 机制实现通知。每个线程任务维护一个 前驱的观察对象（锁对象），和自己的锁对象。
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        class Task implements Runnable{

            private Object prev;
            private Object self;

            public Task(Object prev, Object self) {
                this.prev = prev;
                this.self = self;
            }

            @Override
            public void run() {
                while (true) {
                    //每次先获取到prev的objectMonitor, 这个时候其他线程（pre线程）无法获取到锁，挂起中。
                    synchronized (prev){
                        //获取到自己的的锁对象，保证只有自己可以打印。
                        synchronized (self) {
                            System.out.println(Thread.currentThread().getName() + ":" + (count++));
                            // 通知其他线程，可以继续了。
                            self.notifyAll();
                        }
                        try {
                            //挂起，等待前驱线程打印完。
                            Thread.sleep(1000);
                            prev.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }

        Object t1Lock = new Object();
        Object t2Lock = new Object();

        Thread t1 = new Thread(new Task(t2Lock,t1Lock));
        Thread t2 = new Thread(new Task(t1Lock,t2Lock));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        

    }
}
