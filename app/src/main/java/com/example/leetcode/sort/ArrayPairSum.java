package com.example.leetcode.sort;

import java.util.Arrays;

/**
 * 数组拆分
 */
public class ArrayPairSum {
    /**
     * 561. 数组拆分 I
     *
     * 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对, 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得
     * 从1 到 n 的 min(ai, bi) 总和最大。
     *
     * 示例 1:
     *
     * 输入: [1,4,3,2]
     *
     * 输出: 4
     * 解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4).
     * 提示:
     *
     * n 是正整数,范围在 [1, 10000].
     * 数组中的元素范围在 [-10000, 10000].
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/array-partition-i
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 思路是每一对都牺牲了那个最大值，因此需要使牺牲的最大值最小
     * 方法一：直接排序
     */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length; i = i + 2) {
            ans += nums[i];
        }
        return ans;
    }

    /**
     * 方法二：桶排序
     */
    public int arrayPairSum2(int[] nums) {
        int[] arrays = new int[20001];
        for (int i : nums) {
            arrays[i + 10000] ++;
        }

        int ans = 0;
        //flag = 1表示上一个多余了一个，flag = 0表示上一个正好配对完
        int flag = 0;
        for (int i = 20000; i >= 0; i--) {
            int n = arrays[i];
            if(n != 0){
                int number = (n + flag)/ 2;
                ans += (i - 10000) * number;
                flag = (n - flag) % 2;
            }
        }
        return ans;
    }
}
