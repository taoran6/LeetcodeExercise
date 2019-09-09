package com.example.leetcode.DP;

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
     * TODO 方法二：反转字符串求最长公共子串
     */


    /**
     * 方法三：马拉车算法
     */

}
