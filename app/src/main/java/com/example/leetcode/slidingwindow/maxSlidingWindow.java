package com.example.leetcode.slidingwindow;

import java.util.Deque;
import java.util.LinkedList;

public class maxSlidingWindow {
    /**
     * 239. 滑动窗口最大值
     *
     * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内
     * 的 k 个数字。滑动窗口每次只向右移动一位。
     *
     * 返回滑动窗口中的最大值。
     *
     *  
     *
     * 进阶：
     *
     * 你能在线性时间复杂度内解决此题吗？
     *
     *  
     *
     * 示例:
     *
     * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
     * 输出: [3,3,5,5,6,7]
     * 解释:
     *
     *   滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     *  
     *
     * 提示：
     *
     * 1 <= nums.length <= 10^5
     * -10^4 <= nums[i] <= 10^4
     * 1 <= k <= nums.length
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 需要使用单调队列,算法时间复杂度O(n)，每个元素最多被入队出队各一次
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue queue = new MonotonicQueue();
        int[] ans = new int[nums.length - k + 1];
        int index = 0;

        //先填满前k个数
        for(int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }
        ans[index++] = queue.getMax();

        //后面的窗口计算
        while (index < ans.length) {
            queue.delete(nums[index - 1]);
            queue.offer(nums[index - 1 + k]);
            ans[index++] = queue.getMax();
        }
        return ans;
    }

    /**
     * 单调队列，保证队头的元素永远比队尾元素大
     */
    static class MonotonicQueue {
        //双端队列的数据结构
        Deque<Integer> deque = new LinkedList<>();
        MonotonicQueue() { }

        /**
         * 向单调队列中加入元素
         * @param data
         */
        void offer(int data) {
            //保证单调性
            while (! deque.isEmpty() && deque.getLast() < data) {
                deque.removeLast();
            }
            deque.offerLast(data);
        }

        /**
         * 向单调队列删除元素
         */
        void delete(int data) {
            if(!deque.isEmpty() && deque.getFirst() == data) {
                deque.removeFirst();
            }
            //有可能在后面更大的元素进来的时候这个元素已经被提前删除了，此时不需要操作
        }

        /**
         * 返回队列最大元素
         * @return
         */
        int getMax() {
            return deque.getFirst();
        }
    }
}
