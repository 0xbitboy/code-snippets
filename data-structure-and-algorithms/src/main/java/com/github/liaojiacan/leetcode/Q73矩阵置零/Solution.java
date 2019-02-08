package com.github.liaojiacan.leetcode.Q73矩阵置零;

import com.github.liaojiacan.leetcode.common.Print;

/**
 * @author liaojiacan
 * @date 2019/2/7
 */
class Solution {
	public void setZeroes(int[][] matrix) {
		// 扫描一遍矩阵，将需要处理的行和列标记在第一行和第一列。
		if(matrix == null ){
			return;
		}
		// 标记第一列是否需要被置0，matrix[0][0] 用于标记第一行是否需要置0
		boolean replaceFirstCol = false;
		for(int i=0;i<matrix.length ;++i){
			//说明定义列需要被置0
			if(matrix[i][0] == 0){
				replaceFirstCol = true;
			}

			for(int j =1;j<matrix[i].length;++j){
				if(matrix[i][j] == 0){
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}

		for(int i=1 ; i<matrix.length ;++i){
			for(int j=1 ; j<matrix[i].length ;++j){
				if(matrix[i][0] == 0 || matrix[0][j] == 0){
					matrix[i][j] = 0;
				}
			}
		}

		if(matrix[0][0] == 0){
			for(int i = 0; i<matrix[0].length;++i){
				matrix[0][i] = 0;
			}
		}

		if(replaceFirstCol){
			for(int i = 0; i<matrix.length;++i){
				matrix[i][0] = 0;
			}
		}

	}

	public static void main(String[] args) {
		int[][] matrix = new int[][]{
				{1,1,1},
				{1,0,1},
				{0,1,1}};
		new Solution().setZeroes(matrix);
		Print.printMatrix(matrix);
	}
}
