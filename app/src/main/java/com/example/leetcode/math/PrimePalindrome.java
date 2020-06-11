package com.example.leetcode.math;

/**
 * 866. 回文素数
 */
public class PrimePalindrome {
    /**
     * 求出大于或等于 N 的最小回文素数。
     *
     * 回顾一下，如果一个数大于 1，且其因数只有 1 和它自身，那么这个数是素数。
     *
     * 例如，2，3，5，7，11 以及 13 是素数。
     *
     * 回顾一下，如果一个数从左往右读与从右往左读是一样的，那么这个数是回文数。
     *
     * 例如，12321 是回文数。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：6
     * 输出：7
     * 示例 2：
     *
     * 输入：8
     * 输出：11
     * 示例 3：
     *
     * 输入：13
     * 输出：101
     *  
     *
     * 提示：
     *
     * 1 <= N <= 10^8
     * 答案肯定存在，且小于 2 * 10^8。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/prime-palindrome
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法：从N开始往上加，判断这个是是不是回文数，再判断是不是素数
     * 这里有一个技巧，没有8位数的素数
     *
     * 时间复杂度O(n)
     */
    public int primePalindrome(int N) {
        while (true) {
            if(isPalindrome(N) && isPrime(N)) return N;

            N++;

            //可以跳过检查，因为不存在 8 长度的素数。
            // Java7开始支持定义数字时加下划线
            if(N >= 10_000_000 && N < 100_000_000) {
                N = 100_000_000;
            }
        }
    }

    private boolean isPrime(int n) {
        //小于2的情况需要考虑
        if(n < 2) return false;

        for(int i = 2; i * i <= n; i++) {
            if(n % i == 0) return false;
        }
        return true;
    }

    private boolean isPalindrome(int n) {
        String s = n + "";
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if(s.charAt(start) != s.charAt(end)) return false;
            //每次都忘了这里
            start ++;
            end --;
        }
        return true;
    }
}
