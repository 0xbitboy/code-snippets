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

		if(str==null){
			return null;
		}

		Set<String> result = new HashSet<>();
		permutation(result,new StringBuilder(str),0);
		return new ArrayList<>(result);
	}

	public void permutation(Set<String> result, StringBuilder str, int begin){

		if(str.length()-1==begin){
			result.add(str.toString());
			return ;
		}

		for(int i = begin;i<str.length();++i){

			char temp = str.charAt(i);
			str.setCharAt(i,str.charAt(begin));
			str.setCharAt(begin,temp);
			permutation(result,str,begin+1);

			//把位置换回来，进行后半段的交换。
			temp = str.charAt(i);
			str.setCharAt(i,str.charAt(begin));
			str.setCharAt(begin,temp);

		}

	}

	public static void main(String[] args) {
		System.out.println(String.join(",",new Solution().stringPermutation2("abcd")));
	}
}
