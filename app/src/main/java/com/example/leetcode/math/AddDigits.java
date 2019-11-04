package com.example.leetcode.math;

/**
 * 各位相加
 */
public class AddDigits {
    /**
     * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。
     *
     * 示例:
     *
     * 输入: 38
     * 输出: 2
     * 解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
     * 进阶:
     * 你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-digits
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 常规的迭代方法就不说了，很简单
     * 这里写O(1)的方法
     * 参考 https://brilliant.org/wiki/digital-root/
     */
    public int addDigits(int num) {
        //注意这里0的情况单独考虑
        if(num == 0) return 0;

        int ans = num % 9;
        return ans == 0 ? 9 : ans;
    }

    /**
     * 一行代码解决
     *
     * 另外，虽然本题给的是非负数，但是需要知道在取模运算上，基本不同的语言都有自己的一套机制，一般来说，这套机制
     * 在正数上都一样，所以为了避免出现不必要的问题，建议先把负数转成正数再做取模运算。
     *
     * 例如在 Java/C 中 -15 % 9 结果为 -6
     * 而在Python中的结果是 3
     */
    public int addDigits2(int num) {
        //这里 -1 % 9 = -1
        return (num - 1) % 9 + 1;
    }
}
