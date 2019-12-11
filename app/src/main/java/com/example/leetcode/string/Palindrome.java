package com.example.leetcode.string;

/**
 *  验证回文串
 */
public class Palindrome {
    public boolean isPalindrome(String s) {
        if(s.length() == 0) return true;
        int head = 0;
        int tail = s.length() - 1;
        while (head < tail) {
            char h = s.charAt(head);
            if( ! isValidChar(h)){
                head ++;
                continue;
            }
            char t = s.charAt(tail);
            if(! isValidChar(t)) {
                tail --;
                continue;
            }

            //注意特殊情况"0P", 不能单纯的使用ASCII数值相减！！！
            // （'0'-'P'的值正好等于'a'- A'的值，好坑）
            h = toLowerCase(h);
            t = toLowerCase(t);
            if(h != t) return false;
            tail--;
            head++;

        }

        return true;
    }

    private boolean isValidChar(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }
    private char toLowerCase(char c) {
        if (c >= 'A' && c <= 'Z') return (char) (c - 'A' + 'a');
        return c;
    }

    /**
     * 647. 回文子串
     *
     * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
     *
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
     *
     * 示例 1:
     *
     * 输入: "abc"
     * 输出: 3
     * 解释: 三个回文子串: "a", "b", "c".
     * 示例 2:
     *
     * 输入: "aaa"
     * 输出: 6
     * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
     * 注意:
     *
     * 输入的字符串长度不会超过1000。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/palindromic-substrings
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：中心扩展法
     */
    public int countSubstrings(String s) {
        char[] chars = s.toCharArray();
        int ans = 0;
        for (int i = 0; i < chars.length; i++) {
            ans ++;
            for (int k = 1; i - k >= 0 && i + k < chars.length; k++) {
                if(chars[i-k] == chars[i+k]) ans++;
                else break;
            }
            for (int k = 1; i - k >=0 && i + k - 1 < chars.length; k++) {
                if(chars[i-k] == chars[i+k-1]) ans++;
                else break;
            }
        }
        return ans;
    }

    /**
     * 方法二：动态规划
     */
    public int countSubstrings2(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        //dp[i][j]表示以i开头，j结尾的字符串是否是回文
        boolean[][] dp = new boolean[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++) {
                if(i == j) dp[i][j] = true;
                else {
                    //i + 1 = j 的情况单独考虑， dp[i + 1][j - 1]是dp[i][j]砍头去尾后的是否是回文
                    dp[i][j] = chars[i] == chars[j] && (j - i == 1 || dp[i+1][j-1]);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                if(dp[j][i]) ans++;
            }
        }
        return ans;
    }
}
