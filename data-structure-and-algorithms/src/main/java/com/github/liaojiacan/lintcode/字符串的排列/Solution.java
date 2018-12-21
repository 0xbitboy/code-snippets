package com.github.liaojiacan.lintcode.字符串的排列;

import java.util.*;

/**
 * @author liaojiacan
 * @date 2018/11/11
 */
public class Solution {



	/**
	 * @param str: A string
	 * @return: all permutations
	 */
	public List<String> stringPermutation2(String str) {
		// write your code here

		List<String> result = new LinkedList<>();
		if(str==null || "".equals(str)){
			result.add("");
			return result;
		}

		permutation(result,str.toCharArray(),0);
		return new ArrayList<>(result);
	}

	public void permutation(List<String> result,  char[] chars, int begin){

		if(chars.length-1==begin){
			result.add(String.valueOf(chars));
			return ;
		}

		for(int i = begin;i<chars.length;++i){

			char temp = chars[i];

			// 当出现相邻字符相同时会出现重复排列
			// eg. aabc. i=0时 chars[0]=a，子串为abc。当i=1时 ，chars[1]=a, 子串为 bc 与 i=0时的一个子串重复（此时后面的子串就不需要继续了）。
			if(!needSwap(chars,begin,i)){
				continue;
			}

			chars[i] = chars[begin];
			chars[begin] = temp;
			permutation(result,chars,begin+1);

			//把位置换回来，进行后半段的交换。
			temp = chars[i];
			chars[i] = chars[begin];
			chars[begin] = temp;

		}

	}

	boolean needSwap(char[] chars,int begin,int end){
		for(int i=begin+1;i<=end;++i){
			if(chars[i] == chars[begin]){
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {
		System.out.println(String.join(",",new Solution().stringPermutation2("abca")));
	}
}
