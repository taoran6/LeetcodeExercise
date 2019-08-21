package com.example.leetcode.DP;

/**
 * 最大子序和
 */
public class MaxSubArray {
    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     *
     * 示例:
     *
     * 输入: [-2,1,-3,4,-1,2,1,-5,4],
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-subarray
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 解法一：https://leetcode-cn.com/problems/maximum-subarray/solution/xiang-xi-jie-du-dong-tai-gui-hua-de-shi-xian-yi-li/
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {

        if (nums == null || nums.length == 0) return 0;

        int max_ending_here = nums[0];
        int out = nums[0];
        for (int i = 1; i < nums.length; i++) {

            max_ending_here = Math.max(max_ending_here + nums[i], nums[i]);
            out = Math.max(max_ending_here, out);
        }
        return out;
    }

    /**
     * 解法二：动态规划的简洁代码版，其实和上面的maxSubArray一样，只不过上面这个理解起来更容易一点
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int sum = 0;
        int out = nums[0];
        for(int num : nums) {
            if (sum > 0) {      //等价于 sum + num > num
                sum = sum + num;
            } else {
                sum = num;
            }
            out = Math.max(sum, out);
        }
        return out;
    }
}
