package com.example.leetcode.DP;

/**
 * 爬楼梯
 */
public class ClimbStairs {
    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     *
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * 注意：给定 n 是一个正整数。
     *
     * 示例 1：
     *
     * 输入： 2
     * 输出： 2
     * 解释： 有两种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶
     * 2.  2 阶
     * 示例 2：
     *
     * 输入： 3
     * 输出： 3
     * 解释： 有三种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶 + 1 阶
     * 2.  1 阶 + 2 阶
     * 3.  2 阶 + 1 阶
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/climbing-stairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     *
     *
     * 斐波拉契数列
     */
    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int[] num = new int[n + 1];
        num[1] = 1;
        num[2] = 2;
        //这里使用循环而不是递归 return climbStairs(n-1) + climbStairs(n-2) 的原因是会超时（\笑哭）
        for (int i= 3; i <= n; i++) {
            num[i] = num[i-1] + num[i-2];
        }
        return num[n];
    }

    /**
     * 1137. 第 N 个泰波那契数
     * @param n
     * @return
     */
    public int tribonacci(int n) {
        if(n == 0) return 0;

        int n0 = 0;
        int n1 = 1;
        int n2 = 1;
        for (int i = 3; i <= n; i++) {
            int tmp = n1 + n2 + n0;
            n0 = n1;
            n1 = n2;
            n2 = tmp;
        }
        return n2;
    }

    /**
     * 746. 使用最小花费爬楼梯
     *
     * 数组的每个索引做为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
     *
     * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
     *
     * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
     *
     * 示例 1:
     *
     * 输入: cost = [10, 15, 20]
     * 输出: 15
     * 解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
     *  示例 2:
     *
     * 输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
     * 输出: 6
     * 解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
     * 注意：
     *
     * cost 的长度将会在 [2, 1000]。
     * 每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int minCostClimbingStairs(int[] cost) {
        int length = cost.length;
        int[] dp = new int[length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < length; i++) {
            dp[i] = Math.min(dp[i-2] , dp[i-1]) + cost[i];
        }
        return Math.min(dp[length-1], dp[length-2]);
    }
}
