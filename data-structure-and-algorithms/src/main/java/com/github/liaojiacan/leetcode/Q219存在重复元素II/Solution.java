package com.github.liaojiacan.leetcode.Q219存在重复元素II;

/**
 * @author liaojiacan
 * @date 2019/5/2
 */
public class Solution {

    public boolean containsNearbyDuplicate(int[] nums, int k) {


        for(int i=0;i<nums.length ;++i){

            for(int j=i+k;j<nums.length && j-i<=k;++j){

                if(nums[i] == nums[j]){
                    return true;
                }
            }

        }
        return false;
    }

    public static void main(String[] args) {


        int[] nums = new int[]{1,2,3,1,2,3};
        int k = 2;
        System.out.println(new Solution().containsNearbyDuplicate(nums,k));
    }
}
