package com.github.liaojiacan.leetcode.Q349两个数组的交集;


import java.util.*;

/**
 * @author liaojiacan
 * @date 2019/1/28
 */
class Solution {
	public int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> result = new HashSet<>();
		Map<Integer,Object> map = new HashMap<>(nums1.length);

		for(int num : nums1){
			map.put(num,Boolean.TRUE);
		}

		for(int num : nums2){
			if(map.containsKey(num)){
				result.add(num);
			}
		}
		return result.stream().mapToInt(Integer::intValue).toArray();

	}
	public static void main(String[] args) {
		int[] num1 = new int[]{4,9,5};
		int[] num2 = new int[]{9,4,9,8,4};

		System.out.println(Arrays.toString(new Solution().intersection(num1,num2)));
	}
}