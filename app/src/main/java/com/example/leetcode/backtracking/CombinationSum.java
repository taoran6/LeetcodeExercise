package com.example.leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 组合总和
 */
public class CombinationSum {
    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     *
     * candidates 中的数字可以无限制重复被选取。
     *
     * 说明：
     *
     * 所有数字（包括 target）都是正整数。
     * 解集不能包含重复的组合。 
     * 示例 1:
     *
     * 输入: candidates = [2,3,6,7], target = 7,
     * 所求解集为:
     * [
     *   [7],
     *   [2,2,3]
     * ]
     * 示例 2:
     *
     * 输入: candidates = [2,3,5], target = 8,
     * 所求解集为:
     * [
     *   [2,2,2,2],
     *   [2,3,3],
     *   [3,5]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/combination-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param candidates
     * @param target
     * @return
     *
     * 方法：回溯加剪枝
     * 参考题解 https://leetcode-cn.com/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
     */
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if(candidates.length == 0) return ans;
        Arrays.sort(candidates); //排序防止出现重复解

        findRes(candidates, 0, target, new ArrayList<>());
        return ans;
    }

    /**
     * 递归寻找目标解
     *
     * @param candidates
     * @param start
     * @param target
     * @param pre
     */
    private void findRes (int[] candidates, int start, int target, List<Integer> pre) {
        for (int i = start; i < candidates.length; i ++) {
            int sub = target - candidates[i];
            if(sub >= 0) {
                pre.add(candidates[i]);
                if(sub == 0) {
                    //正好减到0，为目标解
                    ans.add(new ArrayList<>(pre));
                    // 回溯
                    pre.remove(pre.size() - 1);
                    //剪枝
                    break;
                } else {        // sub > 0
                    findRes(candidates, i, sub, pre);
                    //回溯
                    pre.remove(pre.size() - 1);
                }
            }else {
                // sub < 0 剪枝
                break;
            }
        }
    }
}
