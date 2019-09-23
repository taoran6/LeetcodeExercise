package com.example.leetcode.dualpointer;

import android.content.Intent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 */
public class ThreeSum {
    /**
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
     *
     * 注意：答案中不可以包含重复的三元组。
     *
     * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     *
     * 满足要求的三元组集合为：
     * [
     *   [-1, 0, 1],
     *   [-1, -1, 2]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        /**
         * 首先对数组进行排序，排序后固定一个数 nums[i]，再使用左右指针指向 nums[i]nums[i]后面的两端，
         * 数字分别为 nums[L] 和 nums[R]，计算三个数的和 sum判断是否满足为 0，满足则添加进结果集
         *
         * 作者：guanpengchn
         * 链接：https://leetcode-cn.com/problems/3sum/solution/hua-jie-suan-fa-15-san-shu-zhi-he-by-guanpengchn/
         * 来源：力扣（LeetCode）
         * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         */
        Arrays.sort(nums);
        List<List<Integer>> ansList = new ArrayList<>();

        //大于0可以跳出迭代了
        for(int i = 0; i < nums.length - 2 && nums[i] <= 0; i++) {
            //跳过重复的数字
            if(i != 0 && nums[i] == nums[i - 1]) continue;
            findNumber(nums, - nums[i], i + 1, ansList);
        }

        return ansList;
    }

    /**
     * 基于两数之和的双指针
     * @param nums
     * @param target
     * @param start
     * @param list
     */
    private void findNumber(int[] nums, int target, int start, List<List<Integer>> list) {
            int s = start;
            int e = nums.length - 1;
            while (s < e) {
                int ns = nums[s];
                int ne = nums[e];
                if (ns + ne == target) {
                    List<Integer> list1 = new ArrayList<>();
                    list1.add(-target);
                    list1.add(ns);
                    list1.add(ne);
                    list.add(list1);
                    //跳过重复的数字
                    while (s < nums.length && nums[s] == ns) {
                        s++;
                    }
                    while (e > start && nums[e] == ne) {
                        e--;
                    }
                } else if(ns + ne < target) {
                    s++;
                } else {
                    e--;
                }
            }
    }

    /**
     * 最接近的三数之和
     *
     * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 
     * 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
     *
     * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
     *
     * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum-closest
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        if(nums == null || nums.length < 3) return Integer.MAX_VALUE;

        int closet = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;
        Arrays.sort(nums);

        //基于三数之和的双指针
        for(int i = 0; i < nums.length - 2; i ++) {
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end){
                int sum = nums[start] + nums[end] + nums[i];
                if(sum == target) return target;
                else if(sum > target) {
                    end --;
                }else start ++;
                int abs = Math.abs(sum - target);
                if(abs < closet) {
                    ans = sum;
                    closet = abs;
                }
            }
        }

        return ans;
    }


    /**
     * 在threeSumClosest()的基础上进行了优化
     */
    public int threeSumClosest2(int[] nums, int target) {
        if(nums == null || nums.length < 3) return Integer.MAX_VALUE;

        //采用这种方法初始化比较好
        int ans = nums[0] + nums[1] + nums[2];
        int minAbs = Integer.MAX_VALUE;

        Arrays.sort(nums);

        int rangMax = nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3];
        // 数组三数和的最大的值比目标还要小,没必要寻找区间其他值的和了
        if(rangMax < target) {
            return rangMax;
        }

        for(int i = 0; i < nums.length - 2; i ++) {
            int start = i + 1;
            int end = nums.length - 1;

            int rangMin = nums[i] + nums[i + 1] + nums[i + 2];
            int abs;

            // 区间最小值比目标大, 没必要寻找区间其他值的和了
            if(rangMin > target) {
                abs = rangMin - target;
                if(abs < minAbs) {
                    ans = rangMin;
                }
                return ans;
            }

            // 三数之和的双指针
            while (start < end){
                int sum = nums[start] + nums[end] + nums[i];
                if(sum == target) return target;        //直接返回
                else if(sum > target) {
                    end --;
                }else start ++;

                abs = Math.abs(sum - target);
                if(abs < minAbs) {
                    ans = sum;
                    minAbs = abs;
                }
            }
        }

        return ans;
    }
}
