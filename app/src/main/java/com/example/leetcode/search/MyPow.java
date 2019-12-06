package com.example.leetcode.search;

/**
 * 50. Pow(x, n)
 */
public class MyPow {
    /**
     * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
     *
     * 示例 1:
     *
     * 输入: 2.00000, 10
     * 输出: 1024.00000
     * 示例 2:
     *
     * 输入: 2.10000, 3
     * 输出: 9.26100
     * 示例 3:
     *
     * 输入: 2.00000, -2
     * 输出: 0.25000
     * 解释: 2-2 = 1/22 = 1/4 = 0.25
     * 说明:
     *
     * -100.0 < x < 100.0
     * n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/powx-n
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法：二分法，思路跟MySqrt很像
     * 时间复杂度O(log n)
     */
    public double myPow(double x, int n) {

        //是否是负次幂
        boolean flag = n < 0;
        //这里由于数值范围是 [−2^31, 2^31 − 1]，-2^31的情况需要转换为long类型
        long N = n;
        N = Math.abs(N);

        double current = 1.0000;
        double pow = x;
        while (N != 0) {
            long bit = N & 1;
            if(bit != 0) current = current * bit * pow;
            pow = pow * pow;
            N = N >>> 1;
        }

        if(flag) current = 1.0 / current;

        return current;
    }
}
