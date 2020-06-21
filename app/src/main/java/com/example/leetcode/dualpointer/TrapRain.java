package com.example.leetcode.dualpointer;

/**
 * 42. 接雨水
 */
public class TrapRain {
    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     *
     * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
     *
     * 示例:
     *
     * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出: 6
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/trapping-rain-water
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：使用两个数组记录左右两侧的最大值
     * 时间复杂度O(n),空间复杂度O(n)
     */
    public int trap(int[] height) {
        if(height == null || height.length == 0) return 0;

        //left_max[i]表示0-i的范围的最大值
        int[] left_max = new int[height.length];
        //right_max[i]表示i到最后一个数的最大值
        int[] right_max = new int[height.length];


        //初始值先写上
        left_max[0] = height[0];
        for(int i = 1; i < height.length; i++) {
            left_max[i] = Math.max(left_max[i-1], height[i]);
        }
        right_max[height.length - 1] = height[height.length - 1];
        for (int i = height.length - 2; i >= 0; i--) {
            right_max[i] = Math.max(right_max[i + 1], height[i]);
        }


        int ans = 0;
        //从1 到 n-2即可，最后两端肯定是没水的
        for (int i = 1; i < height.length - 1; i++) {
            int level = Math.min(left_max[i], right_max[i]);
            //如果这个柱子是最大值，level == height[i]，接的雨水是0
            ans += level - height[i];
        }

        return ans;
    }

    /**
     * 方法二：优化，空间复杂度优化为O(1)
     *
     * 使用双指针
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        if(height == null || height.length == 0) return 0;

        int left_max = 0;
        int right_max = 0;
        int left = 0;
        int right = height.length - 1;
        int ans = 0;


        while (left <= right) {
            left_max = Math.max(height[left], left_max);
            right_max = Math.max(height[right], right_max);

            if(left_max < right_max) {
                // 左边的小，找到左边的值，更新
                ans += left_max - height[left];
                left ++;
            } else {
                // 右边的最大值小
                ans += right_max - height[right];
                right --;
            }
        }
        return ans;
    }
}
