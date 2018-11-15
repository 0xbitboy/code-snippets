package com.github.liaojiacan.lintcode.中位数;

public class Solution {

    public int solution(int[] nums){
        if(nums==null || nums.length == 1){
            return nums[0];
        }

        int l = 0,r = nums.length-1,mid = (nums.length+1)/2;

        while (true){
            int pivot = partion(nums,l,r);
            if(pivot+1==mid){
                return nums[pivot];
            }else if(pivot+1<mid){
                l = pivot+1;
            }else {
                r = pivot -1;
            }
        }

    }

    public static void main(String[] args) {
        int[] nums = {-1,-2,-3,-100,-1,-50};
        System.out.println(new Solution().solution(nums));
    }
    /**
     * 取第一个数作为参考点，从右边向左扫描直到找到比参考点小的数，进行交换。
     *
     * @param nums
     * @param start
     * @param end
     * @return
     */

    private int partion(int[] nums, int start, int end){
        int l = start, r = end, pivot = nums[start];
        while (l <= r) {
            while (l <= r && nums[l] <= pivot) {
                l++;
            }
            while (l <= r && nums[r] >= pivot) {
                r--;
            }
            if (l <= r) {
                swap(nums, l++, r--);
            }
        }
        swap(nums, start, r);
        return r;
    }
    private void swap(int[] nums,int i,int j){
        int tmp = nums[i];
        nums[i]= nums[j];
        nums[j] = tmp;

    }

}
