package com.github.liaojiacan.acwing.数据结构.单调队列Q154;

import java.io.*;
import java.util.*;
public class Main{


    public static void main(String[] args) throws Exception{

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] s1 = reader.readLine().split(" ");
        int n = Integer.parseInt(s1[0]);
        int k = Integer.parseInt(s1[1]);
        String[] s = reader.readLine().split(" ");
        int[] nums = new int[n];
        for(int i = 0 ; i < n ;++i){
            nums[i] = Integer.parseInt(s[i]);
        }

        Deque<Integer> queue = new LinkedList<>();
        for(int i= 0; i < n ; ++i){
            while(!queue.isEmpty() && nums[queue.peekLast()] >= nums[i]){
                queue.pollLast();
            }
            while(!queue.isEmpty() && queue.peekFirst() < i - k +1){
                queue.pollFirst();
            }
            queue.offerLast(i);
            if(i >= k-1 ){
                writer.write(nums[queue.peekFirst()] +" ");
            }
        }
        queue.clear();
        writer.write("\n");
        for(int i= 0; i < n ; ++i){

            while(!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]){
                queue.pollLast();
            }
            while(!queue.isEmpty() && queue.peekFirst() < i - k +1){
                queue.pollFirst();
            }
            queue.offerLast(i);
            if(i >= k-1){
                writer.write(nums[queue.peekFirst()] +" ");
            }
        }
        writer.flush();
        writer.close();
        reader.close();
    }

}