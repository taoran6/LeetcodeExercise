package com.example.leetcode.DP;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 最长上升子序列
 */
public class LengthOfLIS {
    /**
     * 300. 最长上升子序列
     *
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
     * 354. 俄罗斯套娃信封问题
     *
     * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封
     * 大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
     *
     * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
     *
     * 说明:
     * 不允许旋转信封。
     *
     * 示例:
     *
     * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
     * 输出: 3
     * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/russian-doll-envelopes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0) return 0;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o1[0] - o2[0] : o2[1] - o1[1];
            }
        });

        int[] heights = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            heights[i] = envelopes[i][1];
        }
        return lengthOfLIS2(heights);
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

        //dp[i][j]表示从i到j的子串的最长回文子序列长度是多少
        int[][] dp = new int[s.length()][s.length()];
        for (int j = 0; j < s.length(); j++) {
            //这里倒序
            for (int i = j; i >= 0; i--) {
                if(i == j) dp[i][j] = 1;

                else {
                    if(s.charAt(i) == s.charAt(j)) {
                        //这里包括了i = j-1的情况，此时dp[i+1][j-1]为默认值0
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
