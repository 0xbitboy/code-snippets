package com.github.liaojiacan.jdk;

import java.util.ArrayList;

/**
 * 荔枝FM:
 * 线程安全的有界队列的实现
 * Created by liaojiacan on 2017/7/22.
 */
public class BlockQueue {

    Object lock = new Object();
    int head;
    int tail;
    int count;

    ArrayList<Integer> list ;


    BlockQueue(int size){
        list = new ArrayList<>(size);
    }

    public  int read() throws InterruptedException {
        synchronized(lock){
            while (head==tail){ // 多线程的比较都要用while代替
                lock.wait();
            }

            int x  = list.get(head);
            if(++head==list.size()) head=0;
            count--;
            return x;
        }
    }

    public  void write(int x){
        synchronized(lock){
            list.add(tail,x);
            if(++tail==list.size()) tail=0;
            count++;
            lock.notifyAll();
        }

    }


    public void offer(int x){
        write(x);
    }

    public int poll() throws InterruptedException {
        return read();
    }


    public static void main(String[] args) {

        BlockQueue queue = new BlockQueue(10);

        new Thread(()->{
            while (true){
                queue.offer(1);
            }
        }).start();

        new Thread(()->{
            while (true){
                try {
                    System.out.println(queue.poll());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }




}
