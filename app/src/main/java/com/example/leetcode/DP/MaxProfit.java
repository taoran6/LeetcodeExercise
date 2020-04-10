package com.example.leetcode.DP;

/**
 * 买卖股票的最佳时机
 */
public class MaxProfit {
    /**
     * 买卖股票的最佳时机i
     *
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     *
     * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
     *
     * 注意你不能在买入股票前卖出股票。
     *
     * 示例 1:
     *
     * 输入: [7,1,5,3,6,4]
     * 输出: 5
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
     * 示例 2:
     *
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if(prices == null) return 0;
        int min = Integer.MAX_VALUE;
        int ans = 0;
        for (int i = 0; i < prices.length; i++) {
            if(prices[i] < min) min = prices[i];        //遇到最小值不用再做后面的比较了
            else ans = Math.max(ans, prices[i] - min);
        }
        return ans;
    }

    /**
     * 买卖股票的最佳时机ii
     *
     * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 示例 1:
     *
     * 输入: [7,1,5,3,6,4]
     * 输出: 7
     * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得
     * 利润 = 5-1 = 4 。随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出,
     * 这笔交易所能获得利润 = 6-3 = 3 。
     * 示例 2:
     *
     * 输入: [1,2,3,4,5]
     * 输出: 4
     * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得
     * 利润 = 5-1 = 4 。注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔
     * 交易，你必须在再次购买前出售掉之前的股票。
     * 示例 3:
     *
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *
     * 现实中不存在这样的操作哈哈哈哈哈
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            int sub = prices[i] - prices[i - 1];
            //相当于每天都可以操作，只要能获利就前一天买入第二题卖出
            if(sub > 0) ans += sub;
        }
        return ans;
    }

    /**
     * 309. 最佳买卖股票时机含冷冻期
     *
     * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
     *
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     *
     * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * 示例:
     *
     * 输入: [1,2,3,0,2]
     * 输出: 3
     * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 以为是普通的动态规划实际上是状态机，所以自己想出来的解题思路测试一直不对，看了题解才知道：
     * 每天可能存在三种状态：
     *
     * hold：继续持有股票
     * sold：卖出股票
     * rest：什么都不做
     * 转换图可以看链接
     *
     * hold： 可由两个情况转换来
     * 前一天hold，当日rest
     * 前一天rest，当日买入股票变为hold
     *
     * sold：
     * 前一天hold，当日卖出股票
     *
     * rest：
     * 前一天sold，当日必须rest
     * 前一天rest，当日继续rest
     *
     * 最后一天最大值情况为要么什么都不做，要么卖出股票，即 max(sold，rest)
     *
     * 作者：guohaoding
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/309-zui-jia-mai-mai-gu-piao-shi-ji-han-leng-dong-q/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int maxProfitCold(int[] prices) {
        int n = prices.length;
        if(n <= 1) return 0;

        int[] hold = new int[n];
        int[] sold = new int[n];
        int[] rest = new int[n];
        hold[0] = - prices[0];

        for (int i = 1; i < prices.length; i++) {
            hold[i] = Math.max(rest[i - 1] - prices[i], hold[i-1]);
            sold[i] = hold[i-1] + prices[i];
            rest[i] = Math.max(sold[i-1], rest[i-1]);
        }
        return Math.max(sold[n-1], rest[n-1]);

    }

    /**
     * 123. 买卖股票的最佳时机 III
     *
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     *
     * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 示例 1:
     *
     * 输入: [3,3,5,0,0,3,1,4]
     * 输出: 6
     * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
     *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
     * 示例 2:
     *
     * 输入: [1,2,3,4,5]
     * 输出: 4
     * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。  
     *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。  
     *      因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
     * 示例 3:
     *
     * 输入: [7,6,4,3,1]
     * 输出: 0
     * 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int maxProfitIII(int[] prices) {
        if(prices.length == 0) return 0;

        //这里是k = 2的买卖股票的最佳时机 IV的特殊情况，省略了k=0时的空间
        int[][][] dp = new int[prices.length][2][2];
        dp[0][0][1] = - prices[0];
        dp[0][1][1] = - prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0][0] = Math.max(dp[i-1][0][0], dp[i-1][0][1] + prices[i]);
            dp[i][0][1] = Math.max(dp[i-1][0][1], - prices[i]);
            dp[i][1][0] = Math.max(dp[i-1][1][0], dp[i-1][1][1] + prices[i]);
            dp[i][1][1] = Math.max(dp[i-1][1][1], dp[i-1][0][0] - prices[i]);
        }
        return dp[prices.length - 1][1][0];
    }

    /**
     * 188. 买卖股票的最佳时机 IV
     *
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     *
     * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * 示例 1:
     *
     * 输入: [2,4,1], k = 2
     * 输出: 2
     * 解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得
     * 利润 = 4-2 = 2 。
     * 示例 2:
     *
     * 输入: [3,2,6,5,0,3], k = 2
     * 输出: 7
     * 解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得
     * 利润 = 6-2 = 4 。
     * 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得
     * 利润 = 3-0 = 3 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 参考 https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/solution/yi-ge-tong-yong-fang-fa-tuan-mie-6-dao-gu-piao-w-5/
     */
    public int maxProfitIV(int k, int[] prices) {
        if(k <= 0 || prices.length == 0) return 0;

        // k 可以是无限大，但是一天只能买进或卖出，实际操作次数最多是天数的一半。否则后面new dp[]的时候会超内存
        if(k > prices.length / 2) {
            int ans = 0;
            for(int i = 1; i < prices.length; i++) {
                if(prices[i] > prices[i-1]) {
                    ans += prices[i] - prices[i-1];
                }
            }
            return ans;
        }

        // dp[i][j][0]表示第i天进行过最多j次交易后手中不持有股票的最大利润，dp[i][j][1]是持有股票的最大利润
        int[][][] dp = new int[prices.length][k+1][2];

        //这里初始化 无论k是多少，第一天持有一定是 -prices[0](其实我还是不明白为啥要这样初始化啊)
        for(int i = 1; i <= k; i++) {
            dp[0][i][1] = -prices[0];
        }


        for (int i = 1; i < prices.length; i++) {
            for(int j = 1; j <= k; j++) {
                // dp[i-1][j][0]表示前一天已抛出，今日什么都不做；dp[i-1][j][1] + prices[i]表示前一天持有，今日抛出
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i]);
                //dp[i-1][j-1][0] - prices[i]表示前一天已抛出，今日买入；dp[i-1][j][1]表示前一天已买入，今日什么都不做
                dp[i][j][1] = Math.max(dp[i-1][j-1][0] - prices[i], dp[i-1][j][1]);
                //大致明白了这样初始化，即使在操作次数大于天数的日子可以保证dp[i][j][]跟dp[i][j-1][]相等
            }
        }

        return dp[prices.length - 1][k][0];
    }
}
