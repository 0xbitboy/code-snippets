package com.github.liaojiacan.lintcode.recover_rotated_sorted_array;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    /**
     * 给定一个旋转排序数组，在原地恢复其排序。
     * 比如，原始数组为[1,2,3,4], 则其旋转数组可以是[1,2,3,4], [2,3,4,1], [3,4,1,2], [4,1,2,3]
     * 三步反转法
     * @param nums: An integer array
     * @return: nothing
     */
    public void recoverRotatedSortedArray(List<Integer> nums) {
        // write your code here

        for(int i=0;i<nums.size()-1;i++){
            if(nums.get(i)>nums.get(i+1)){
                reverse(nums,0,i);
                reverse(nums,i+1,nums.size()-1);
                reverse(nums,0,nums.size()-1);
                return;
            }
        }
    }

    private void reverse(List<Integer> nums,int start,int end){
        while (start<end){
            Integer temp = nums.get(start);
            nums.set(start,nums.get(end));
            nums.set(end,temp);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(4, 5, 1, 2, 3);
        new Solution().recoverRotatedSortedArray(nums);
        System.out.println(nums.stream().map((num)->String.valueOf(num)).collect(Collectors.joining(",")));
    }
}