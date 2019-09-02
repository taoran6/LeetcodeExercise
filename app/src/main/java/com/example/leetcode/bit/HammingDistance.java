package com.example.leetcode.bit;

/**
 * 汉明距离
 */
public class HammingDistance {
    /**
     * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
     *
     * 给出两个整数 x 和 y，计算它们之间的汉明距离。
     *
     * 注意：
     * 0 ≤ x, y < 231.
     *
     * 示例:
     *
     * 输入: x = 1, y = 4
     *
     * 输出: 2
     *
     * 解释:
     * 1   (0 0 0 1)
     * 4   (0 1 0 0)
     *        ↑   ↑
     *
     * 上面的箭头指出了对应二进制位不同的位置。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/hamming-distance
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param x
     * @param y
     * @return
     *
     * 核心：位运算
     */
    public int hammingDistance(int x, int y) {
        int ans = 0;
        int z = x ^ y;
        while (z != 0) {
            ans = ans + (z & 0x01);
            z = z >>> 1;
        }

        return ans;
    }

    /**
     * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            ans += n & 0x01;
            n = n >>> 1;
        }
        return ans;
    }

    /**
     * 汉明重量方法二：这里关键的想法是对于任意数字 n ，将 n 和 n−1 做与运算，会把最后一个 1的位变成 0
     * 如 010100 变成 010000
     */
    public int hammingWeight1(int n) {
        int ans = 0;
        while (n != 0) {
            ans ++;
            n = n & (n - 1);
        }
        return ans;
    }
}
