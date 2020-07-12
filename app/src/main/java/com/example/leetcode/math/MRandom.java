package com.example.leetcode.math;

import java.util.Random;

public class MRandom {
    private int Random(int n) {
        Random random = new Random(System.currentTimeMillis());
        return random.nextInt(n);
    }

    /**
     * Random(7) 求 Random(5)
     */
    public int MRandom5() {
        int ans = Random(7);
        while (ans >= 5) {
            ans = Random(7);
        }
        return ans;
    }

    /**
     * Random(5) 求 Random(7)
     *
     * 方法一：在最坏的情况下，得出结果的情况可能永远不会发生，但从统计上看，最坏的情况永远不会发生。：)
     */
    public int MRandom7() {
        int[][] matrix = new int[][] {
                {0, 1, 2, 3, 4},
                {5, 6, 0, 1, 2},
                {3, 4, 5, 6, 0},
                {1, 2, 3, 4, 5},
                {6, -1, -1, -1, -1}
        };

        int ans = -1;
        while (ans == -1) {
            int i = Random(5);
            int j = Random(5);
            ans = matrix[i][j];
        }
        return ans;
    }

    /**
     * 方法二：上面的方法可以化简为这个方法
     *
     * 这个循环的预期运行时间为25/21=1.19次循环，但永远循环的概率是无穷小的。
     * @return
     */
    public int MRandom7II() {
        int ans = 21;
        while (ans >= 21) {
            int i = Random(5);
            int j = Random(5);
            ans = i * 5 + j;
        }
        return ans % 7;
    }
}
