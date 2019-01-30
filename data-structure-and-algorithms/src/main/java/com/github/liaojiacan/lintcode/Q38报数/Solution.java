package com.github.liaojiacan.lintcode.Q38报数;

/**
 * @author liaojiacan
 * @date 2019/1/30
 */
class Solution {

	public String countAndSay(int n) {
		if(n == 1){
			return "1";
		}
		return countAndSay(countAndSay(n-1));
	}

	/**
	 * 2个指针扫描，直到不值不相等的时候2指针的差值就是报数的个数。
	 * 1 1 1 2 2 1
	 * ↑     ↑
	 * p1    p2 -> p2-p1=3-0=3 -> 3个1
	 * @param originalStr
	 * @return
	 */
	private String countAndSay(String originalStr){
		StringBuilder sb = new StringBuilder(originalStr.length());
		int p1 = 0;
		int p2 = 0;
		while(p1<originalStr.length()){
			if(p2<originalStr.length() && originalStr.charAt(p1) == originalStr.charAt(p2)){
				p2++;
				continue;
			}
			int count = p2-p1;
			sb.append(count).append(originalStr.charAt(p1));
			p1 = p2;
		}
		return sb.toString();
	}
}