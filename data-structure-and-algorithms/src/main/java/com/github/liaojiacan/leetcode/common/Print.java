package com.github.liaojiacan.leetcode.common;

import java.util.Arrays;

/**
 * @author liaojiacan
 * @date 2019/2/7
 */
public class Print {

	public static void printMatrix(int[][] matrix){
		System.out.println("----------------Matrix Start-------------------");
		Arrays.stream(matrix).forEach(line->{
			System.out.println(Arrays.toString(line));
		});
		System.out.println("-----------------Matrix End-------------------");

	}
}
