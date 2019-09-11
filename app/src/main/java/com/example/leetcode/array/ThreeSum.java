package com.example.leetcode.array;

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
}
