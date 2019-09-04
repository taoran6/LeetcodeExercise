package com.example.leetcode.bit;

/**
 * 两整数之和
 */
public class GetSum {
    /**
     * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
     *
     * 示例 1:
     *
     * 输入: a = 1, b = 2
     * 输出: 3
     * 示例 2:
     *
     * 输入: a = -2, b = 3
     * 输出: 1
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sum-of-two-integers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：迭代
     * @param a
     * @param b
     * @return
     */
    public int getSum1(int a, int b) {
        int flag = 0;
        int ans = 0;
        int mask = 1;
        for(int i = 0; i < 32; i ++) {
            int ma = a & mask;
            int mb = b & mask;
            ans = ans | (ma ^ mb ^ flag);
            flag = ((ma & mb) | (flag & ma) | (flag & mb)) << 1;
            mask = mask << 1;
        }
        return ans;
    }

    /**
     * 方法二：递归
     *
     * a + b 的问题拆分为 (a 和 b 的无进位结果) + (a 和 b 的进位结果)
     * 无进位加法使用异或运算计算得出
     * 进位结果使用与运算和移位运算计算得出
     * 循环此过程，直到进位为 0
     *
     * 作者：jalan
     * 链接：https://leetcode-cn.com/problems/sum-of-two-integers/solution/wei-yun-suan-xiang-jie-yi-ji-zai-python-zhong-xu-y/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        int ans = a ^ b;
        int forward = (a & b) << 1;
        if (forward != 0) {
            return getSum(forward, ans);
        }
        return ans;
    }
}
