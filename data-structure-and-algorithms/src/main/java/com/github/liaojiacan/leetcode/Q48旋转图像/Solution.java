package com.github.liaojiacan.leetcode.Q48旋转图像;

import java.util.Arrays;

/**
 * @author liaojiacan
 * @date 2019/1/29
 */
class Solution {
	public void rotate(int[][] matrix) {
		//将矩阵分解成nxn,n-2 x n-2 ,... 1x1 的 n/2个圈进行处理。
		int level = 0;
		//表示最大的层次
		int maxLevel = matrix.length/2;
		//表示矩阵的边框[0,k]
		int k = matrix.length-1;

		while(level < maxLevel ){

			for(int i=level;i<k;++i){
				swap(matrix,level,i,i,k);
				swap(matrix,level,i,k,k-i+level);
				swap(matrix,level,i,k-i+level,level);
			}
			++level;
			--k;
		}

	}

	private void swap(int[][] matrix,int point1X,int point1Y,int point2X,int point2Y){
		int temp = matrix[point1X][point1Y];
		matrix[point1X][point1Y] = matrix[point2X][point2Y];
		matrix[point2X][point2Y] = temp;
	}

	public static void main(String[] args) {

		int MATRIX_SIZE = 4;

		int[][] matrix = new int[MATRIX_SIZE][MATRIX_SIZE];
		int count = 0;
		for(int i=0; i<MATRIX_SIZE ; ++i){
			for(int j=0; j<MATRIX_SIZE ; ++j){
				matrix[i][j] = count++;
			}
		}
		System.out.println("----------------Original Matrix-------------------");
		printMatrix(matrix);
		System.out.println("----------------Original Matrix End-------------------");
		System.out.println("----------------Rotated Matrix-------------------");
		new Solution().rotate(matrix);
		printMatrix(matrix);
		System.out.println("----------------Rotated End-------------------");



	}

	private static void printMatrix(int[][] matrix){
		Arrays.stream(matrix).forEach(line->{
			System.out.println(Arrays.toString(line));
		});
	}
}