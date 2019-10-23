package com.example.leetcode.DP;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 完全平方数
 */
public class NumSquares {
    /**
     * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平
     * 方数的个数最少。
     *
     * 示例 1:
     *
     * 输入: n = 12
     * 输出: 3
     * 解释: 12 = 4 + 4 + 4.
     * 示例 2:
     *
     * 输入: n = 13
     * 输出: 2
     * 解释: 13 = 4 + 9.
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/perfect-squares
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：动态规划，动态转移方程为:dp[i] = MIN(dp[i], dp[i - j * j] + 1)，i表示当前数字，j*j表示平方数
     * 执行用时 89ms
     */
    public int numSquares1(int n) {
        if(n <= 0) return 0;

        int[] counts = new int[n + 1];

        //i从1开始 counts[0] = 0
        for(int i = 1; i <= n; i++) {
            counts[i] = counts[i - 1] + 1;
            for (int j = 1; i / j >= j; j++)        //使用除法防止溢出
                counts[i] = Math.min(counts[i], counts[i - j * j] + 1);
        }
        return counts[n];
    }

    /**
     * 方法二：在方法一的基础上进行了优化，存储完全平方数的值，减少乘法的次数
     * 执行用时 41ms
     * @param n
     * @return
     */
    public int numSquares2(int n) {
        if(n <= 0) return 0;

        int[] counts = new int[n + 1];

        //存储完全平方数的值
        int[] squares = new int[n + 1];

        int k = 1;
        for(; n / k >= k; k++) {
            int k2 = k * k;
            squares[k] = k2;
            counts[k2] = 1;
        }
        if (n == squares[k - 1]) return 1;
        squares[k] = n + 1; //最后一位使用最大值，以满足i - squares[j] < 0 的情况

        for(int i = 1; i <= n; i++) {
            if(counts[i] != 0) continue;       //完全平方数直接跳过
            counts[i] = counts[i - 1] + 1;

            //这里用的不是 i - squares[j]>=0 因为完全平方数的情况在上面已经跳过了
            for (int j = 2; i - squares[j] > 0; j++)
                counts[i] = Math.min(counts[i], counts[i - squares[j]] + 1);
        }
        return counts[n];
    }

    /**
     * 方法三：广度优先搜索
     * 用时15ms
     *
     * https://leetcode-cn.com/problems/perfect-squares/solution/yan-du-you-xian-sou-suo-java-by-eiletxie/
     */
    /**
     * 先定义一个数据结构
     */
    private class Node {
        int val;
        int step;

        Node(int val, int step) {
            this.val = val;
            this.step = step;
        }
    }

    public int numSquares3(int n) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(n, 1));
        //记录是否已经遍历过
        boolean[] record = new boolean[n];

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            // 每一层的广度遍历
            for (int i = 1; ; i++) {
                int nextVal = node.val - i * i;
                if (nextVal < 0) break;
                if (nextVal == 0) return node.step;

                // 当再次出现时没有必要加入，因为在该节点的路径长度肯定不小于第一次出现的路径长
                if (!record[nextVal]) {
                    queue.offer(new Node(nextVal, node.step + 1));
                    record[nextVal] = true;
                }
            }
        }
        return 0;
    }


    /**
     * 方法四：四平方定理： 任何一个正整数都可以表示成不超过四个整数的平方之和。 推论：满足四数平方和定理的
     * 数n（四个整数的情况），必定满足 n=4^a(8b+7)
     * 用时 1ms
     *
     * 可以查看题解 https://blog.csdn.net/NK_test/article/details/49388581
     */
    public int numSquares4(int n) {
        if(n <= 0) return 0;

        while (n % 4 == 0) n /= 4;  //一个数如果含有因子4，那么我们可以把4都去掉，并不影响结果

        if(n % 8 == 7) return 4;    //n=(4^a) * (8b+7)

        for(int i = 1; n / i >= i; i++) {
            if(i * i == n) return 1;    //完全平方数
        }

        for(int i = 1; n / i >= i; i++) {
            int b = (int) Math.sqrt(n - i * i);
            if((i * i + b * b) == n) {
                return 2;      //2个完全平方数之和
            }
        }

        return 3;       //其他情况3
    }
}
