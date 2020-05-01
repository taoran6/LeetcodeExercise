package com.example.leetcode.DP;

public class MaxA {
    /**
     * 651. 4键键盘
     *
     * 假设你有一个特殊的键盘包含下面的按键：
     *
     * Key 1: (A)：在屏幕上打印一个 'A'。
     *
     * Key 2: (Ctrl-A)：选中整个屏幕。
     *
     * Key 3: (Ctrl-C)：复制选中区域到缓冲区。
     *
     * Key 4: (Ctrl-V)：将缓冲区内容输出到上次输入的结束位置，并显示在屏幕上。
     *
     * 现在，你只可以按键 N 次（使用上述四种按键），请问屏幕上最多可以显示几个 'A'呢？
     *
     * 样例 1:
     *
     * 输入: N = 3
     * 输出: 3
     * 解释:
     * 我们最多可以在屏幕上显示三个'A'通过如下顺序按键：
     * A, A, A
     *  
     *
     * 样例 2:
     *
     * 输入: N = 7
     * 输出: 9
     * 解释:
     * 我们最多可以在屏幕上显示九个'A'通过如下顺序按键：
     * A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
     *  
     *
     * 注释:
     *
     * 1 <= N <= 50
     * 结果不会超过 32 位有符号整数范围。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/4-keys-keyboard
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int maxA(int N) {
        if(N <= 0) return 0;

        int[] dp = new int[N + 1];
        //dp[0] = 0省略


        for (int i = 1; i <= N; i++) {
            // 本次按A
            dp[i] = dp[i - 1] + 1;

            // 最优的操作序列一定是 C-A C-C 接着若干 C-V ，所以我们用一个变量 j 作为若干 C-V 的起点。
            // 本次从j以后全按C+V，那么j必定为Ctrl C，j-1必定为Ctrl A
            for (int j = 3; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[j-2] * (i - j + 1));
            }
        }

        return dp[N];
    }
}
