package com.github.liaojiacan.coding.实现柱塞队列;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liaojiacan
 * @date 2021/3/2
 * @desc
 */
public class ArrayListBlockingQueue<T> {

    private int head;
    private int tail;
    private int size;
    private final Object[] items;
    private final int capacity;
    private final ReentrantLock lock;
    private final Condition notFull;
    private final Condition notEmpty;

    public ArrayListBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.lock = new ReentrantLock();
        this.notFull = this.lock.newCondition();
        this.notEmpty = this.lock.newCondition();
        this.items = new Object[capacity];
    }

    public T poll() throws InterruptedException {
        final Lock lock = this.lock;
        lock.lock();
        try{
            while (isEmpty()){
                notEmpty.await();
            }
            T res = (T) items[head];
            if(++head == capacity) head = 0;
            size--;
            notFull.signalAll();
            return res;
        } finally {
            lock.unlock();
        }
    }


    public void offer(T t) throws InterruptedException {
        final Lock lock = this.lock;
        lock.lock();
        try {
            while (isFull()){
                notFull.await();
            }
            items[tail] = t;
            if(++tail == capacity) tail = 0;
            size++;
            notEmpty.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isFull(){
        return size == capacity;
    }
}
