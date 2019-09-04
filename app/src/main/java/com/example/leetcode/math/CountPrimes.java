package com.example.leetcode.math;

/**
 * 计数质数
 */
public class CountPrimes {
    /**
     * 统计所有小于非负整数 n 的质数的数量。
     *
     * 示例:
     *
     * 输入: 10
     * 输出: 4
     * 解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
     * 方法：厄拉多筛选法
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        if(n < 3) return 0;
        int ans = 0;
        boolean[] primes = new boolean[n];
        //使用i * i < n 计算2到根号n即可
        for (int i = 2; i * i < n; i ++) {
            if(!primes[i]) {
                //步长为i
                for(int j = i + i; j < n; j = j + i) {
                    primes[j] = true;
                }
            }
        }

        for(int i = 2; i < n; i ++) {
            if (! primes[i]) ans++;
        }

        return ans;
    }
}
