package com.github.liaojiacan.leetcode.Q260只出现一次的数字III;

/**
 * @author liaojiacan
 * @date 2019/2/12
 */
class Solution {
	public int[] singleNumber(int[] nums) {

		int result = 0;
		for(int i : nums){
			result ^= i;
		}

		int flag = 1;

		while((result & 1) == 0 ){
			flag <<= 1;
			 result >>= 1;
		}

		int nums1 = 0;
		int nums2 = 0;
		for(int i:nums){
			if((i & flag) == 0){
				nums1 ^= i;
			}else{
				nums2 ^= i;
			}
		}

		return new int[]{nums1,nums2};
	}

}
