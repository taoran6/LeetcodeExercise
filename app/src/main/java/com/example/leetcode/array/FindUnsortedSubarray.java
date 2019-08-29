package com.example.leetcode.array;

/**
 * 最短无序连续子数组
 */
public class FindUnsortedSubarray {
    /**
     * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
     *
     * 你找到的子数组应是最短的，请输出它的长度。
     *
     * 示例 1:
     *
     * 输入: [2, 6, 4, 8, 10, 9, 15]
     * 输出: 5
     * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
     * 说明 :
     *
     * 输入的数组长度范围在 [1, 10,000]。
     * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 解法一最优解：从左到右循环，记录最大值为 max，若 nums[i] < max, 则表明位置 i 需要调整, 循环结束，记录需要调整的最大
     * 位置 i 为 maxp; 同理，从右到左循环，记录最小值为 min, 若 nums[i] > min, 则表明位置 i 需要调整，
     * 循环结束，记录需要调整的最小位置 i 为 minp.
     *
     * 解法二：先排序，然后对比两个数组
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        if(nums == null || nums.length == 0 || nums.length == 1) return 0;

        int max = nums[0];
        int maxp = -1;
        int min = nums[nums.length - 1];
        int minp = -1;
        for(int i = 1; i< nums.length; i++) {
            if (nums[i] < max) maxp = i;
            else max = nums[i];
        }
        if(maxp == -1) return 0;
        for (int i = nums.length -2; i>=0; i--){
            if(nums[i] > min) minp = i;
            else min = nums[i];
        }
        return maxp - minp + 1;
    }
}
