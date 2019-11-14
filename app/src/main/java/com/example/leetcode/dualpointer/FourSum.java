package com.example.leetcode.dualpointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 四数之和
 */
public class FourSum {
    /**
     * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，
     * 使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
     *
     * 注意：
     *
     * 答案中不可以包含重复的四元组。
     *
     * 示例：
     *
     * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
     *
     * 满足要求的四元组集合为：
     * [
     *   [-1,  0, 0, 1],
     *   [-2, -1, 1, 2],
     *   [-2,  0, 0, 2]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/4sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    List<List<Integer>> ans = new ArrayList<>();

    /**
     * 四数之和
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if(nums.length < 4) return ans;
        Arrays.sort(nums);

        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < nums.length - 3; i++) {
            //提前剪枝
            if(nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3]> target) break;
            //越过重复解
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);
            threeSum(nums, target - nums[i], i + 1, list);
            list.remove(list.size() - 1);
        }

        return ans;
    }

    /**
     * 三数之和
     * @param nums
     * @param target
     * @param start
     * @param list
     */
    private void threeSum(int[] nums, int target, int start, List<Integer> list) {
        for (int i = start; i < nums.length - 2; i++) {
            if (nums[i] + nums[i + 1] + nums[i + 2] > target) break;
            if(i > start && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);
            twoSum(nums, target - nums[i], i + 1, list);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 两数之和，双指针
     * @param nums
     * @param target
     * @param start
     * @param list
     */
    private void twoSum(int[] nums, int target, int start, List<Integer> list) {
        int startIndex = start;
        int endIndex = nums.length - 1;
        while (startIndex < endIndex) {
            int sum = nums[startIndex] + nums[endIndex];
            if(sum == target) {
                List<Integer> res = new ArrayList<>(list);
                res.add(nums[startIndex]); res.add(nums[endIndex]);
                ans.add(res);
            }

            //越过重复解
            if(sum < target) {
                do {
                    startIndex++;
                } while (startIndex < endIndex && nums[startIndex] == nums[startIndex - 1]);
                //上面这个地方判断也要加上startIndex < endIndex否则会数组越界。
                //这个跟快速排序里的判断有异曲同工之妙。
            }
            else {
                do {
                    endIndex --;
                } while (startIndex < endIndex && nums[endIndex] == nums[endIndex + 1]);
            }
        }
    }
}
