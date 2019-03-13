package com.github.liaojiacan.leetcode.Q1003检查替换后的词是否有效;

import java.util.Stack;

/**
 * @author liaojiacan
 * @date 2019/3/6
 */
class Solution {
	public boolean isValid(String S) {
		Stack<Character> stack = new Stack<>();
		for(char c : S.toCharArray()){
			if(c == 'c' ){
				if(stack.isEmpty() || !stack.pop().equals('b')){
					return false;
				}

				if(stack.isEmpty() || !stack.pop().equals('a')){
					return false;
				}
				continue;
			}
			stack.push(c);
		}

		return true;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().isValid("aabcbc"));
	}
}