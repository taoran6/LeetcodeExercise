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
        //Arrays.fill() 这个方法很有用
        Arrays.fill(nums, 1);
        for (int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                nums[j] += nums[j - 1];
            }
        }
        return nums[n - 1];
    }

    /**
     * 最小路径和
     *
     * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     *
     * 说明：每次只能向下或者向右移动一步。
     *
     * 示例:
     *
     * 输入:
     * [
     *   [1,3,1],
     *   [1,5,1],
     *   [4,2,1]
     * ]
     * 输出: 7
     * 解释: 因为路径 1→3→1→1→1 的总和最小。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-path-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        if(m == 0) return 0;
        int n = grid[0].length;
        if(n == 0) return 0;

        //用一个二维数组存储最小值
        int[][] min = new int[m][n];
        min[0][0] = grid[0][0];
        for(int i = 1; i < n; i++) min[0][i] = min[0][i - 1] + grid[0][i];
        for(int j =1; j < m; j++) min[j][0] = min[j -1][0] + grid[j][0];

        for(int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                min[i][j] = Math.min(min[i - 1][j], min[i][j - 1]) + grid[i][j];
            }
        }
        return min[m - 1][n - 1];
    }

    /**
     * 方法二：将最小值就地保存到grid中，代码略
     *
     * 方法三：用一维数组存储
     * @param grid
     * @return
     */
    public int minPathSum2(int[][] grid) {
        int m = grid.length;
        if(m == 0) return 0;
        int n = grid[0].length;
        if(n == 0) return 0;

        int[] min = new int[n];
        min[0] = grid[0][0];
        for (int i = 1; i < n; i++) min[i] = min[i - 1] + grid[0][i];
        for (int i = 1; i < m; i++) {
            min[0] += grid[i][0];
            for (int j = 1; j < n; j++) {
                min[j] = Math.min(min[j], min[j - 1]) + grid[i][j];
            }
        }
        return min[n - 1];
    }

    /**
     * 在二维平面上，有一个机器人从原点 (0, 0) 开始。给出它的移动顺序，判断这个机器人在完成移动后是否在 (0, 0)
     * 处结束。
     *
     * 移动顺序由字符串表示。字符 move[i] 表示其第 i 次移动。机器人的有效动作有 R（右），L（左），U（上）和
     * D（下）。如果机器人在完成所有动作后返回原点，则返回 true。否则，返回 false。
     *
     * 注意：机器人“面朝”的方向无关紧要。 “R” 将始终使机器人向右移动一次，“L” 将始终向左移动等。此外，假设每次
     * 移动机器人的移动幅度相同。
     *
     *  
     *
     * 示例 1:
     *
     * 输入: "UD"
     * 输出: true
     * 解释：机器人向上移动一次，然后向下移动一次。所有动作都具有相同的幅度，因此它最终回到它开始的原点。因此，我
     * 们返回 true。
     * 示例 2:
     *
     * 输入: "LL"
     * 输出: false
     * 解释：机器人向左移动两次。它最终位于原点的左侧，距原点有两次 “移动” 的距离。我们返回 false，因为它在移动
     * 结束时没有返回原点。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/robot-return-to-origin
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param moves
     * @return
     */

    public boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;
        for (char i : moves.toCharArray()) {
            switch (i) {
                case 'U': y++; break;
                case 'D': y--; break;
                case 'L': x--; break;
                case 'R': x++; break;
            }
        }
        return x==0 && y==0;
    }
    public boolean judgeCircle2(String moves) {
        int[] cnt = new int[26];
        for(char c : moves.toCharArray()) cnt[c-'A']++;
        return cnt['L'-'A']==cnt['R'-'A'] && cnt['U'-'A']==cnt['D'-'A'];
    }
}
