package com.example.leetcode.DP;

/**
 * 剪绳子
 */
public class CutRope {
    /**
     * 题目描述
     * 给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1），每段绳子的长度记为
     * k[0],k[1],...,k[m]。请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪
     * 成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     *
     * 输入描述:
     * 输入一个数n，意义见题面。（2 <= n <= 60）
     * 输出描述:
     * 输出答案
     *
     * 示例1
     * 输入
     * 8
     * 输出
     * 复制
     * 18
     *
     * 链接：https://www.nowcoder.com/questionTerminal/57d85990ba5b440ab888fc72b0751bf8?f=discussion
     *     来源：牛客网
     *
     * 方法一：找规律，要使乘积最大，尽可能使k[0],k[1],...,k[m]之差最小，比如8=2+3+3
     *
     */
    public int cutRope(int target) {
        double ans = 0;

        for (int i = 2; i <= target; i++) {
            int div = target / i;
            int mod = target % i;
            //小技巧，使用Math.pow()使得复杂度降低为O(log n)
            double mul = Math.pow(div+1, mod);
            mul = mul * Math.pow(div, (i - mod));
            ans = Math.max(mul, ans);
        }

        return (int)ans;
    }

    /**
     * 方法二：动态规划
     */
    public int cutRope2(int target) {
        int[] dp = new int[target];
        dp[1] = 1;
        for (int i = 2; i < target; i++) {
            int max = 0;
            for (int j = 1; j <= (i / 2); j++) {
                max = Math.max(dp[j] * dp[i - j], max);
            }
            dp[i] = Math.max(max, i);
        }

        int ans = 0;
        for (int i = 1; i <= (target / 2); i++) {
            ans = Math.max(dp[i] * dp[target - i], ans);
        }
        return ans;
    }

    /**
     * 方法三：思想是切割越接近自然底数(约等于2.7)乘积越大！比如13，直接分成5等份，乘积最大。这里因为是整数，
     * 尽可能接近3.
     */
    public int cutRope3(int n) {
        if(n==2){
            return 1;
        }else if(n==3){
            return 2;
        }
        if(n%3==0){
            return (int)Math.pow(3,n/3);
        }else if(n%3==1){
            return 4*(int)Math.pow(3,n/3-1);
        }else {
            return 2*(int)Math.pow(3,n/3);
        }
    }
}
