package com.github.liaojiacan.leetcode.Q622设计循环队列;

/**
 * @author liaojiacan
 * @date 2019/3/1
 */
public class MyCircularQueue {

	private int head ;
	private int tail ;
	private int size ;
	private int[] data;
	/** Initialize your data structure here. Set the size of the queue to be k. */
	public MyCircularQueue(int k) {
		data = new int[k];
		head = -1;
		tail = -1;
		size = 0;
	}

	/** Insert an element into the circular queue. Return true if the operation is successful. */
	public boolean enQueue(int value) {
		if(this.isFull()){
			return false;
		}

		if(this.isEmpty()){
			head = 0;
			tail = -1;
		}
		tail = ((tail+1) % data.length);
		data[tail] = value;
		size++;
		return true;
	}

	/** Delete an element from the circular queue. Return true if the operation is successful. */
	public boolean deQueue() {
		if(this.isEmpty()){
			return false;
		}
		head++;
		if(head > data.length -1){
			head = 0;
		}
		size--;
		return true;
	}

	/** Get the front item from the queue. */
	public int Front() {
		if(this.isEmpty()){
			return  -1;
		}
		return data[head];
	}

	/** Get the last item from the queue. */
	public int Rear() {
		if(this.isEmpty()){
			return  -1;
		}
		return data[tail];
	}

	/** Checks whether the circular queue is empty or not. */
	public boolean isEmpty() {
		return size == 0;
	}

	/** Checks whether the circular queue is full or not. */
	public boolean isFull() {
		return size == data.length;
	}

	public static void main(String[] args) {
		MyCircularQueue q = new MyCircularQueue(6);
		System.out.println(q.enQueue(6));
		System.out.println(q.Rear());
		System.out.println(q.Rear());
		System.out.println(q.deQueue());
		System.out.println(q.enQueue(5));
		System.out.println(q.Rear());
		System.out.println(q.deQueue());
		System.out.println(q.Front());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
	}
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */