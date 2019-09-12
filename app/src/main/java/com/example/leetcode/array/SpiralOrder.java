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
}
