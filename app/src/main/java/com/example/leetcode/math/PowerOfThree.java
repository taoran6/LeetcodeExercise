package com.example.leetcode.math;

/**
 * 3的幂
 */
public class PowerOfThree {
    /**
     * TODO LeetCode内部错误？？？
     * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 27
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: 0
     * 输出: false
     * 示例 3:
     * <p>
     * 输入: 9
     * 输出: true
     * 示例 4:
     * <p>
     * 输入: 45
     * 输出: false
     * 进阶：
     * 你能不使用循环或者递归来完成本题吗？
     * <p>
     * 方法一：循环
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/power-of-three
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThree1(int n) {
        if (n <= 0) return false;
        while (n % 3 == 0) {
            n = n / 3;
        }
        return n == 1;
    }

    /**
     * 方法二：基准转换
     * <p>
     * 我们所要做的就是将数字转换为以3为底的基数 ，并检查它是否为前导1，后跟所有 0。
     * 正则表达式来检查字符串是否以1 ^1 开头，后跟 0 或 多个 0 0* 并且不包含任何其他值 $。
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThree2(int n) {
        if (n <= 0) return false;
        String baseStr = Integer.toString(n, 3);
        return baseStr.matches("^10*$");
    }

    /**
     * 方法三：运算法
     * 我们可以用下面的数学公式 n = 3^i   =>  i = log(n) / log(3)
     *
     * 若 n 是 3 的幂则 i 是整数。在 Java 中，我们通过取小数部分（利用 % 1）来检查数字是否是整数，
     * 并检查它是否是 0。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/power-of-three/solution/3de-mi-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 在比较双精度数时不应使用 ==。这是因为 Math.log10(n)/Math.log10(3) 的结果可能是
     * 5.0000001 或 4.9999999。使用 Math.log() 函数而不是Math.log10() 可以观察到这种效果。
     * 为了解决这个问题，我们需要将结果与 epsilon 进行比较。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/power-of-three/solution/3de-mi-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    public boolean isPowerOfThree3(int n) {
        return (Math.log(n) / Math.log(3) + Double.MIN_NORMAL) % 1 <= 2 * Double.MIN_NORMAL;
    }

    /**
     * 方法四：
     *
     * n 的最大值，也就是 3 的幂，是 1162261467，而且3为质数
     * 因此，我们应该返回 true 的 n 的可能值应为3的倍数
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/power-of-three/solution/3de-mi-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean isPowerOfThree4(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
