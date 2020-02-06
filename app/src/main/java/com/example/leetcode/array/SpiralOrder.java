package com.example.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 */
public class SpiralOrder {
    /**
     * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
     *
     * 示例 1:
     *
     * 输入:
     * [
     *  [ 1, 2, 3 ],
     *  [ 4, 5, 6 ],
     *  [ 7, 8, 9 ]
     * ]
     * 输出: [1,2,3,6,9,8,7,4,5]
     * 示例 2:
     *
     * 输入:
     * [
     *   [1, 2, 3, 4],
     *   [5, 6, 7, 8],
     *   [9,10,11,12]
     * ]
     * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/spiral-matrix
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public List<Integer> spiralOrder(int[][] matrix) {
        //这个解法好费脑子啊~ ~ ~ ~ ~边界条件很容易出错
        List<Integer> ans = new ArrayList<>();
        int m = matrix.length;
        if(m == 0) return ans;
        int n = matrix[0].length;
        int shortLen = Math.min(m, n);
        if(shortLen == 0) return ans;

        for(int i = 0; i <= (shortLen - 1) / 2 ;i ++) {
            for(int j = i; j < n - i; j ++) {
                ans.add(matrix[i][j]);
            }

            for(int j = i + 1; j < m - i; j++) {
                ans.add(matrix[j][n - i - 1]);
            }

            //最后只剩下一行或一列提前结束
            if(m - i - 1 == i || n - i - 1 == i) break;

            for(int j = n - i - 2; j >= i; j--) {
                ans.add(matrix[m - i - 1][j]);
            }
            for(int j = m - i - 2; j > i; j--) {
                ans.add(matrix[j][i]);
            }
        }

        return ans;
    }

    /**
     * 解法二：循环次数比上一个方法多一次循环
     */
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> ans = new ArrayList<>();
        if(matrix.length == 0 || matrix[0].length == 0) return ans;

        int row = matrix.length;
        int col = matrix[0].length;
        int n = row > col ? col : row;

        //这里循环次数(n + 1)/2而不是（n/2+ 1）
        for(int i = 0; i < (n + 1)/2; i++){
            for(int j = i; j < col-i; j++) ans.add(matrix[i][j]);
            //已是最后一行，提前结束
            if((row - i * 2) == 1) return ans;
            for(int j = i+1; j < row-i; j++) ans.add(matrix[j][col-i-1]);
            //已是最后一列，提前结束
            if((col - i * 2) == 1) return ans;
            for(int j = col-i-2; j >= i; j--) ans.add(matrix[row-i-1][j]);  //这个边界别错了
            for(int j = row-i-2; j > i; j--) ans.add(matrix[j][i]);
        }

        return ans;
    }

    /**
     * 螺旋矩阵 II
     *
     * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
     *
     * 示例:
     *
     * 输入: 3
     * 输出:
     * [
     *  [ 1, 2, 3 ],
     *  [ 8, 9, 4 ],
     *  [ 7, 6, 5 ]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/spiral-matrix-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int count = 1;
        int n2 = n * n;
        int i = 0; int j = 0;

        //螺旋迭代是状态，一共有四种
        int state = 0;
        while (count <= n2) {
            switch (state) {
                case 0:     //从左往右
                    ans[i][j ++] = count ++;
                    if(j == n || ans[i][j] != 0) {
                        state = 1;
                        i++; j--;
                    }
                break;
                case 1:     //从上往下
                    ans[i ++][j] = count ++;
                    if(i == n || ans[i][j] != 0) {
                        state = 2;
                        i--; j--;
                    }
                break;
                case 2:     //从右往左
                    ans[i][j --] = count ++;
                    if(j == -1 || ans[i][j] != 0) {
                        state = 3;
                        i--; j++;
                    }
                break;
                case 3:     //从下往上
                    ans[i --][j] = count ++;
                    if(i == -1 || ans[i][j] != 0) {
                        state = 0;
                        i++; j++;
                    }
            }
        }
        return ans;
    }

}
