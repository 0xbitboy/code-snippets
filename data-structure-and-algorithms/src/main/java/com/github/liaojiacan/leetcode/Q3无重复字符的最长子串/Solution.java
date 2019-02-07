package com.github.liaojiacan.leetcode.Q3无重复字符的最长子串;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liaojiacan
 * @date 2019/2/5
 */
class Solution {
	public int lengthOfLongestSubstring(String s) {

		int max = 0;
		Set<Character> set = new HashSet<>(s.length());
		int i=0,j=0;
		while(i < s.length() && j < s.length()){
			if(set.add(s.charAt(j))){
				j++;
				if(max < j-i){
					max = j-i;
				}
			}else{
				set.remove(s.charAt(i++));
			}

		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().lengthOfLongestSubstring("aab"));
	}
}