package com.example.leetcode.DP;

import java.util.Arrays;

/**
 * 416. 分割等和子集
 */
public class CanPartition {
    /**
     * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     *
     * 注意:
     *
     * 每个数组中的元素不会超过 100
     * 数组的大小不会超过 200
     * 示例 1:
     *
     * 输入: [1, 5, 11, 5]
     *
     * 输出: true
     *
     * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
     *  
     *
     * 示例 2:
     *
     * 输入: [1, 2, 3, 5]
     *
     * 输出: false
     *
     * 解释: 数组不能分割成两个元素和相等的子集.
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 这个题目看了题解，还是因为不熟悉0-1背包问题所以做不出来
     * 方法一：二维动态规划
     * 题解：https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/0-1-bei-bao-wen-ti-xiang-jie-zhen-dui-ben-ti-de-yo/
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        //先求和，奇数直接返回false
        if(sum % 2 != 0) return false;
        int half = sum / 2;

        //dp[i][j]表示从前i-1个数里面能不能取出来的几个数的和等于j
        boolean[][] dp = new boolean[nums.length][half + 1];
        //这里要做一下判断，否则数组越界
        if(nums[0] <= half) dp[0][nums[0]] = true;
        //关于0的初始化
        dp[0][0] = true;

        for (int i = 1; i < nums.length; i++) {
            for(int j = 0; j <= half; j++) {
                //这里是核心的转移方程
                if(j - nums[i] >= 0) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
            //提前结束
            if(dp[i][half]) return true;
        }

        return false;
    }

    /**
     * 方法二：动态规划，二维转一维，更新时需要从后往前遍历
     * 速度有大幅度提升
     */
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for(int num : nums) {
            sum += num;
        }
        //先求和，奇数直接返回false
        if(sum % 2 != 0) return false;
        int half = sum / 2;


        boolean[] dp = new boolean[half + 1];
        //这里要做一下判断，否则数组越界
        if(nums[0] <= half) dp[nums[0]] = true;
        //关于0的初始化
        dp[0] = true;

        for (int i = 1; i < nums.length; i++) {
            if(dp[half]) return true;
            for(int j = half; j >= 0; j--) {
                //这里是核心的转移方程
                if(j >= nums[i])    dp[j] = dp[j] || dp[j - nums[i]];
            }
        }

        return dp[half];
    }


}
