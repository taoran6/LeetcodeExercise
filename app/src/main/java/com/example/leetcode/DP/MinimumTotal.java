package com.example.leetcode.DP;

import java.util.List;

public class MinimumTotal {
    /**
     * 120. 三角形最小路径和
     *
     * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
     *
     * 例如，给定三角形：
     *
     * [
     *      [2],
     *     [3,4],
     *    [6,5,7],
     *   [4,1,8,3]
     * ]
     * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/triangle
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null || triangle.size() == 0) return 0;

        // dp[i]存储当前行的第i个位置的最小路径
        int[] dp = new int[triangle.size()];
        dp[0] = triangle.get(0).get(0);
        for(int i = 1; i < triangle.size(); i++) {
            // 先更新最后一个
            List<Integer> list = triangle.get(i);
            dp[i] = dp[i-1] +  list.get(i);
            // 由于存储优化，需要从后往前遍历
            for (int j = i-1; j > 0; j--) {
                dp[j] = list.get(j) + Math.min(dp[j], dp[j-1]);
            }
            //最后更新第一个（都一个和最后一个都单独考虑）
            dp[0] = dp[0] + list.get(0);
        }

        int ans = Integer.MAX_VALUE;
        for(int i : dp) {
            ans = Math.min(i, ans);
        }
        return ans;
    }

}
