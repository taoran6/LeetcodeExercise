package com.example.leetcode.array;

import java.util.PriorityQueue;

/**
 * 数组中的第K个最大元素
 */
public class FindKthLargest {
    /**
     * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个
     * 不同的元素。
     *
     * 示例 1:
     *
     * 输入: [3,2,1,5,6,4] 和 k = 2
     * 输出: 5
     * 示例 2:
     *
     * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
     * 输出: 4
     * 说明:
     *
     * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：先排序，取第k位放，略
     *
     * 方法二：使用堆
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if(queue.size() < k) queue.add(nums[i]);
            else {
                if(queue.peek() < nums[i]) {
                    queue.remove();
                    queue.add(nums[i]);
                }
            }
        }
        return queue.peek();
    }

    /**
     * 方法三：快速选择
     *
     * 首先，我们选择一个枢轴，并在线性时间内定义其在排序数组中的位置。这可以通过 划分算法 的帮助来完成。
     * 这样，在输出的数组中，枢轴达到其合适位置。所有小于枢轴的元素都在其左侧，所有大于或等于的元素都在其右侧。
     *
     * 如果是快速排序算法，会在这里递归地对两部分进行快速排序，时间复杂度为 O(NlogN)。
     *
     * 而在这里，由于知道要找的第 N - k 小的元素在哪部分中，我们不需要对两部分都做处理，这样就将平均时间复杂度
     * 下降到 O(N)。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/shu-zu-zhong-de-di-kge-zui-da-yuan-su-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/shu-zu-zhong-de-di-kge-zui-da-yuan-su-by-leetcode/
     */
    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, k -1, 0, nums.length - 1);
    }

    private int findKthLargest(int[] nums, int k, int low, int high) {
        int index = partition(nums, low, high);
        if(index == k) return nums[index];
        else if(index < k) return findKthLargest(nums, k, index + 1, high);
        else return findKthLargest(nums, k, low, index - 1);
    }

    private int partition(int[] nums, int low, int high) {
        int pivot = nums[low];
        while (low < high) {
            while (low < high && nums[high] <= pivot) {
                high --;
            }
            nums[low] = nums[high];
            while (low < high && nums[low] >= pivot) {
                low ++;
            }
            nums[high] = nums[low];
        }
        nums[low] = pivot;
        return low;
    }

}
