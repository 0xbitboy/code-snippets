package com.github.liaojiacan.lintcode.Q148_颜色分类;

/**
 * @see https://www.lintcode.com/problem/sort-colors/description
 */
public class Solution {
    /**
     * @param nums: A list of integer which is 0, 1 or 2
     * @return: nothing
     */
    public void sortColors(int[] nums) {
             // write your code here
        // 计数排序法
        int[] count = new int[3];

        for(int i=0;i<nums.length;++i){
            count[nums[i]]++;
        }
        int idex = 0;
        for(int i=0;i<count.length;++i){
            for(int j=0;j<count[i];j++){
                nums[idex++] = i;
            }
        }

    }
}
