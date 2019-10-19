package com.example.leetcode.array;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * 下一个排列
 */
public class NextPermutation {
    /**
     * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
     *
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
     *
     * 必须原地修改，只允许使用额外常数空间。
     *
     * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
     * 1,2,3 → 1,3,2
     * 3,2,1 → 1,2,3
     * 1,1,5 → 1,5,1
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/next-permutation
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * emmmm.....这题目描述太...（黑人问号.jpg）看了评论，其实就是找出这个数组排序出的所有数中，刚好比当前数
     * 大的那个数
     *
     * 比如当前 nums = [1,2,3]。这个数是123，找出1，2，3这3个数字排序可能的所有数，排序后，比123大的那个数也
     * 就是132
     *
     * 如果当前 nums = [3,2,1]。这就是1，2，3所有排序中最大的那个数，那么就返回1，2，3排序后所有数中最小的
     * 那个，也就是1，2，3 -> [1,2,3]
     */
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length <= 1) return;

        int length = nums.length;

        int i = length - 2;
        for( ; i >= 0; i --) {
            if(nums[i + 1] > nums[i]) break;
        }

        if(i == -1) {    //说明整个数组递减的
            reverse(nums, 0, length);
            return;
        }

        for(int j = length - 1; j > i; j --) {
            if(nums [j] > nums[i]) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                //这里相当于Arrays.sort(nums, i +1, length)，由于这个区间本来就是单调递减的，所以排序退化为反转
                reverse(nums, i + 1, length);
                break;
            }
        }

    }

    private void reverse(int[] nums, int from, int to) {
        for(int i = 0; i < ((to - from) / 2); i++) {
            int tmp = nums[from + i];
            nums[from + i] = nums[to - 1 - i];
            nums[to - 1 - i] = tmp;
        }
    }
}
