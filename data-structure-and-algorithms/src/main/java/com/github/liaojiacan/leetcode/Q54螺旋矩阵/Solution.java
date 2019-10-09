package com.github.liaojiacan.leetcode.Q54螺旋矩阵;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 *
 * @author liaojiacan
 * @date 2019/9/30
 * @desc
 */
public class Solution {


    public final static int[][] offsets = {
            {0, 1}, {1, 0},
            {0, -1}, {-1, 0}
    };

    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix.length == 0){
            return Collections.emptyList();
        }
        List<Integer> ans = new ArrayList<>(matrix.length * matrix[0].length);
        int N = matrix.length;
        int M = matrix[0].length;
        int k = 0, i = 0, j = 0;
        int[][] mem = new int[N][M];
        while (true) {
            ans.add(matrix[i][j]);
            //标记已经走过
            mem[i][j] = 1;
            //在当前方向上继续走一步，看是否满足条件，不满足条件则转向。
            int nextI = i + offsets[k][0];
            int nextJ = j + offsets[k][1];
            //需要轉向
            int count = 1;
            while (count <= 4 && ( (nextI < 0 || nextI >= N) || (nextJ < 0 || nextJ >= M) || mem[nextI][nextJ] == 1)) {
                k = (k + 1) % 4;
                nextI = i + offsets[k][0];
                nextJ = j + offsets[k][1];
                count++;
            }
            //说明是已经到尽头了。
            if (count >= 4) {
                return ans;
            }

            i = nextI;
            j = nextJ;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3,10},
                {4, 5, 6,11},
                {7, 8, 9,12}
        };
        List<Integer> ans = new Solution().spiralOrder(matrix);
        System.out.println(String.join(",", ans.stream().map(String::valueOf).collect(Collectors.toList())));
    }
}
