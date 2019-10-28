package com.example.leetcode.stack;

import java.util.Stack;

/**
 * 柱状图中最大的矩形
 */
public class LargestRectangleArea {
    /**
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

}
