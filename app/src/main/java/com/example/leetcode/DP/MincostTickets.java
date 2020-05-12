package com.example.leetcode.DP;

public class MincostTickets {
    public int mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[366];
        int index = 0;
        for(int i = 1; i <= 365; i++) {
            if(i < days[index]) {
                dp[i] = dp[i-1];
            } else {
                dp[i] = dp[i-1] + costs[0];
                dp[i] = Math.min(dp[i], i > 7 ? dp[i-7] + costs[1] : costs[1]);
                dp[i] = Math.min(dp[i], i > 30 ? dp[i-30] + costs[2] : costs[2]);
                index ++;
                if(index == days.length) return dp[i];
            }
        }
        return 0;
    }
}
