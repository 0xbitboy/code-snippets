package com.github.liaojiacan.thread;

import java.util.Arrays;

/**
 * @author liaojiacan
 * @date 2019/3/8
 */
public class WaitNotifyTest {



	public static void main(String[] args) {

		Object lock = new Object();

		new Thread(new Runnable() {
			@Override
			public void run() {

				synchronized (lock){

					System.out.println("Thread waiting...");
					try {
						lock.wait();
						System.out.println("Thread wakeup...");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		},"T1").start();

		new Thread(new Runnable() {
			@Override
			public void run() {

				synchronized (lock){

					System.out.println("T2-Notifier Thread...");
					// 唤醒T1,执行后T1进入Blocked状态，直到T2执行完同步代码块。
					lock.notify();
					System.out.println("T2-After Notify");
				}
				System.out.println("T2-After synchronized block");

			}
		},"T2").start();

	}
}
