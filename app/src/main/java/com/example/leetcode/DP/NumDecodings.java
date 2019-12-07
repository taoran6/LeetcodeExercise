package com.example.leetcode.DP;

/**
 * 91. 解码方法
 */
public class NumDecodings {
    /**
     * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
     *
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
     *
     * 示例 1:
     *
     * 输入: "12"
     * 输出: 2
     * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
     * 示例 2:
     *
     * 输入: "226"
     * 输出: 3
     * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/decode-ways
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int numDecodings(String s) {
        if(s.length() == 0 || s.startsWith("0")) return 0;

        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        char[] chars = s.toCharArray();

        for (int i = 1; i < chars.length; i++) {
            int last = (chars[i-1] - '0') * 10 + chars[i] - '0';
            if(last == 10 || last == 20) {
                dp[i + 1] = dp[i - 1];
            }
            else if(last > 10 && last <= 26) {
                dp[i + 1] = dp[i] + dp[i-1];
            }
            //这里要考虑非法字符串如"409"这种情况
            else if(last % 10 == 0) {
                return 0;
            }
            else {
                dp[i + 1] = dp[i];
            }
        }

        return dp[chars.length];
    }

    /**
     * 方法二：这里可以空间压缩
     * 分类判断跟上一个方法有所不同，但也是大同小异，思路都差不多
     *
     * 奇怪的是这个方法内存消耗35M，上一个方法34M，迷！（跟Java的垃圾回收机制有关吗？）
     */
    public int numDecodings2(String s) {
        if(s.length() == 0 || s.startsWith("0")) return 0;

        char[] chars = s.toCharArray();
        int dp_1 = 1;
        int dp_2 = 1;

        for(int i = 1; i < chars.length; i++) {
            int curr;
            //1.当前为0，判断是否是非法字符串
            if(chars[i] == '0') {
                if(chars[i-1] != '1' && chars[i-1] != '2') return 0;
                //合法，与前两个的解相同
                else curr = dp_2;
            }
            //2.前一个是1或2，可以有两种组合
            else if(chars[i-1] == '1' || chars[i-1] == '2' && chars[i] <= '6'){
                curr = dp_1 + dp_2;
            }
            //3.只有一种组合的情况
            else {
                curr = dp_1;
            }

            dp_2 = dp_1;
            dp_1 = curr;
        }

        return dp_1;
    }
}
