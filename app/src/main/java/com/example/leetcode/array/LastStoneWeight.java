package com.example.leetcode.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 最后一块石头的重量
 */
public class LastStoneWeight {
    /**
     * 有一堆石头，每块石头的重量都是正整数。
     *
     * 每一回合，从中选出两块最重的石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的
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
    public int lastStoneWeight(int[] stones) {
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
    public int lastStoneWeight2(int[] stones) {
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

}
