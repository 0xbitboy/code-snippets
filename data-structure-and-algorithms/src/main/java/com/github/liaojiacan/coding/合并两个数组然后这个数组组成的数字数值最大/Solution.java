package com.github.liaojiacan.coding.合并两个数组然后这个数组组成的数字数值最大;

import java.util.Arrays;

/**
 * @author liaojiacan
 * @date 2020/8/5
 * @desc
 */
public class Solution {

    public int[] mergeTwoArray(int[] arr1, int[] arr2) {
        if (arr2 == null || arr2.length == 0) {
            return arr1;
        }

        if (arr1 == null || arr1.length == 0) {
            return arr2;
        }

        int[] ans = new int[arr1.length + arr2.length];

        int p1 = 0, p2 = 0, k = 0;

        while (p1 < arr1.length && p2 < arr2.length) {
            if (arr1[p1] > arr2[p2]) {
                ans[k++] = arr1[p1++];
            } else {
                ans[k++] = arr2[p2++];
            }
        }

        while (p1 < arr1.length) {
            ans[k++] = arr1[p1++];
        }

        while (p2 < arr2.length) {
            ans[k++] = arr2[p2++];
        }


        return ans;
    }

    public static void main(String[] args) {
        int[] arr1 = {6, 2 ,2,1};
        int[] arr2 = {6,2,2,3};
        System.out.println(Arrays.toString(new Solution().mergeTwoArray(arr1, arr2)));
    }

}
