package com.github.liaojiacan.coding.实现柱塞队列;

/**
 * @author liaojiacan
 * @date 2021/3/2
 * @desc
 */
public class Solution {

    public static void main(String[] args) {
        ArrayListBlockingQueue queue = new ArrayListBlockingQueue(10);

        new Thread(()->{
            int i=1;
            while (true){
                try {
                    queue.offer(i++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
