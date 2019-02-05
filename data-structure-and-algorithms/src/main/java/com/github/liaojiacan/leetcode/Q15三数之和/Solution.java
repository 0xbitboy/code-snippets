package com.github.liaojiacan.leetcode.Q15三数之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liaojiacan
 * @date 2019/2/3
 */
class Solution {

	/**
	 * 先排序
	 * 选定一个数num[i]，则剩下的2个数的和就是 0-num[i] ，也就是转换成了2数之和的问题。
	 * 采用2个指针来扫描，如果2个指针的数的和刚好命中，则添加到结果列表。
	 * 如果2个数的和小于指定数，则左边的指针右移（右边的数大点）
	 * 如果2个数的和大于指定数，则右边的指针左移（左边的数小点）
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);

		for(int i = 0 ; i< nums.length-2;++i){

			int l = i+1;
			int r = nums.length-1;
			int sum = 0 - nums[i];
			while(l < r && (i==0 || nums[i] != nums[i-1])){
				if(nums[l] + nums[r] == sum){
					result.add(Arrays.asList(nums[i],nums[l],nums[r]));
					while(l<r && nums[l] == nums[l+1]) l++;
					while(l<r && nums[r] == nums[r-1]) r--;
					l++;
					r--;
				}else if(nums[l] + nums[r] < sum){
					while(l<r && nums[l] == nums[l+1]) l++;
					l++;
				}else {
					while(l<r && nums[r] == nums[r-1]) r--;
					r--;
				}

			}

		}
		return result;
	}
}
