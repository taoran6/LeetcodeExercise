package com.example.leetcode.DP;

/**
 * 221. 最大正方形
 */
public class MaximalSquare {
    /**
     * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
     *
     * 示例:
     *
     * 输入:
     *
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0
     *
     * 输出: 4
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximal-square
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        //dp[i][j]存储了以matrix[i][j]为右下顶点的最大正方形边长
        int[][] dp = new int[matrix.length][matrix[0].length];

        int max = 0;
        for(int i =0; i< matrix[0].length; i++) {
            if(matrix[0][i] == '1') {
                dp[0][i] = 1;
                max = 1;
            }
        }
        for(int i =1; i< matrix.length; i++) {
            if(matrix[i][0] == '1') {
                dp[i][0] = 1;
                max = 1;
            }
        }

        for(int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if(matrix[i][j] == '1') {
                    //转移方程dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }

        return max * max;
    }

    /**
     * 方法二：只用一维数组存储
     * @param matrix
     * @return
     */
    public int maximalSquare2(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;
        //这里多加一位，就不用单独考虑第一列的情况啦
        int[] dp = new int[matrix[0].length + 1];

        int max = 0;
        int lastDpj;
        for(int i = 0; i < matrix.length; i++) {
            //用一个变量存储上一个dp[j+1]
            lastDpj = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == '1') {
                    int tmp = Math.min(lastDpj, Math.min(dp[j+1], dp[j])) + 1;
                    lastDpj = dp[j + 1];
                    dp[j+1] = tmp;
                    max = Math.max(tmp, max);
                } else {
                    //这里容易出错，等于0的情况要显式的赋值
                    lastDpj = dp[j + 1];
                    dp[j + 1] = 0;
                }
            }
        }
        return max * max;
    }
}
