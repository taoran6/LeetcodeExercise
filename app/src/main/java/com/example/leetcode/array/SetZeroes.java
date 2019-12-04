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

    /**
     * 1089. 复写零
     *
     * 给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
     *
     * 注意：请不要在超过该数组长度的位置写入元素。
     *
     * 要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：[1,0,2,3,0,4,5,0]
     * 输出：null
     * 解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]
     * 示例 2：
     *
     * 输入：[1,2,3]
     * 输出：null
     * 解释：调用函数后，输入的数组将被修改为：[1,2,3]
     *  
     *
     * 提示：
     *
     * 1 <= arr.length <= 10000
     * 0 <= arr[i] <= 9
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/duplicate-zeros
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public void duplicateZeros(int[] arr) {
        // 统计需要复写0的个数
        int count = 0;
        int i = 0;
        for (; i + count < arr.length; i++) {
            if(arr[i] == 0) {
                count++;
            }
        }

        //双指针，从后往前
        int left = arr.length - 1 - count;
        int right = arr.length - 1;

        //这里需要注意，最后一个0没有复写空间的情况
        //比如[3,0,1,0,1] 复写后变成[3,0,0,1,0]，但是count是等于2的
        if(i + count > arr.length) {
            arr[right --] = 0;
        }
        while (left >=0 && left != right) {
            if(arr[left] == 0) {
                arr[right --] = 0;
            }
            arr[right --] = arr[left --];
        }
    }
}
