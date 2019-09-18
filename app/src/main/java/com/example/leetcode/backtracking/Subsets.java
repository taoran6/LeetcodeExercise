package com.example.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 子集
 */
public class Subsets {
    /**
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     *
     * 说明：解集不能包含重复的子集。
     *
     * 示例:
     *
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/subsets
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     *
     * 方法一：递归添加
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        ans.add(list);
        traceBack(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void traceBack(int[] nums, int start, List<Integer> list, List<List<Integer>> ans) {
        for(int i = start; i < nums.length; i ++) {
            list.add(nums[i]);
            ans.add(new ArrayList<>(list));
            traceBack(nums, i + 1, list, ans);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 方法二：循环
     * 直接从后遍历，遇到一个数就把所有子集加上该数组成新的子集，遍历完毕即是所有子集
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        for(int i = nums.length - 1; i >= 0; i --) {
            //注意这里不能写成for (int j = 0; j < ans.length; j ++)， 因为ans的长度在内层循环中时刻变化
            int size = ans.size();
            for (int j = 0; j < size; j ++) {
                List<Integer> list = new ArrayList<>(ans.get(j));
                list.add(0, nums[i]);
                ans.add(list);
            }
        }
        return ans;
    }
}
