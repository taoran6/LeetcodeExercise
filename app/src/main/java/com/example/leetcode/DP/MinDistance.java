package com.example.leetcode.DP;

/**
 * 72. 编辑距离
 */
public class MinDistance {
    /**
     * 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数 。
     *
     * 你可以对一个单词进行如下三种操作：
     *
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     * 示例 1:
     *
     * 输入: word1 = "horse", word2 = "ros"
     * 输出: 3
     * 解释:
     * horse -> rorse (将 'h' 替换为 'r')
     * rorse -> rose (删除 'r')
     * rose -> ros (删除 'e')
     * 示例 2:
     *
     * 输入: word1 = "intention", word2 = "execution"
     * 输出: 5
     * 解释:
     * intention -> inention (删除 't')
     * inention -> enention (将 'i' 替换为 'e')
     * enention -> exention (将 'n' 替换为 'x')
     * exention -> exection (将 'n' 替换为 'c')
     * exection -> execution (插入 'u')
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/edit-distance
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 参看题解https://leetcode-cn.com/problems/edit-distance/solution/zi-di-xiang-shang-he-zi-ding-xiang-xia-by-powcai-3/
     */
    public int minDistance(String word1, String word2) {
        if(word1 == null || word2 == null) return 0;
        // 添加空字符头防止越界
        char[] wordArray1 = (" " + word1).toCharArray();
        char[] wordArray2 = (" " + word2).toCharArray();

        //dp[i][j]表示从wordArray1的前i个字符转换成wordArray2的前j个字符需要的最小操作数
        int[][] dp = new int[wordArray1.length][wordArray2.length];
        //初始化首行首列
        for (int i = 0; i < wordArray1.length; i++) {
            //这里都是插入的次数
            dp[i][0] = i;
        }
        for(int j = 0; j < wordArray2.length; j++) {
            dp[0][j] = j;
        }

        for(int i = 1; i < wordArray1.length; i++) {
            for (int j = 1; j < wordArray2.length; j++) {
                if(wordArray1[i] == wordArray2[j]) dp[i][j] = dp[i - 1][j - 1];
                else {
                    // dp[i - 1][j - 1]，dp[i - 1][j]，dp[i][j - 1]分别表示替换，删除，插入操作
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;
                }
            }
        }

        return dp[wordArray1.length - 1][wordArray2.length - 1];
    }
}
