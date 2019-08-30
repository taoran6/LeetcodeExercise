package com.example.leetcode.array;

/**
 * 旋转数组
 */
public class Rotate {
    /**
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     *
     * 示例 1:
     *
     * 输入: [1,2,3,4,5,6,7] 和 k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     * 示例 2:
     *
     * 输入: [-1,-100,3,99] 和 k = 2
     * 输出: [3,99,-1,-100]
     * 解释:
     * 向右旋转 1 步: [99,-1,-100,3]
     * 向右旋转 2 步: [3,99,-1,-100]
     * 说明:
     *
     * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
     * 要求使用空间复杂度为 O(1) 的 原地 算法。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/rotate-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：这是我自己写的环状替换
     * @param nums
     * @param k
     */
    public void rotate1(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k % nums.length == 0) return;
        int step = 0;
        int start = 0;
        int size = nums.length;
        int tmp = nums[0];
        int index = 0;
        while (step < size) {
            if((index + k) % size == start) {
                nums[start] = tmp;
                start ++;
                index = start;
                tmp = nums[index];
                step ++;
                continue;
            }
            int next = (index + k) % size;
            int change = nums [next];
            nums[next] = tmp;
            tmp = change;
            index = next;
            step ++;
        }
    }

    /**
     * 方法二：简洁代码后的环状替换,注意do-while循环
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        k = k % nums.length;
        int step = 0;
        for(int start = 0; step < nums.length; start ++) {
            int index = start;
            int pre = nums[start];
            do {
                int next = (index + k) % nums.length;
                int temp = nums[next];
                nums[next] = pre;
                pre = temp;
                index = next;
                step ++;
            } while (start != index);
        }
    }

    /**
     * 方法三：反转数组
     * @param nums
     * @param k
     */
    public void rotate3(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
        int x = 1;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
}
