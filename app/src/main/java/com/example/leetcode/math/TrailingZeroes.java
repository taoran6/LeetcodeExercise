package com.example.leetcode.math;

/**
 *  阶乘后的零
 */
public class TrailingZeroes {
    /**
     * 给定一个整数 n，返回 n! 结果尾数中零的数量。
     *
     * 示例 1:
     *
     * 输入: 3
     * 输出: 0
     * 解释: 3! = 6, 尾数中没有零。
     * 示例 2:
     *
     * 输入: 5
     * 输出: 1
     * 解释: 5! = 120, 尾数中有 1 个零.
     * 说明: 你算法的时间复杂度应为 O(log n) 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/factorial-trailing-zeroes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        //错误解法，输入1808548329 预期结果452137076 而我的是452137078， 多的2，原因是内存溢出了
        //参看 https://blog.csdn.net/fy_sun123/article/details/42339579
        int ans = 0;
        int num = 5;
        while (n >= num) {
            ans += n / num;
            num *= 5;
        }
        return ans;
    }

    public int trailingZeroes2(int n) {
        int ans = 0;
        while (n > 0) {
            ans += n / 5;
            n = n /5;
        }
        return ans;
    }
}
