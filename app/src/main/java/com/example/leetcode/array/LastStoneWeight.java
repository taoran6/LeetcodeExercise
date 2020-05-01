package com.example.leetcode.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最后一块石头的重量
 */
public class LastStoneWeight {
    /**
     * 1046. 最后一块石头的重量
     *
     * 有一堆石头，每块石头的重量都是正整数。
     *
     * 每一回合，从中选出两块 最重 的石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的
     * 可能结果如下：
     *
     * 如果 x == y，那么两块石头都会被完全粉碎；
     * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
     * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
     *
     *  
     *
     * 提示：
     *
     * 1 <= stones.length <= 30
     * 1 <= stones[i] <= 1000
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/last-stone-weight
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param stones
     * @return
     *
     * 方法一：使用PriorityQueue 大顶堆
     */
    public int lastStoneWeightI(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int i = 0; i < stones.length; i++) {
            queue.offer(stones[i]);
        }
        while (queue.size() > 1) {
            int y = queue.poll();
            int x = queue.poll();
            if(x != y) queue.offer(y - x);
        }
        return queue.isEmpty() ? 0 : queue.peek();
    }

    /**
     * 方法二：使用排序
     *
     * 这个思路在于每次都重新排序，
     * 理论上复杂度会更高，但实际更快
     * 是因为每次最多只有一对元素逆序吗。。。
     * @param stones
     * @return
     */
    public int lastStoneWeightI2(int[] stones) {
        int length = stones.length;
        if(stones.length == 1) return stones[0];
        Arrays.sort(stones);
        while (stones[length - 2] != 0) {
            int sub = stones[length - 1] - stones[length - 2];
            stones[length - 2] = 0;
            stones[length - 1] = sub;
            Arrays.sort(stones);
        }
        return stones[length - 1];
    }

    /**
     * 1049. 最后一块石头的重量 II
     *
     * 有一堆石头，每块石头的重量都是正整数。
     *
     * 每一回合，从中选出 任意 两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的
     * 可能结果如下：
     *
     * 如果 x == y，那么两块石头都会被完全粉碎；
     * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
     * 最后，最多只会剩下一块石头。返回此石头 最小 的可能重量。如果没有石头剩下，就返回 0。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/last-stone-weight-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 思路：换一种想法，就是 将这些数字分成两拨，使得他们的和的差最小
     *
     * 再进一步，可以变成 选出一些数字，使得它们最逼近整个数组和除以二的值，而最后的结果，就是
     * abs（这个数-总和除以二）*2
     *
     * 所以说这题实际上转换成了0-1背包问题，这里背包总容量是W = sum(array[i]) / 2, 要求用这个背包装物品，最多
     * 能装的价值是多少，每个石头的价值即为它的重量
     *
     * 作者：moguiyu
     * 链接：https://leetcode-cn.com/problems/last-stone-weight-ii/solution/shao-wei-nao-jin-zhuan-ge-wan-yong-shi-ji-bai-9778/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int lastStoneWeightII(int[] stones) {
        if(stones == null || stones.length == 0) return 0;

        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }

        int W = sum / 2;
        // dp[i][j]表示在前i个石头中，最多能在j容量的背包中装下石头的最大价值
        int[][] dp = new int[stones.length + 1][W + 1];

        for(int i = 1; i <= stones.length; i++) {
            for (int j = 1; j <= W; j++) {
                if(j - stones[i - 1] < 0) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-stones[i - 1]] + stones[i - 1]);
                }
            }
        }

        // 得到两堆的差值
        return sum - dp[stones.length][W] - dp[stones.length][W];
    }

}
