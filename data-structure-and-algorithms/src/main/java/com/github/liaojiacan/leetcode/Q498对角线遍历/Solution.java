package com.github.liaojiacan.leetcode.Q498对角线遍历;

import java.util.Arrays;

/**
 * @author liaojiacan
 * @date 2019/3/8
 */
class Solution {
public int[] findDiagonalOrder(int[][] matrix) {
	int N = matrix.length -1;
	if(N <0){
		return new int[0];
	}
	int M = matrix[0].length -1;
	int x =0 ,y=0;
	int[] ans = new int[(N+1)*(M+1)];
	int k = 0;
	int[][] vector=new int[][]{{-1,1},{1,0},{1,-1},{0,1}};
	int direct = 0;
	while(true){
		ans[k++] = matrix[x][y];
		if(x == N && y==M){
			return ans;
		}
		x+=vector[direct][0];
		y+=vector[direct][1];
		// 如果越界了 进行转弯
		while(x<0 || x>N || y<0 || y>M){
			direct = (direct+1) % 4;
			x+=vector[direct][0];
			y+=vector[direct][1];
		}
		// 遍历过程必须保证是斜角线方向
		if(direct!=0 && direct !=2){
			direct = (direct+1) % 4;
		}
	}
}

	public static void main(String[] args) {
		int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
		System.out.println(Arrays.toString(new Solution().findDiagonalOrder(matrix)));
	}
}