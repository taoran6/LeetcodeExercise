package com.example.leetcode.DP;

import java.util.Arrays;

/**
 * 零钱兑换
 */
public class CoinChange {
    /**
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果
     * 没有任何一种硬币组合能组成总金额，返回 -1。
     *
     * 示例 1:
     *
     * 输入: coins = [1, 2, 5], amount = 11
     * 输出: 3
     * 解释: 11 = 5 + 5 + 1
     * 示例 2:
     *
     * 输入: coins = [2], amount = 3
     * 输出: -1
     * 说明:
     * 你可以认为每种硬币的数量是无限的。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/coin-change
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 方法一：动态规划，自上而下
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange1(int[] coins, int amount) {
        int[] counts = new int[amount + 1];
        //这里需要注意，coins[]不是有序的，不要被示例给蒙蔽了
        return getCoinChange(coins, amount, counts);
    }

    private int getCoinChange(int[] coins, int amount, int[] counts) {
        if(amount == 0) return 0;
        if(amount < coins[0]) return -1;

        if(counts[amount] != 0) return counts[amount];
        int min = Integer.MAX_VALUE;

        //需要遍历一遍找最小值
        for (int coin : coins) {
            int left = getCoinChange(coins, amount - coin, counts);
            if(left >= 0 && left < min)
                min = left + 1;
        }
        counts[amount] = (min == Integer.MAX_VALUE ? -1 : min);
        return counts[amount];
    }

    /**
     * 方法二：TODO 后面再写吧，妈呀今天好累
     */
    public int coinChange2(int[] coins, int amount) {
        return -1;
    }
}
