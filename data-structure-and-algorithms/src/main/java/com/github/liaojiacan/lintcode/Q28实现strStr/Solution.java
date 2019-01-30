package com.github.liaojiacan.lintcode.Q28实现strStr;

/**
 * @author liaojiacan
 * @date 2019/1/30
 */
class Solution {
	public int strStr(String haystack, String needle) {

		if("".equals(needle)){
			return 0;
		}

		if(needle.length()>haystack.length()){
			return -1;
		}
		char[] haystackChars = haystack.toCharArray();
		char[] needleChars = needle.toCharArray();
		int index = -1;
		int p = 0;
		for(int i=0;i<haystackChars.length;++i){

			if(p < needleChars.length && haystackChars[i] == needleChars[p]){
				p++;
				if(index == -1){
					index = i;
				}
				if(p==needleChars.length){
					return index;
				}
			}else{
				p=0;
				if(index != -1){
					i = index;
				}
				index = -1;
			}

		}

		return -1;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().strStr("mississippi","pi"));
	}
}