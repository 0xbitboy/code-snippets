package com.github.liaojiacan.lintcode.删除数组中重复的数字;

import java.util.Arrays;

/**
 *
    101. 删除排序数组中的重复数字 II
        跟进“删除重复数字”：
        如果可以允许出现两次重复将如何处理？
 * https://www.lintcode.com/problem/remove-duplicates-from-sorted-array-ii/description
 */
public class Solution {
    /**
     * @param A: a array of integers
     * @return : return an integer
     */
    public int removeDuplicates(int[] nums) {
        // write your code here

        int k = 0;
        int count = 0;
        for(int i=0;i<nums.length;++i){

            if(nums[i]==nums[k]){
                if(count<2){
                    nums[k++] = nums[i];
                    count++;
                }
            }else{
                nums[k++] = nums[i];
                count=1;
            }

        }
        return k;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        new Solution().removeDuplicates(nums);
        System.out.println(Arrays.toString(nums));
    }
}
