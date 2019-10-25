package com.example.leetcode.array;

/**
 * 矩阵置零
 */
public class SetZeroes {
    /**
     * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
     *
     * 示例 1:
     *
     * 输入:
     * [
     *   [1,1,1],
     *   [1,0,1],
     *   [1,1,1]
     * ]
     * 输出:
     * [
     *   [1,0,1],
     *   [0,0,0],
     *   [1,0,1]
     * ]
     * 示例 2:
     *
     * 输入:
     * [
     *   [0,1,2,0],
     *   [3,4,5,2],
     *   [1,3,1,5]
     * ]
     * 输出:
     * [
     *   [0,0,0,0],
     *   [0,4,5,0],
     *   [0,3,1,0]
     * ]
     * 进阶:
     *
     * 一个直接的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
     * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
     * 你能想出一个常数空间的解决方案吗？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/set-matrix-zeroes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        /**
         * 本方法使用O(1)空间
         * 使用matrix[0][j]标记整个第j列都为0，使用matrix[i][0]标记整个第i行都为0
         */
        int m = matrix.length;
        if(m == 0) return;
        int n = matrix[0].length;
        if(n == 0) return;

        //记录首行是否需要被置0
        boolean flagFirstRow = false;
        //记录首列是否需要被置0
        boolean flagFirstCol = false;

        if(matrix[0][0] == 0) {
            //这里需要对matrix[0][0]做特殊处理，否则会由于后面的操作使整个矩阵都置0
            matrix[0][0] = 1;
            flagFirstCol = true;
            flagFirstRow = true;
        } else {
            for (int i = 0; i < m; i++) {
                if(matrix[i][0] == 0) {
                    flagFirstCol  =true;
                    break;
                }
            }

            for (int j = 0; j < n; j++) {
                if(matrix[0][j] == 0) {
                    flagFirstRow = true;
                    break;
                }
            }
        }

        //注意这里是从 i = 1, j = 1 开始的
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            //对matrix[0][0]做特殊处理的话这个地方会使整个第0行都为0
            if(matrix[i][0] == 0) {
                for (int j = 1; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for(int j = 0; j < n; j++) {
            if(matrix[0][j] == 0) {
                for(int i = 1; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        if(flagFirstRow) {
            for(int i = 0; i < n; i++) matrix[0][i] = 0;
        }
        if(flagFirstCol) {
            for(int i = 0; i < m; i++) matrix[i][0] = 0;
        }
    }
}
