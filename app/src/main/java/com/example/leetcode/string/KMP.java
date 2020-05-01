package com.example.leetcode.string;

/**
 * KMP 字符匹配算法
 */
public class KMP {
    /**
     * 在一个文本串 str 内查找第一个模式串 patten 的出现位置。
     * @param str
     * @param patten
     * @return
     */
    public int KMP(String str, String patten) {
        if(str == null || patten == null || str.length() == 0 || patten.length() == 0) return -1;


        // dp[i][j] 表示在在patten的第i个字符的状态下遇到第j个字符应该转移到的状态
        // 这里使用256是因为ASCII字符最多256个（ASCII 码使用指定的7位或8位来表示128或256种可能的字符）
        int[][] dp = new int[patten.length()][256];
        KMP_patten(patten, dp);

        int state = 0;
        for(int i = 0; i < str.length(); i++) {
            // 计算下一个状态
            state = dp[state][str.charAt(i)];
            // 到达终止态，返回结果
            if(state == patten.length()) return i - patten.length() + 1;
        }
        return -1;
    }

    /**
     * 构建状态转移图
     * @param patten
     * @param dp
     */
    private void KMP_patten(String patten, int[][] dp) {

        dp[0][patten.charAt(0)] = 1;
        //X为影子状态
        int X = 0;

        // 当前状态 i 从 1 开始
        for(int i = 1; i < patten.length(); i++) {
            for(int j = 0; j < 256; j++) {
                if(j == patten.charAt(i)) {
                    dp[i][j] = i + 1;
                } else {
                    dp[i][j] = dp[X][j];
                }
            }
            // 更新影子状态
            X = dp[X][patten.charAt(i)];
        }
    }
}
