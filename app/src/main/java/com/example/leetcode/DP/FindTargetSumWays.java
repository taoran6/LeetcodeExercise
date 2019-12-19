package com.example.leetcode.DP;

import java.util.HashMap;
import java.util.Map;

/**
 * 494. 目标和
 */
public class FindTargetSumWays {
    /**
     * 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号 + 和 -。对于数组中的任意一个
     * 整数，你都可以从 + 或 -中选择一个符号添加在前面。
     *
     * 返回可以使最终数组和为目标数 S 的所有添加符号的方法数。
     *
     * 示例 1:
     *
     * 输入: nums: [1, 1, 1, 1, 1], S: 3
     * 输出: 5
     * 解释:
     *
     * -1+1+1+1+1 = 3
     * +1-1+1+1+1 = 3
     * +1+1-1+1+1 = 3
     * +1+1+1-1+1 = 3
     * +1+1+1+1-1 = 3
     *
     * 一共有5种方法让最终目标和为3。
     * 注意:
     *
     * 数组非空，且长度不会超过20。
     * 初始的数组的和不会超过1000。
     * 保证返回的最终结果能被32位整数存下。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/target-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：遍历，使用hashMap统计所有结果
     * 105ms
     */
    public int findTargetSumWays(int[] nums, int S) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            Map<Integer, Integer> tmp = new HashMap<>();
            for (Map.Entry<Integer, Integer> kv : map.entrySet()) {
                tmp.put(kv.getKey() - nums[i],
                        tmp.getOrDefault(kv.getKey() - nums[i], 0) + kv.getValue());
                tmp.put(kv.getKey() + nums[i],
                        tmp.getOrDefault(kv.getKey() + nums[i], 0) + kv.getValue());
                map = tmp;
            }
        }

        return map.getOrDefault(S, 0);
    }

    /**
     * 方法二：动态规划
     * 又是0-1背包问题
     */
    public int findTargetSumWays2(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if(sum< Math.abs(S) || ((sum + S) & 1) != 0) return 0;

        sum = (S + sum) >> 1;
        //dp[i] 从数组nums中取某些数相加为i的取法有多少种
        int[] dp = new int[sum  + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            //这里空间优化了，要用逆序遍历
            for (int j = sum; j >= nums[i]; j--) {
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }
        return dp[sum];
    }


}
