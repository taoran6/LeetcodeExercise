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

    /**
     * 338. 比特位计数
     *
     * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数
     * 组返回。
     *
     * 示例 1:
     *
     * 输入: 2
     * 输出: [0,1,1]
     * 示例 2:
     *
     * 输入: 5
     * 输出: [0,1,1,2,1,2]
     * 进阶:
     *
     * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
     * 要求算法的空间复杂度为O(n)。
     * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）
     * 来执行此操作。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/counting-bits
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param num
     * @return
     *
     * 核心：重用之前计算过的值
     *
     * 方法一：i >> 1会把最低位去掉，因此i >> 1 也是比i小的，同样也是在前面的数组里算过。当 i 的最低位是0，则
     * i 中1的个数和i >> 1中1的个数相同；当i的最低位是1，i 中1的个数是 i >> 1中1的个数再加1
     */

    public int[] countBits(int num) {
        int[] ans = new int[num + 1];

        for (int i = 1; i <= num; i++) {
            ans[i] = ans[i >>> 1] + (i & 1);
        }
        return ans;
    }

    /**
     * 方法二：i & (i - 1)可以去掉i最右边的一个1（如果有），因此 i & (i - 1）是比 i 小的，而且i & (i - 1)
     * 的1的个数已经在前面算过了，所以i的1的个数就是 i & (i - 1)的1的个数加上1
     */
    public int[] countBits2(int num) {
        int[] ans = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            ans[i] = ans[i & (i - 1)] + 1;
        }
        return ans;
    }
}
