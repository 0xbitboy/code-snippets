package com.github.liaojiacan.lintcode.Q387字符串中的第一个唯一字符;

/**
 * @see <a href="https://leetcode-cn.com/problems/first-unique-character-in-a-string/submissions/">字符串中的第一个唯一字符</a>
 * @author liaojiacan
 * @date 2019/1/29
 */
class Solution {
	public int firstUniqChar(String s) {
		int[] counts = new int[26];
		char[] chars = s.toCharArray();
		for(int i=0;i<chars.length;++i){
			counts[chars[i]-'a']++;
		}
		for(int i=0;i<chars.length;++i){
			if(counts[chars[i]-'a']==1){
				return i;
			}
		}
		return -1;
	}
}
