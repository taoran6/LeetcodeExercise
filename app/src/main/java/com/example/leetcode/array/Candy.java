package com.example.leetcode.array;

public class Candy {
    /**
     * 有N个小朋友站在一排，每个小朋友都有一个评分
     * 你现在要按以下的规则给孩子们分糖果：
     * - 每个小朋友至少要分得一颗糖果
     * - 分数高的小朋友要他比旁边得分低的小朋友分得的糖果多
     * 你最少要分发多少颗糖果？
     *
     * 注意，这里分数相同的小孩糖果数可以不同，如[1,2,2]只需要4颗糖（虽然觉得这不合逻辑）
     *
     * https://www.nowcoder.com/practice/74a62e876ec341de8ab5c8662e866aef?tpId=46&tqId=29045&tPage=1&rp=1&ru=%2Fta%2Fleetcode&qru=%2Fta%2Fleetcode%2Fquestion-ranking
     */
    public int candy(int[] ratings) {
        if(ratings == null && ratings.length == 0) return 0;
        int dp[] = new int[ratings.length];
        dp[0] = 1;
        for(int i = 1; i < ratings.length; i++){
            if(ratings[i] > ratings[i-1]){
                dp[i] = dp[i-1] + 1;
            }else {
                dp[i] = 1;
            }
        }

        for(int i = ratings.length - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i + 1] && dp[i] <= dp[i + 1]){
                dp[i] = dp[i+1] + 1;
            }
        }

        int ans = 0;
        for(int i : dp) {
            ans += i;
        }
        return ans;
    }
}
