package com.github.liaojiacan.lintcode.查找第n大的数;

import java.util.Arrays;

class Solution {
	/*
	 * @param k : description of k
	 * @param nums : array of nums
	 * @return: description of return
	 */
	public int kthLargestElement(int k, int[] nums) {
		// write your code here
		//第K大的位置则 pivot 的位置为nth = nums.length-k;

		if(nums==null || nums.length==1){
			return nums[0];
		}

		int start = 0,end = nums.length-1,nth = nums.length-k;
		while (true){
			int pivot = partion(nums,start,end);
			if (pivot==nth){
				return nums[pivot];
			}else if(pivot<nth){
				start = pivot+1;
			}else {
				end = pivot-1;
			}
		}

	}


	public int partion(int[] nums,int start ,int end){
		int l = start,r = end+1,pivot = nums[start];
		while (true){
			while (nums[--r]>pivot){
				if(r==start) break;
			}
			while (nums[++l]<pivot){
				if(l==end) break;
			}
			if(r<=l) break;

			swap(nums,r,l);
		}
		swap(nums,start,r);
		return r;
	}

	private void swap(int[] nums,int i,int j){
		int tmp = nums[i];
		nums[i]= nums[j];
		nums[j] = tmp;

	}

	public void qs(int[] nums,int start,int end){
		if(start>=end){
			return;
		}

		int pivot = partion(nums,start,end);
		if(pivot>start)
			partion(nums,start,pivot-1);
		if(pivot<end)
			partion(nums,pivot+1,end);
	}

	public static void main(String[] args) {
		int[] nums = {1,2,3,4,5,6,8,9,10,7};
		// System.out.println(new Solution().kthLargestElement(2,nums));
		new Solution().qs(nums,0,nums.length-1);
		System.out.println(Arrays.toString(nums));
	}
}
