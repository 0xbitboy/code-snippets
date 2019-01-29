package com.github.liaojiacan.lintcode.Q8字符串转换整数;

/**
 * @author liaojiacan
 * @date 2019/1/29
 */
class Solution {
	public int myAtoi(String str) {
		char[] chars =  str.toCharArray();
		boolean isNegative = false;
		boolean isFirstNoneBankChar = true;
		int num = 0;
		for(char c : chars){

			if(isFirstNoneBankChar && c==' ' ){
				continue;
			}

			if(isFirstNoneBankChar && c=='-'){
				isNegative = true;
				isFirstNoneBankChar = false;
				continue;
			}

			if(isFirstNoneBankChar && c=='+'){
				isNegative = false;
				isFirstNoneBankChar = false;
				continue;
			}


			if(isFirstNoneBankChar && (c<'0'||c>'9')) {
				return 0;
			}

			if(!isFirstNoneBankChar && (c<'0'||c>'9')){
				break;
			}

			isFirstNoneBankChar = false;
			num=num*10+(c-'0');
			if(num%10!=c-'0'){
				return isNegative?Integer.MIN_VALUE:Integer.MAX_VALUE;
			}

		}
		return isNegative?-num:num;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().myAtoi("-12a42"));
	}
}