package com.github.liaojiacan.acwing.数据结构.单调队列Q154;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author liaojiacan
 * @date 2020/12/15
 * @desc
 */
public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Deque<Integer> queue = new LinkedList<>();
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] > nums[i]){
                queue.pollLast();
            }
            queue.offerFirst(i);
            if(!queue.isEmpty() && queue.peekLast() < i - k + 1){
                queue.pollLast();
            }
            if(i >= k-1 ){
                System.out.print(nums[queue.peekFirst()]+" ");
            }
        }
        System.out.print("\n");
        queue.clear();
        for (int i = 0; i < n; i++) {
            if (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]){
                queue.pollLast();
            }
            queue.offerFirst(i);
            if(!queue.isEmpty() && queue.peekLast() < i - k + 1){
                queue.pollLast();
            }

            if(queue.size() == k){
                System.out.print(nums[queue.peek()]+" ");
            }
        }



    }

}
