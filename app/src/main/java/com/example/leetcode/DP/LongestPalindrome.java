package com.example.leetcode.DP;

import java.util.Arrays;

/**
 * 最长回文子串
 */
public class LongestPalindrome {
    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     *
     * 示例 1：
     *
     * 输入: "babad"
     * 输出: "bab"
     * 注意: "aba" 也是一个有效答案。
     * 示例 2：
     *
     * 输入: "cbbd"
     * 输出: "bb"
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：中心扩展算法， 有 2n-1 个这样的中心
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";

        int ansLength = 1;
        String ansStr = s.substring(0, 1);
        for(int i = 0; i < s.length() - ansLength / 2; i++) {
            String tmp1 = findSubPalindrome(s, i - 1, i + 1, ansLength);
            if(ansLength < tmp1.length()) {
                ansLength = tmp1.length();
                ansStr = tmp1;
            }

            String tmp2 = findSubPalindrome(s, i, i + 1 ,ansLength);
            if(ansLength < tmp2.length()) {
                ansLength = tmp2.length();
                ansStr = tmp2;
            }
        }
        return ansStr;
    }

    private String findSubPalindrome(String s, int start, int end, int ansLength) {
        String ansStr = "";
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            if (end - start + 1 > ansLength) {
                ansLength = end - start + 1;
                ansStr = s.substring(start, end + 1);
            }
            start--;
            end++;
        }
        return ansStr;
    }

    /**
     * 方法二：动态规划
     */
    public String longestPalindrome2(String s) {
        if(s == null || s.length() == 0) return "";

        //dp[i][j]表示从i到j的字符串是不是回文串
        boolean[][] dp = new boolean[s.length()][s.length()];
        //保存最大长度
        int ans = 0;
        //保存最大长度的左索引
        int ansLeft = 0;
        //保存最大长度的右索引
        int ansRight = 0;
        char[] sArray = s.toCharArray();

        //right从0到length遍历
        for(int r = 0; r < s.length(); r++) {
            //这里left倒着遍历
            for (int l = r; l >= 0; l --) {
                if(r == l) dp[l][r] = true;
                else if(r == l + 1 && sArray[l] == sArray[r]) dp[l][r] = true;
                else if(sArray[l] == sArray[r] && dp[l + 1][r - 1]){
                    dp[l][r] = true;
                }
                if(dp[l][r] && (r - l + 1) > ans ) {
                    //更新最大长度
                    ans = r - l + 1;
                    ansLeft = l;
                    ansRight = r;
                }
            }
        }

        return s.substring(ansLeft, ansRight  +1);

    }
}
