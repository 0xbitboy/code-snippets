package com.github.liaojiacan.acwing.动态规划.最长上升子序列Ⅱ;

import java.util.Scanner;

/**
 * @author liaojiacan
 * @date 2021/1/28
 * @desc
 *
 * @href https://www.acwing.com/problem/content/898/
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        // 长度
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int[] q = new int[n];
        q[0] = Integer.MIN_VALUE;
        int len = 0;
        // 表示长度为k的以nums[k]
        for (int i = 0; i < n ; i++) {
            int l = 0 ,r = len;
            while (l < r){
                int mid = l + (r - l) / 2;
                if(q[mid] < nums[i]){
                    l = mid+1;
                }else {
                    r = mid;
                }
            }
            len = Math.max(len, r+1);
            q[r+1] = nums[i];
        }

        System.out.println(len);

    }
}
