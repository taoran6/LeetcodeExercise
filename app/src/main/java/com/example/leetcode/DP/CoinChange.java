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
     * 方法二：动态规划，自下而上
     */
    public int coinChange2(int[] coins, int amount) {
        if(amount < 0) return -1;

        // dp[i]存储凑成总金额i最少需要的硬币数
        int[] dp = new int[amount + 1];
        //初始化为MAX_VALUE，方便后面判断
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 1; i <= amount; i++) {
            for (int coin: coins) {
                if((i - coin) < 0 || dp[i - coin] == Integer.MAX_VALUE) continue;
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    /**
     * 518. 零钱兑换 II
     *
     * 给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 
     *
     *  
     *
     * 示例 1:
     *
     * 输入: amount = 5, coins = [1, 2, 5]
     * 输出: 4
     * 解释: 有四种方式可以凑成总金额:
     * 5=5
     * 5=2+2+1
     * 5=2+1+1+1
     * 5=1+1+1+1+1
     * 示例 2:
     *
     * 输入: amount = 3, coins = [2]
     * 输出: 0
     * 解释: 只用面额2的硬币不能凑成总金额3。
     * 示例 3:
     *
     * 输入: amount = 10, coins = [10]
     * 输出: 1
     *  
     *
     * 注意:
     *
     * 你可以假设：
     *
     * 0 <= amount (总金额) <= 5000
     * 1 <= coin (硬币面额) <= 5000
     * 硬币种类不超过 500 种
     * 结果符合 32 位符号整数
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/coin-change-2
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int change(int amount, int[] coins) {
        if(coins.length == 0) return 0;


        //dp[i]表示取当前个coin，组成i金额有多少种可能
        int[] dp = new int[amount+1];
        dp[0] = 1;

        //每次只考虑前i个硬币
        for (int i = 0; i < coins.length; i++) {
            int coin = coins[i];
            //根据金额遍历
            for (int j = coin; j <= amount; j++) {
                dp[j] += dp[j - coin];
            }
        }

        return dp[amount];
    }

    /**
     * 标准二维dp
     * @param amount
     * @param coins
     * @return
     */
    public int change2(int amount, int[] coins) {
        if(amount == 0) return 1;
        if(coins == null || coins.length == 0) return 0;

        //dp[i][j]表示在前i种硬币中凑出j金额有几种凑法
        int[][] dp = new int[coins.length + 1][amount + 1];
        //初始化，如果凑出的目标金额为 0，那么“无为而治”就是唯一的一种凑法。
        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if(j - coins[i-1] < 0) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    //这里是加号，用到了j层的结果
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                }
            }
        }

        return dp[coins.length][amount];
    }

    /**
     * 化简为一维dp
     */
    public int change3(int amount, int[] coins) {
        if(amount == 0) return 1;
        if(coins == null || coins.length == 0) return 0;

        //dp[j]表示在凑出j金额有几种凑法
        int[] dp = new int[amount + 1];
        //初始化，如果凑出的目标金额为 0，那么“无为而治”就是唯一的一种凑法。
        dp[0] = 1;

        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if(j - coins[i-1] >= 0) {
                    dp[j] = dp[j] + dp[j - coins[i-1]];
                }
            }
        }

        return dp[amount];
    }
}
