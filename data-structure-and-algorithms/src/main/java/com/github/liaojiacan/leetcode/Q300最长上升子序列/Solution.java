package com.github.liaojiacan.leetcode.Q300最长上升子序列;

//给定一个无序的整数数组，找到其中最长上升子序列的长度。
//
// 示例:
//
// 输入: [10,9,2,5,3,7,101,18]
//输出: 4
//解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
//
// 说明:
//
//
// 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
// 你算法的时间复杂度应该为 O(n2) 。
//
//
// 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
// Related Topics 二分查找 动态规划


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLIS(int[] nums) {
        // 定义 dp[i] 为以nums[i] 结尾的上升子序列长度
        // 状态转移:
        //       dp[i] = 1;
        //       dp[i] = max{dp[i],dp[k] + 1} {k<i && nums[k] < nums[i]}
        if(nums.length == 0){
            return 0;
        }
        int[] dp  = new int[nums.length];
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            ans = Math.max(dp[i] , ans);
        }
        return ans;
    }

    public int lengthOfLIS_bs(int[] nums){
        // 贪心算法+ 二分查找
        // 关键: 从左到右遍历，维护一个top[]，top[i]表示以top[i] 为首的一组数据(我们只需要求长度，所以序列可以不保存，只保留这组数据的最小值)
        //
        int[] top = new int[nums.length+1];
        int lines = 0;
        for (int num: nums){

            int left  = 0, right = lines;
            while (left< right){
                int mid = (left+right)/2;
                if(top[mid] >= num){
                    right = mid;
                }else  {
                    left = mid + 1;
                }
            }
            if(left == lines){
                lines ++;
            }
            top[left] = num;
        }
        return lines;
    }
}
//leetcode submit region end(Prohibit modification and deletion)