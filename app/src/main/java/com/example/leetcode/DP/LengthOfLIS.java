package com.example.leetcode.DP;

/**
 * 最长上升子序列
 */
public class LengthOfLIS {
    /**
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     *
     * 示例:
     *
     * 输入: [10,9,2,5,3,7,101,18]
     * 输出: 4
     * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
     * 说明:
     *
     * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
     * 你算法的时间复杂度应该为 O(n^2) 。
     * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     *
     * 方法一：动态规划
     * maxLength[i]中存储nums 前 i 个数字的最长子序列长度
     * 转移方程：当 nums[i] > nums[j]时， maxLength[i] = max(maxLength[i], maxLength[j] + 1) for j in [0, i)
     *
     * 时间复杂度O(n^2)
     */
    public int lengthOfLIS(int[] nums) {

        // maxLength[i]表示以nums[i]结尾的最长递增子序列
        int[] maxLength = new int[nums.length];

        for(int i = 0; i < nums.length; i++) {
            for(int j = i - 1; j >= 0; j--) {
                if(nums[i] > nums[j]) {
                    //先找前i项的最大值
                    maxLength[i] = Math.max(maxLength[j], maxLength[i]);
                }
            }
            //最大值加1，如果是nums[i]比之前所有值都小的话，这里的maxLength[i]就是初始值0，加1之后变成1
            maxLength[i] ++;
        }

        int ans = 0;
        //找maxLength[]中的最大值即可
        for (int i = 0; i < maxLength.length; i++) {
            ans = Math.max(ans, maxLength[i]);
        }
        return ans;
    }

    /**
     * 方法二：动态规划 + 二分查找
     *
     * 我们考虑：是否可以通过重新设计状态定义，使整个 maxLength[] 为一个排序列表；这样在计算每个 maxLength[k]
     * 时，就可以通过二分法遍历 [0,k) 区间元素，将此部分复杂度由 O(N) 降至 O(logN)。
     *
     * 我们考虑维护一个列表 tails[]，其中每个元素 tails[k]的值代表 长度为 k 的子序列尾部元素的值。在遍历计
     * 算每个 tails[k]，不断更新长度为 [1,k] 的子序列尾部元素值，始终保持每个尾部元素值最小，因此这个tails[]
     * 一定是严格单调递增的。
     */
    public int lengthOfLIS2(int[] nums) {
        int[] tails = new int[nums.length + 1];
        //为了方便比较大小，这里0的位置使用最小值
        tails[0] = Integer.MIN_VALUE;
        int ans = 1;

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > tails[ans - 1]) {
                //nums[i]比之前的最大值都大
                tails[ans++] = nums[i];
            } else {
                //查找需要插入的位置
                int insert = binarySearch(tails, ans, nums[i]);
                //更新最小值
                tails[insert] = nums[i];
            }
        }
        return ans - 1;
    }

    /**
     * 查找区间是[0, end)
     * @param nums 需要查找的数组
     * @param end 终止区间点
     * @param target 需要查找的值
     * @returnn 返回查找的位置，如果找不到，返回它应该插入的位置（即第一个比它大的值的位置）
     */
    private int binarySearch(int[] nums, int end, int target) {
        int start = 0;
        while (start < end) {
            int mid = (start + end) / 2;
            if(nums[mid] == target) return mid;
            if(nums[mid] < target) {
                start = mid + 1;
            }else {
                end = mid;
            }
        }
        return start;
    }

    /**
     * 516. 最长回文子序列
     *
     * 给定一个字符串s，找到其中最长的回文子序列。可以假设s的最大长度为1000。
     *
     * 示例 1:
     * 输入:
     *
     * "bbbab"
     * 输出:
     *
     * 4
     * 一个可能的最长回文子序列为 "bbbb"。
     *
     * 示例 2:
     * 输入:
     *
     * "cbbd"
     * 输出:
     *
     * 2
     * 一个可能的最长回文子序列为 "bb"。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int longestPalindromeSubseq(String s) {
        if(s== null || s.length() == 0) return 0;
        int[][] dp = new int[s.length()][s.length()];
        for (int j = 0; j < s.length(); j++) {
            for (int i = j; i >= 0; i--) {
                if(i == j) dp[i][j] = 1;

                else {
                    if(s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i+1][j-1] + 2;
                    }else {
                        dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                    }
                }
            }
        }

        return dp[0][s.length() - 1];
    }
}
