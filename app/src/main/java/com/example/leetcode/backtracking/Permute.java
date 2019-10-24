package com.example.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 全排列
 */
public class Permute {
    /**
     * 给定一个没有重复数字的序列，返回其所有可能的全排列。
     *
     * 示例:
     *
     * 输入: [1,2,3]
     * 输出:
     * [
     *   [1,2,3],
     *   [1,3,2],
     *   [2,1,3],
     *   [2,3,1],
     *   [3,1,2],
     *   [3,2,1]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/permutations
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     *
     * 方法一：回溯
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            ans.add(new ArrayList<>());
            return ans;
        }
        boolean[] visited = new boolean[nums.length];
        traceBack(nums, visited, new ArrayList<>(), ans);
        return ans;
    }

    /**
     * 回溯
     * @param nums
     * @param visited
     * @param list
     * @param ans
     */
    private void traceBack(int[] nums, boolean[] visited, List<Integer> list, List<List<Integer>> ans) {
        for (int i = 0; i < nums.length; i++) {
            if(visited[i]) continue;
            list.add(nums[i]);
            visited[i] = true;
            if (list.size() == nums.length) {
                ans.add(new ArrayList<>(list));
            } else {
                traceBack(nums, visited, list, ans);
            }
            //这里记得置为false回溯
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }

    /**
     * 方法二：递归交换
     *
     * 从一个原始排列开始，第 1 个元素与依次与后面的所有元素交换，这种操作是递归进行的。没有回溯算法的思路经典。
     *
     * 例如：
     *
     * 1 + permute([2, 3, 4])
     *
     * 2 + permute([1, 3, 4])
     *
     * 3 + permute([1, 2, 4])
     *
     * 4 + permute([1, 2, 3])
     *
     * 作者：liweiwei1419
     * 链接：https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            ans.add(new ArrayList<>());
            return ans;
        }
        help(ans, 0, nums);
        return ans;
    }

    private void help(List<List<Integer>> ans, int start, int[] nums) {
        //此时nums[]已经保存了所有的数字排列
        if(start == nums.length - 1) {
            List<Integer> list = new ArrayList<>();
            for (int k = 0; k < nums.length; k++) list.add(nums[k]);
            ans.add(list);
            return;
        }

        //i从start开始，自身不交换的排列也考虑进去
        for (int i = start; i < nums.length; i++) {
            int tmp = nums[start];
            nums[start] = nums[i];
            nums[i] = tmp;

            help(ans, start + 1, nums);

            tmp = nums[start];
            nums[start] = nums[i];
            nums[i] = tmp;
        }
    }

    /**
     * 全排列ii
     *
     * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
     *
     * 示例:
     *
     * 输入: [1,1,2]
     * 输出:
     * [
     *   [1,1,2],
     *   [1,2,1],
     *   [2,1,1]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/permutations-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return ans;
        }
        boolean[] visited = new boolean[nums.length];
        traceBackUnique(nums, visited, new ArrayList<>(), ans);
        return ans;
    }

    private void traceBackUnique(int[] nums, boolean[] visited, List<Integer> list, List<List<Integer>> ans) {
        HashSet<Integer> thisLevel = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if(visited[i] || thisLevel.contains(nums[i])) continue;

            thisLevel.add(nums[i]);
            visited[i] = true;
            list.add(nums[i]);
            if(list.size() == nums.length) {
                ans.add(new ArrayList<>(list));
            } else {
                traceBackUnique(nums, visited, list, ans);
            }
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            ans.add(new ArrayList<>());
            return ans;
        }
        helpUnique(ans, 0, nums);
        return ans;
    }

    private void helpUnique(List<List<Integer>> ans, int start, int[] nums) {
        //此时nums[]已经保存了所有的数字排列
        if(start == nums.length - 1) {
            List<Integer> list = new ArrayList<>();
            for (int k = 0; k < nums.length; k++) list.add(nums[k]);
            ans.add(list);
            return;
        }

        //i从start开始，自身不交换的排列也考虑进去
        HashSet<Integer> set = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            if(set.contains(nums[i])) continue;

            set.add(nums[i]);
            int tmp = nums[start];
            nums[start] = nums[i];
            nums[i] = tmp;

            helpUnique(ans, start + 1, nums);

            tmp = nums[start];
            nums[start] = nums[i];
            nums[i] = tmp;
        }
    }
    //TODO 题解还有更多解法，不过我觉得我自己的解法容易理解
}
