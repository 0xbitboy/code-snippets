package com.github.liaojiacan.lintcode.中位数;

public class Solution {

    public int solution(int[] nums){
        return quicksort(nums,0,nums.length-1);
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 1, 2, 3};
        System.out.println(new Solution().solution(nums));
    }
    /**
     * 取第一个数作为参考点，从右边向左扫描直到找到比参考点小的数，进行交换。
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    int partion(int[] nums,int left , int right){
        int temp = nums[left];
        while (left<right){
            while (temp<nums[right] && left<right){
                --right;
            }
            nums[left] = nums[right];
            while (temp>nums[left] && left<right){
                ++left;
            }
            nums[right] = nums[left];
        }
        nums[left] = temp;
        return left;
    }

    int quicksort(int[] nums,int left,int right){

        int pivot;
        if(left>=right){
            return nums[left];
        }
        pivot = partion(nums,left,right);
        if(pivot==nums.length/2){
            return nums[pivot];
        }
        partion(nums,left,pivot-1);
        partion(nums,pivot+1,right);
        return -1;
    }

}
