package com.github.liaojiacan.coding.查找第一个大于K且不存在于数组中的数;

import java.util.Arrays;

/**
 *  无序数组m，数字k，O(n)时间， O(1)空间下 找到第一个不存在于数组中的，且大于k的正整数
 * @author liaojiacan
 * @date 2021/6/7
 * @desc
 */
public class Solution {

    public static void main(String[] args) {

        int[] arr = new int[]{6,-80,8,14,11,19};
        int k = 7;
        int n = arr.length;
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if(num <= k){
                arr[i] =0;
            }else if(num - k < n){
                int j = num - k;
                if(arr[j] > 0){
                    arr[i] = arr[j];
                }
                arr[j] = -1;
            }
        }
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > 0){
                System.out.println(i + k);
                return;
            }
        }
        System.out.println(arr.length + k + 1);
    }
}
