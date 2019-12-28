package com.example.leetcode.math;

/**
 * 29. 两数相除
 */
public class Divide {
    /**
     * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
     *
     * 返回被除数 dividend 除以除数 divisor 得到的商。
     *
     * 示例 1:
     *
     * 输入: dividend = 10, divisor = 3
     * 输出: 3
     * 示例 2:
     *
     * 输入: dividend = 7, divisor = -3
     * 输出: -2
     * 说明:
     *
     * 被除数和除数均为 32 位有符号整数。
     * 除数不为 0。
     * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返
     * 回 2^31 − 1。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/divide-two-integers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：用long型可以避免溢出的情况发生
     * 方法二：为了挑战一下用了int，单独考虑Integer.MIN_VALUE的情况，虽然花了不少时间去思考。但是AC的
     * 时候很开心啦啦啦
     * 方法三：看了一种思路， 正数边界问题比较麻烦，所以改用负数计算。
     */
    public int divide(int dividend, int divisor) {
        int ans = 0;
        //除数为0
        if(divisor == 0) return Integer.MIN_VALUE;
        //判断符号
        boolean sign = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);

        //除数为-2^31
        if(divisor == Integer.MIN_VALUE) return dividend == Integer.MIN_VALUE ? 1 : 0;
        //被除数为-2^31
        boolean isDivMIN = false;
        if(dividend == Integer.MIN_VALUE) {
            //两个溢出的情况单独考虑
            if(divisor == -1) return Integer.MAX_VALUE;
            if(divisor == 1) return Integer.MIN_VALUE;
            isDivMIN = true;
            dividend = Integer.MAX_VALUE;
        }

        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        int x = 0;
        while (divisor << (x + 1) > 0 && (divisor << (x + 1)) <= dividend) {
            x ++;
        }

        for(int i = x; i >= 0; i --){
            int mul = divisor << i;
            if(dividend >= mul) {
                ans += 1 << i;
                dividend -= mul;
            }
        }

        if(isDivMIN && dividend == divisor - 1) ans ++;
        return sign ? - ans : ans;
    }
}
