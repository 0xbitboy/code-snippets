package com.github.liaojiacan.leetcode.Q215数组中的第K个最大元素;

/**
 * @author liaojiacan
 * @date 2019/2/10
 */
class Solution {

	public int findKthLargest(int[] nums, int k) {

		return findKthLargest(nums,nums.length-k,0,nums.length-1);

	}

	private int findKthLargest(int[] nums, int k, int l,int r){
		int  pivotIndex = partition(nums,l,r);
		if(pivotIndex == k){
			return nums[pivotIndex];
		}
		if(pivotIndex < k){
			return findKthLargest(nums,k,pivotIndex+1,r);
		}
		return  findKthLargest(nums,k,l,pivotIndex-1);

	}

	private int partition(int[] nums,int left,int right){
		int l = left,r = right;
		int pivot = nums[l];

		while(l < r){
			while(l<r && nums[r] >= pivot ){
				r--;
			}

			while(l<r && nums[l]<=pivot){
				l++;
			}
			swap(nums,l,r);
		}
		swap(nums,left,l);
		return l;
	}

	private void swap(int[] nums,int i,int j){
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}


}