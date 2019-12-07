package com.example.leetcode.array;

import java.util.Arrays;

/**
 * 高度检查器
 */
public class HeightChecker {
    /**
     * 学校在拍年度纪念照时，一般要求学生按照 非递减 的高度顺序排列。
     *
     * 请你返回至少有多少个学生没有站在正确位置数量。该人数指的是：能让所有学生以 非递减 高度排列的必要移动人数。
     *
     *  
     *
     * 示例：
     *
     * 输入：[1,1,4,2,1,3]
     * 输出：3
     * 解释：
     * 高度为 4、3 和最后一个 1 的学生，没有站在正确的位置。
     *  
     *
     * 提示：
     *
     * 1 <= heights.length <= 100
     * 1 <= heights[i] <= 100
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/height-checker
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：使用自带的排序，时间复杂度O(nlogn)
     */

    public int heightChecker(int[] heights) {
        if(heights == null) return  0;
        int[] sorted = heights.clone();
        Arrays.sort(sorted);
        int ans = 0;
        for(int i = 0; i < heights.length; i++) {
            if(heights[i] != sorted[i]) ans ++;
        }
        return ans;
    }

    /**
     * 方法二：桶排序思想，但并不需要直接对数组排序
     * 根据提示：1 <= heights[i] <= 100
     * 时间复杂度O(n)
     */
    public int heightChecker2(int[] heights) {
        int[] buck = new int[101];
        for(int i = 0; i < heights.length; i++) {
            buck[heights[i]] ++;
        }

        int ans = 0;
        int p = 0;      //在heights[]中移动的指针
        for(int i = 1; i < buck.length; i++) {      //遍历桶，遍历顺序实际上就是按照大小顺序了
            while (buck[i] > 0) {
                if(heights[p] != i) { //排好序的值与heights[j]中的值做比较
                    ans ++;
                }
                p ++;       //height指针前进一位
                buck[i] --;     //相当于桶指针前进一位
            }
        }
        return ans;
    }

    /**
     * 665. 非递减数列
     * 给定一个长度为 n 的整数数组，你的任务是判断在最多改变 1 个元素的情况下，该数组能否变成一个非递减数列。
     *
     * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (1 <= i < n)，满足 array[i] <= array[i + 1]。
     *
     * 示例 1:
     *
     * 输入: [4,2,3]
     * 输出: True
     * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
     * 示例 2:
     *
     * 输入: [4,2,1]
     * 输出: False
     * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
     * 说明:  n 的范围为 [1, 10,000]。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/non-decreasing-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 1256123
     * 13423
     *
     * 天呐这题好难，提交了4次都不通过呜呜呜~~~
     * 看了一下别人的题解
     *
     * 在遍历的过程中修改数组，如果出现nums[i]>nums[i+1]，则需要修改一个数，修改flag标志位，而修改分两种情况：
     * 1.如果i是0或者i-1的值小于i+1的值，就将nums[i]减小为nums[i+1]；
     * 2.如果i+1的值小于i-1的值，就将nums[i+1]增大为nums[i]；
     *
     * 作者：zackqf
     * 链接：https://leetcode-cn.com/problems/non-decreasing-array/solution/javade-liang-chong-jie-ti-fang-fa-by-zackqf/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean checkPossibility(int[] nums) {
        boolean flag = false;

        for(int i = 0; i < nums.length - 1; i++) {
            if(nums[i] > nums[i+1]) {
                if(flag) return false;
                flag = true;
                if(i == 0 || nums[i - 1] <= nums[i + 1]) nums[i] = nums[i+1];
                else nums[i+1] = nums[i];
            }
        }

        return true;
    }

}
