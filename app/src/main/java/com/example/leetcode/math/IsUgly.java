package com.example.leetcode.math;

/**
 * 263. 丑数
 */
public class IsUgly {
    /**
     * 编写一个程序判断给定的数是否为丑数。
     *
     * 丑数就是只包含质因数 2, 3, 5 的正整数。
     *
     * 示例 1:
     *
     * 输入: 6
     * 输出: true
     * 解释: 6 = 2 × 3
     * 示例 2:
     *
     * 输入: 8
     * 输出: true
     * 解释: 8 = 2 × 2 × 2
     * 示例 3:
     *
     * 输入: 14
     * 输出: false
     * 解释: 14 不是丑数，因为它包含了另外一个质因数 7。
     * 说明：
     *
     * 1 是丑数。
     * 输入不会超过 32 位有符号整数的范围: [−231,  231 − 1]。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/ugly-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param num
     * @return
     */
    public boolean isUgly(int num) {
        if(num <= 0) return false;

        while (num != 1) {
            if(num % 2 == 0) num = num / 2;
            else if(num % 3 == 0) num = num / 3;
            else if(num % 5 == 0) num = num / 5;
            else return false;
        }
        return true;
    }

    /**
     * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯
     * 上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
     *
     * 思路：使用动态规划，跟踪
     */
    public int GetUglyNumber_Solution(int index) {
        //小于0的情况，坑死了
        if(index <= 0) return 0;

        int[] ansArray = new int[index];
        //第一个丑数
        ansArray[0] = 1;
        //t2、t3、t5分别记录了计算下一个丑数时，可以乘2、乘3或乘5的最小丑数。
        int t2 = 0;
        int t3 = 0;
        int t5 = 0;
        for (int i = 1; i < index; i++) {
            //取这三个中最小的一个
            ansArray[i] = min(ansArray[t2] * 2, ansArray[t3] * 3, ansArray[t5] * 5);
            // 注意这里当ansArray[t2] * 2和ansArray[t3] * 3相等（如3*2和2*3）时，t2和t3都是要指针前移的，
            // 因此不会重复！
            if(ansArray[i] == ansArray[t2] * 2) t2 ++; //指针前移一个
            if(ansArray[i] == ansArray[t3] * 3) t3 ++;
            if(ansArray[i] == ansArray[t5] * 5) t5 ++;
        }
        return ansArray[index - 1];
    }

    private int min(int a, int b, int c) {
        int min = a;
        if(b < min) min = b;
        if(c < min) min = c;
        return min;
    }
}
