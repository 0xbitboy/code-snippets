package com.github.liaojiacan.leetcode.Q18四数之和;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author liaojiacan
 * @date 2019/3/6
 */
class Solution {
	public List<List<Integer>> fourSum(int[] nums, int target) {

		Arrays.sort(nums);
		List<List<Integer>> ans = new ArrayList<>();
		for(int i=0;i<nums.length - 3  ;++i){

			if(i>0 && nums[i] == nums[i-1]) continue;

			for(int j=i+1;j<nums.length -2 ;++j){

				if( j>i+1 && nums[j] == nums[j-1]) continue;

				int l=j+1,r=nums.length-1;
				while(l<r){
					int sum = nums[i]+nums[j]+nums[l]+nums[r];
					if(sum == target){
						ans.add(Arrays.asList(nums[i],nums[j],nums[l],nums[r]));
						while (l<r && nums[l] == nums[l+1])
							l++;
						while (l<r && nums[r] == nums[r-1])
							r--;
						l++;
						r--;
					}else if(sum < target){
						l++;
					}else{
						r--;
					}
				}
			}

		}

		return ans;
	}

	public static void main(String[] args) {
		int[] data = new int[]{5,5,3,5,1,-5,1,-2};
		Arrays.sort(data);
		System.out.println(Arrays.toString(data));
		List<List<Integer>> ans = new Solution().fourSum(data, 0);
		ans.stream().forEach(l-> System.out.println(l.toString()));
	}
}
