package com.github.liaojiacan.leetcode.Q347前K个高频元素;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author liaojiacan
 * @date 2019/2/17
 */
public class Solution {

	public static class Item{
		int num;
		int count;

		public Item(int num, int count) {
			this.num = num;
			this.count = count;
		}
	}

	public List<Integer> topKFrequent(int[] nums, int k) {
		if(nums == null || nums.length == 0 ){
			return Collections.emptyList();
		}

		Map<Integer,Integer> map = new HashMap<>(nums.length);
		for(int num : nums){
			map.put(num,map.getOrDefault(num,0)+1);
		}

		PriorityQueue<Integer> minHeap = new PriorityQueue<>(k,(num1,num2)->{
			return map.get(num1) -  map.get(num2);
		});

		map.forEach((num,count)->{

			if(minHeap.size() < k ){
				minHeap.add(num);
			}else if( map.get(minHeap.peek()) < count){
				minHeap.poll();
				minHeap.add(num);
			}

		});

		return minHeap.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
	}

	public static void main(String[] args) {
		int nums[] = new int[]{1,2,2};
		int k = 1;
		System.out.println(Arrays.toString(new Solution().topKFrequent(nums,k).toArray()));
	}

}
