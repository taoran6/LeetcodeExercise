package com.example.leetcode.search;

/**
 *  x 的平方根
 */
public class MySqrt {
    /**
     * 实现 int sqrt(int x) 函数。
     *
     * 计算并返回 x 的平方根，其中 x 是非负整数。
     *
     * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
     *
     * 示例 1:
     *
     * 输入: 4
     * 输出: 2
     * 示例 2:
     *
     * 输入: 8
     * 输出: 2
     * 说明: 8 的平方根是 2.82842...,
     *      由于返回类型是整数，小数部分将被舍去。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sqrtx
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一： 二分法
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if(x <= 0) return 0;
        if(x == 1) return 1;

        long start = 1;
        long end = x;
        while (start < end) {
            long mid = (start + end) >>> 1;
            //也可以使用 x / mid < mid 防止溢出测试用例2147395599
            long sq = mid * mid;

            if(sq == x) return (int)mid;
            else if(sq < x) start = mid + 1;
            else end = mid;
        }

        return (int)start - 1;
    }

    /**
     * 方法二：牛顿迭代法
     * @param a
     * @return
     */
    public int mySqrt2(int a) {
        long x = a;
        while (x * x > a) {
            x = (x + a / x) / 2;
        }
        return (int) x;
    }
}
