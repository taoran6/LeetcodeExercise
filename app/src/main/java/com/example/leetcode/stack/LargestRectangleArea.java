package com.example.leetcode.stack;

import java.util.List;
import java.util.Stack;

public class LargestRectangleArea {
    /**
     * 84. 柱状图中最大的矩形
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     *
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     *
     * 示例:
     *
     * 输入: [2,1,5,6,2,3]
     * 输出: 10
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：暴力法
     * 执行用时 306ms 最坏情况O(n^2)
     *
     * 第 i 位置最大面积是以i 为中心，向左找第一个小于 heights[i] 的位置 left_i；向右找第一个小于 heights[i]
     * 的位置 right_i，即最大面积为 heights[i] * (right_i - left_i -1)
     */
    public int largestRectangleArea1(int[] heights) {
        int res = 0;

        for(int i = 0; i < heights.length; i++) {
            int left = 0;
            for(int j = i - 1; j >= 0; j--) {
                if(heights[j] < heights[i]) {
                    left = j + 1;
                    break;
                }
            }

            int right = heights.length - 1;
            for (int j = i + 1; j < heights.length; j++) {
                if(heights[j] < heights[i]) {
                    right = j - 1;
                    break;
                }
            }

            res = Math.max(heights[i] * (right - left + 1), res);
        }
        return res;
    }

    /**
     * 方法二：使用单调递增的栈,用于确定左右边界
     *
     * 单调递增的栈里存的肯定是左边界
     *
     * 时间复杂度O(n)
     * 空间复杂是O(n)
     */
    public int largestRectangleArea2(int[] heights) {
        int res = 0;
        Stack<Integer> stack = new Stack<>();       //存储的是下标

        for(int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {   //满足此条件说明i - 1一定是右边界
                int pos = stack.pop();

                //左边界为left, 右边界为 i
                int left = stack.isEmpty() ? -1 : stack.peek();
                res = Math.max(res, heights[pos] * (i - 1 - left));

            }

            stack.push(i);
        }

        //还剩最后一个heights[heights.length - 1]
        while (!stack.isEmpty()) {
            int pos = stack.pop();
            //左边界为left, 右边界为 heights.length - 1
            int left = stack.isEmpty() ? -1 : stack.peek();
            res = Math.max(res, heights[pos] * (heights.length - 1 - left));
        }

        return res;
    }



    /**
     * 85. 最大矩形
     *
     * 给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
     *
     * 示例:
     *
     * 输入:
     * [
     *   ["1","0","1","0","0"],
     *   ["1","0","1","1","1"],
     *   ["1","1","1","1","1"],
     *   ["1","0","0","1","0"]
     * ]
     * 输出: 6
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximal-rectangle
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 套用了84题的解法去解，就是求出每一层的 heights[] 然后传给上一题的函数就可以了,注意这题解法跟
     * 221题求最大正方形面积 不同，虽然题目很相似
     *
     * 参考https://leetcode-cn.com/problems/maximal-rectangle/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-1-8/
     * 的解法二
     */
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0 || matrix[0].length == 0) return 0;

        int[] heights = new int[matrix[0].length];
        int ans = 0;
        for (int i = 0; i < matrix.length; i++) {
            // 更新heights[]
            for(int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == '1') {
                    heights[j] ++;
                } else {
                    heights[j] = 0;
                }
            }

            ans = Math.max(largestRectangleArea2(heights), ans);
        }
        return ans;
    }


}
