package com.github.liaojiacan.leetcode.Q5最长回文子串;

/**
 * @author liaojiacan
 * @date 2019/2/9
 */
class Solution {
	/**
	 * 	中心扩散检查，分2种，一种存在中心点，一种不存在中心点如 abba;
	 */
	public String longestPalindrome(String s) {
		if(s == null || s.length()<1){
			return "";
		}
		int start = 0,end = 0 , max = 0;
		for(int i=0;i<s.length();++i){

			int len1 = tryExpandAroundCenter(s,i,i);
			int len2 = tryExpandAroundCenter(s,i,i+1);
			int len = Math.max(len1,len2);
			if(max < len){
				start = (i-(len-1)/2);
				end = (i+len/2);
				max = len;
			}

		}
		return s.substring(start,end+1);
	}

	private int tryExpandAroundCenter(String s,int l,int r){

		while(s.length() > r && l >=0 && s.charAt(l) == s.charAt(r)){
			l--;
			r++;
		}
		return r-l-1;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().longestPalindrome("bbabab"));
	}
}