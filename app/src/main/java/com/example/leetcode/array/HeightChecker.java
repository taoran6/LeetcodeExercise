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

}
