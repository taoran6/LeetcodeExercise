package com.example.leetcode.DP;

/**
 * 博弈问题
 */
public class GameDP {
    /**
     * 你和你的朋友面前有一排石头堆，用一个数组 piles 表示，piles[i] 表示第 i 堆石子有多少个。你们轮流拿石头，
     * 一次拿一堆，但是只能拿走最左边或者 最右边的石头堆。所有石头被拿完后，谁拥有的石头多，谁获胜。
     *
     * 假设两人都很聪明，设计一个算法，返回先手和后手的最后得分(石头 总数)之差
     */
    public int stoneGame(int[] piles) {
        if(piles == null || piles.length == 0) return 0;
        int n = piles.length;

        // dp[i][j][0]表示从i到j的石头堆，先手拥有的石头数，dp[i][j][1]表示后手拥有的石头数
        int[][][] dp = new int[n][n][2];

        for(int j = 0; j < n; j++) {
            for (int i = j; i >= 0; i--) {
                if(i == j) {
                    dp[i][j][0] = piles[i];
                    dp[i][j][1] = 0;
                } else {
                    // 先手选择最左边或最右边的分数
                    int left = dp[i+1][j][1] + piles[i];
                    int right = dp[i][j-1][1] + piles[j];
                    //取最大值
                    if(left > right) {
                        dp[i][j][0] = left;
                        //后手实际上转换成了下一轮的先手
                        dp[i][j][1] = dp[i+1][j][0];
                    }else {
                        dp[i][j][0] = right;
                        dp[i][j][1] = dp[i][j-1][0];
                    }
                }
            }
        }

        return dp[0][n-1][0] - dp[0][n-1][1];
    }
}
