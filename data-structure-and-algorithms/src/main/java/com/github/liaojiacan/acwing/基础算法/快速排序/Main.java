package com.github.liaojiacan.acwing.基础算法.快速排序;

/**
 * @author liaojiacan
 * @date 2020/12/13
 * @desc
 */
import java.util.*;
import java.util.stream.*;
public class Main{

    public static void swap(int[] nums, int i, int j){

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;

    }

    // 5, 4
    public static int partition(int[] nums, int left, int right){

        int l = left, r = right + 1;
        //5
        int pvoit = nums[left];

        while(true){
            while(nums[++l] < pvoit) if(l == right) break;
            while(nums[--r] > pvoit) if(r == left) break;

            if(l >= r){
                break;
            }
            swap(nums, l, r);
        }
        swap(nums, left, r);
        return r;

    }

    public static void sort(int[] nums,int left ,int right){

        if(left >= right){
            return;
        }

        int j = partition(nums, left, right);
        sort(nums, left, j-1);
        sort(nums, j+1, right);
    }


    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] nums = new int[n];
        for(int i =0 ;i < n; ++i){
            nums[i] = scanner.nextInt();
        }

        sort(nums,0, nums.length -1);

        String res = Arrays.stream(nums).mapToObj(String::valueOf).collect(Collectors.joining(" "));
        System.out.println(res);
    }

}