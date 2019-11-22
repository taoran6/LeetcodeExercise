package com.example.leetcode.stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * 下一个更大元素
 */
public class NextGreaterElement {
    /**
     * 496. 下一个更大元素 I
     *
     * 给定两个没有重复元素的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在
     *  nums2 中的下一个比其大的值。
     *
     * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，
     * 对应位置输出-1。
     *
     * 示例 1:
     *
     * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
     * 输出: [-1,3,-1]
     * 解释:
     *     对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
     *     对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
     *     对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
     * 示例 2:
     *
     * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
     * 输出: [3,-1]
     * 解释:
     *     对于num1中的数字2，第二个数组中的下一个较大数字是3。
     *     对于num1中的数字4，第二个数组中没有下一个更大的数字，因此输出 -1。
     * 注意:
     *
     * nums1和nums2中所有元素是唯一的。
     * nums1和nums2 的数组大小都不超过1000。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/next-greater-element-i
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 思路：使用单调栈
     *
     * ps:如果想要优化执行时间的话可以用int[]和int top代替Stack，用int[]代替Map，这里思路是一样的，就不写了。
     */
    public int[] nextGreaterElementI(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = nums2.length - 1; i >= 0; i--) {
            while (!stack.empty() && nums2[i] >= stack.peek()) {
                stack.pop();
            }
            if(stack.isEmpty()) map.put(nums2[i], -1);
            else map.put(nums2[i], stack.peek());
            stack.push(nums2[i]);
        }

        int[] ans = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }

    /**
     * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下
     * 一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的
     * 数。如果不存在，则输出 -1。
     *
     * 示例 1:
     *
     * 输入: [1,2,1]
     * 输出: [2,-1,2]
     * 解释: 第一个 1 的下一个更大的数是 2；
     * 数字 2 找不到下一个更大的数；
     * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
     * 注意: 输入数组的长度不会超过 10000。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/next-greater-element-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int[] nextGreaterElementsII(int[] nums) {
        int[] ans = new int[nums.length];

        Stack<Integer> stack = new Stack<>();

        for(int i = nums.length - 1; i >= 0; i--) {
            while (!stack.empty() && nums[i] >= stack.peek()) {
                stack.pop();
            }
            if(stack.empty()) ans[i] = Integer.MIN_VALUE;
            else ans[i] = stack.peek();
            stack.push(nums[i]);
        }

        for(int i = nums.length - 1; i >=0; i--) {
            //这里使用Integer.MIN_VALUE这个特殊值而不是-1的原因是上一轮可能会出现下一个更大的数为-1的情况
            //比如[3,-2,-1]的情况
            if(ans[i] == Integer.MIN_VALUE) {
                while (!stack.empty() && nums[i] >= stack.peek()) {
                    stack.pop();
                }
                if(!stack.empty()) ans[i] = stack.peek();
                else ans[i] = -1;
            }
        }
        return ans;
    }

    //TODO 取余的方法
}
