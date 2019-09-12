package com.example.leetcode.DP;

import java.util.Arrays;

/**
 * 不同路径
 */
public class UniquePaths {
    /**
     * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
     *
     * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
     *
     * 问总共有多少条不同的路径？
     *
     * 说明：m 和 n 的值均不超过 100。
     *
     * 示例 1:
     *
     * 输入: m = 3, n = 2
     * 输出: 3
     * 解释:
     * 从左上角开始，总共有 3 条路径可以到达右下角。
     * 1. 向右 -> 向右 -> 向下
     * 2. 向右 -> 向下 -> 向右
     * 3. 向下 -> 向右 -> 向右
     * 示例 2:
     *
     * 输入: m = 7, n = 3
     * 输出: 28
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/unique-paths
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：空间复杂度O(n^2)
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        if(m < 1 || n < 1) return 0;
        int[][] num = new int[m + 1][n + 1];
        num[1][1] = 1;
        for(int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                num[i][j] += num[i - 1][j] + num[i][j-1];
            }
        }
        return num[m][n];
    }

    /**
     * 方法二：空间复杂度O(n)
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths2(int m, int n) {
        if(m < 1 || n < 1) return 0;
        int[] nums = new int[n];
        Arrays.fill(nums, 1);
        for (int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                nums[j] += nums[j - 1];
            }
        }
        return nums[n - 1];
    }

}
