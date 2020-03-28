package com.example.leetcode.DP;

public class LongestValidParentheses {
    /**
     * 32. 最长有效括号
     *
     * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
     *
     * 示例 1:
     *
     * 输入: "(()"
     * 输出: 2
     * 解释: 最长有效括号子串为 "()"
     * 示例 2:
     *
     * 输入: ")()())"
     * 输出: 4
     * 解释: 最长有效括号子串为 "()()"
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-valid-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 动态规划的方法需要额外注意边界
     */
    public int longestValidParentheses(String s) {
        if(s == null || s.length() == 0) return 0;
        char[] sArray = s.toCharArray();
        //dp[i]表示以i结尾的字符串有效括号的长度。有效括号字符串的长度不一定（就是所求的dp[i]），但一定是以i结尾
        //如果dp[i]为0说明以i结尾的字符串不可能是有效括号
        int[] dp = new int[s.length()];
        int ans = 0;
        for(int i = 1; i < sArray.length; i++) {
            // 当前字符为 '(',肯定不是有效字符串
            if(sArray[i] == '(') dp[i] = 0;
            // 当前字符为 ')'
            else {
                if(sArray[i - 1] == '(') {
                    // 前一个字符为'(', 直接配对 +2即可
                    if(i >= 2) dp[i] = dp[i - 2] + 2;
                    else dp[i] = 2;
                    ans = Math.max(dp[i], ans);
                } else {    // 前一个字符为')'
                    // 有一个对应的 ‘(’和当前字符配对
                    if(i - dp[i-1] - 1 >= 0 && sArray[i - dp[i-1] - 1] == '(') {
                        //这里要额外判断一下两种情况
                        if(i - dp[i-1] - 2 >= 0) dp[i] = dp[i - 1] + 2 + dp[i - dp[i-1] - 2];
                        else dp[i] = dp[i - 1] + 2;
                        ans = Math.max(ans, dp[i]);
                    }
                    //其他情况dp[i] = 0
                }
            }
        }
        return ans;
    }
}
