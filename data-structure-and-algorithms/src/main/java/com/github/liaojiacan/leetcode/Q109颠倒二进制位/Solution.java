package com.github.liaojiacan.leetcode.Q109颠倒二进制位;

/**
 * @author liaojiacan
 * @date 2019/2/2
 */
public class Solution {
	// you need treat n as an unsigned value
	public int reverseBits(int n) {
		int temp = n;
		if(n>0){
			n |= (1<<31);
		}
		int result = 0;
		while(n!=0){
			result = ((result<<1)+(n & 1));
			n >>>= 1;
		}
		return temp>0?result-1:result;
	}
}