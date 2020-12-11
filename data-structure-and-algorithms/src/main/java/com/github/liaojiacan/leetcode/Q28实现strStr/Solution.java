package com.github.liaojiacan.leetcode.Q28实现strStr;

/**
 * @author liaojiacan
 * @date 2019/1/30
 */
class Solution {
	public int strStr(String haystack, String needle) {
		int n = haystack.length();
		int m = needle.length();
		if(m == 0){
			return 0;
		}
		// 计算next数组
		int[] next = new int[m];
		computeNextTable(needle,next);
		int k = -1;
		for (int i = 0; i < n; i++) {
			while ( k >= 0 && haystack.charAt(i) != needle.charAt(k+1)){
				k = next[k];
			}

			if( haystack.charAt(i) == needle.charAt(k+1)){
				++k;
			}

			if( k == m - 1){
				return i- m +1;
			}

		}

		return -1;

	}

	// ll
	private void computeNextTable(String needle , int[] next){
		// m = 2
		int m = needle.length();
		next[0] = -1;
		int k = -1;
		// ll
		// next[0] = -1
		for (int i = 1; i < m ; i++) {
			while (k >= 0 && needle.charAt(k + 1) != needle.charAt(i)){
				k = next[k];
			}
			// needle.charAt(0) != needle.charAt(1)
			if(needle.charAt(k + 1) == needle.charAt(i)){
				++k;
			}
			// next[1] = -1;
			next[i] = k;
		}
	}

	public static void main(String[] args) {
		System.out.println(new Solution().strStr("abcababcd","ababcd"));
	}

}