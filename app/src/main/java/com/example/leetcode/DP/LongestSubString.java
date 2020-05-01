package com.example.leetcode.DP;

import java.util.Scanner;

public class LongestSubString {
//    public static void main(String args[]){
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextLine()) {
//            String s1 = scanner.nextLine();
//            String s2 = scanner.nextLine();
//            System.out.println(getLongestSubString(s1, s2));
//        }
//    }

    /**
     * 最长公共子串
     *
     * 给定两个字符串str1和str2,输出两个字符串的最长公共子串，如果最长公共子串为空，输出-1。
     * @param s1
     * @param s2
     * @return
     */
    public static String getLongestSubString (String s1, String s2) {
        if(s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) return "-1";
        char[] s1Array = s1.toCharArray();
        char[] s2Array = s2.toCharArray();

        int max = 0;
        int index = -1;
        // dp[i][j] 表示的是以s1[i-1]，s2[j-1]“结尾”的公共子串的长度
        int[][] dp = new int[s1Array.length + 1][s2Array.length + 1];
        for (int i = 0; i < s1Array.length; i++) {
            for (int j = 0; j < s2Array.length; j++) {
                if(s1Array[i] == s2Array[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    if(dp[i + 1][j + 1] > max) {
                        max = dp[i + 1][j + 1];
                        index = j;
                    }
                }
            }
        }
        if(index == -1) return "-1";
        else return s2.substring(index - max + 1, index + 1);
    }

    /**
     * 1143. 最长公共子序列(LCS)
     *
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
     *
     * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符
     * （也可以不删除任何字符）后组成的新字符串。
     * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字
     * 符串所共同拥有的子序列。
     *
     * 若这两个字符串没有公共子序列，则返回 0。
     *
     *  
     *
     * 示例 1:
     *
     * 输入：text1 = "abcde", text2 = "ace"
     * 输出：3
     * 解释：最长公共子序列是 "ace"，它的长度为 3。
     * 示例 2:
     *
     * 输入：text1 = "abc", text2 = "abc"
     * 输出：3
     * 解释：最长公共子序列是 "abc"，它的长度为 3。
     * 示例 3:
     *
     * 输入：text1 = "abc", text2 = "def"
     * 输出：0
     * 解释：两个字符串没有公共子序列，返回 0。
     *  
     *
     * 提示:
     *
     * 1 <= text1.length <= 1000
     * 1 <= text2.length <= 1000
     * 输入的字符串只含有小写英文字符。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-common-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 时间复杂度O(n^2)
     * 空间复杂度O(n^2)
     */
    public int longestCommonSubsequence(String text1, String text2) {
        if(text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) return 0;

        int ans = 0;

        // dp[i][j]表示以text1的第i个字符和text2的第j个字符结尾的两个子串的最长公共子序列是多少
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        //这里预留了0个字符的位置初始化为0

        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if(text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }

        return ans;
    }

    /**
     * 方法二：压缩空间
     *
     * 这里其实值用到了前一排，所以二维可以压缩为一维
     */
    public int longestCommonSubsequence2(String text1, String text2) {
        if(text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) return 0;

        int ans = 0;

        // dp[j]表示以text2的第j个字符结尾的两个子串的最长公共子序列是多少
        int[] dp = new int[text2.length() + 1];
        //这里预留了0个字符的位置初始化为0

        for (int i = 1; i <= text1.length(); i++) {
            int[] tmp = new int[text2.length() + 1];
            for (int j = 1; j <= text2.length(); j++) {
                if(text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    tmp[j] = dp[j-1] + 1;
                } else {
                    tmp[j] = Math.max(tmp[j-1], dp[j]);
                }
                ans = Math.max(ans, tmp[j]);
            }

            dp = tmp;
        }

        return ans;
    }
}
