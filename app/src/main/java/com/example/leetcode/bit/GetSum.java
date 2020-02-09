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
            //！！注意这里进位要左移
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

    /**
     * 方法三：1.两个数异或：相当于每一位相加，而不考虑进位；
     * 2.两个数相与，并左移一位：相当于求得进位；
     * 3.将上述两步的结果相加
     */
    public int getSum3(int num1, int num2) {
        int carry = 0;
        while (num2 != 0) {
            int sum = num1 ^ num2;
            //！！注意这里进位要左移
            carry = (num1 & num2) << 1;
            num2 = carry;
            num1 = sum;
        }
        return num1;
    }

    /**
     * 求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及
     * 条件判断语句（A?B:C）。
     *
     * 链接：https://www.nowcoder.com/questionTerminal/7a0da8fc483247ff8800059e12d7caf1?f=discussion
     * 来源：牛客网
     *
     * 1.需利用逻辑与的短路特性实现递归终止。
     * 2.当n==0时，(n>0)&&((sum+=Sum_Solution(n-1))>0)只执行前面的判断，为false，然后直接返回0；
     * 3.当n>0时，执行sum+=Sum_Solution(n-1)，实现递归计算Sum_Solution(n)。
     */
    public int Sum_Solution(int n) {
        int sum = n;
        boolean test = (n > 0) && ((sum += Sum_Solution(n-1)) > 0);
        return sum;
    }
}
