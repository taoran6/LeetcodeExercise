package com.example.leetcode.DP;

/**
 * 152. 乘积最大子序列
 */
public class MaxProduct {
    /**
     * 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
     *
     * 示例 1:
     *
     * 输入: [2,3,-2,4]
     * 输出: 6
     * 解释: 子数组 [2,3] 有最大乘积 6。
     * 示例 2:
     *
     * 输入: [-2,0,-1]
     * 输出: 0
     * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 解法思路：遍历数组时计算当前最大值，不断更新
     * 令max为当前最大值，则当前最大值为 max = max(max * nums[i], nums[i])
     * 由于存在负数，那么会导致最大的变最小的，最小的变最大的。因此还需要维护当前最小值min，
     * min = min(min * nums[i], nums[i])
     * 当负数出现时则max与min进行交换再进行下一步计算
     * 时间复杂度：O(n)
     *
     * 作者：guanpengchn
     * 链接：https://leetcode-cn.com/problems/maximum-product-subarray/solution/hua-jie-suan-fa-152-cheng-ji-zui-da-zi-xu-lie-by-g/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int maxProduct(int[] nums) {
        if(nums.length == 0) return 0;

        int ans = nums[0];
        //维护当前最大值，这个最大值是必包含当前最后一个元素的子数组乘积的最大值。
        //只需要知道前一个最大值，节省空间不需要int[]。
        int max = nums[0];
        //由于存在负数，需要维护当前最小值
        int min = nums[0];

        for(int i = 1; i < nums.length; i++) {
            int newMax = max * nums[i];
            int newMin = min * nums[i];
            if(newMax > newMin){
                max = Math.max(newMax, nums[i]);
                min = Math.min(newMin, nums[i]);
            } else {
                min = Math.min(newMax, nums[i]);
                max = Math.max(newMin, nums[i]);
            }

            //更新全局的最大值
            ans = Math.max(max, ans);
        }

        return ans;
    }
}
