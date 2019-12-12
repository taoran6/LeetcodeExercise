package com.example.leetcode.search;

/**
 * 搜索二维矩阵
 */
public class SearchMatrix {
    /**
     * 240.搜索二维矩阵 II
     *
     * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
     *
     * 每行的元素从左到右升序排列。
     * 每列的元素从上到下升序排列。
     * 示例:
     *
     * 现有矩阵 matrix 如下：
     *
     * [
     *   [1,   4,  7, 11, 15],
     *   [2,   5,  8, 12, 19],
     *   [3,   6,  9, 16, 22],
     *   [10, 13, 14, 17, 24],
     *   [18, 21, 23, 26, 30]
     * ]
     * 给定 target = 5，返回 true。
     *
     * 给定 target = 20，返回 false。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：左下角的元素是这一行中最小的元素，同时又是这一列中最大的元素。比较左下角元素和目标：
     * 若左下角元素等于目标，则找到
     * 若左下角元素大于目标，则目标不可能存在于当前矩阵的最后一行，问题规模可以减小为在去掉最后一行的子矩阵中寻找目标
     * 若左下角元素小于目标，则目标不可能存在于当前矩阵的第一列，问题规模可以减小为在去掉第一列的子矩阵中寻找目标
     * 若最后矩阵减小为空，则说明不存在
     *
     * 可以从左下角或者右上角开始遍历
     * 时间复杂度为O(m+n)
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0 || matrix[0].length == 0) return false;

        int i = matrix.length - 1;
        int j = 0;

        while (i >= 0 && j < matrix[0].length) {
            if(matrix[i][j] == target) return true;
            if(matrix[i][j] < target) j++;
            else i--;
        }
        return false;
    }

    /**
     * 方法二：二分查找
     * 无他，就是一行行的二分查找，时间复杂度O(mlogn)执行时间竟然比上面这个快
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length - 1;
        for(int i = matrix[0].length - 1; i >= 0; i--) {
            if(matrix[0][i] <= target) {
                // int start = matrix[0][i];
                int start = 0;
                int end = row;
                // int end = matrix[row][i];
                while(start + 1 < end) {
                    int mid = start + (end - start) / 2;
                    if(matrix[mid][i] == target) {
                        return true;
                    }else if(matrix[mid][i] < target) {
                        start = mid;
                    }else {
                        end = mid;
                    }
                }
                if(matrix[start][i] == target || matrix[end][i] == target) {
                    return true;
                }
            }
        }
        return false;
    }
}
