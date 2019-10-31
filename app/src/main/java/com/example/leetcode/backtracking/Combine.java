package com.example.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合
 */
public class Combine {
    /**
     * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
     *
     * 示例:
     *
     * 输入: n = 4, k = 2
     * 输出:
     * [
     *   [2,4],
     *   [3,4],
     *   [2,3],
     *   [1,2],
     *   [1,3],
     *   [1,4],
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/combinations
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：回溯加剪枝
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if(k > n || k <= 0) return ans;

        backTracking(1, n, k, new ArrayList<>(), ans);
        return ans;
    }

    private void backTracking(int index, int n, int k, List<Integer> list, List<List<Integer>> ans) {
        //这里边界不是 i <= n, 原因是当i + k - 1 - list.size() > n就表示加上后面的数已经凑不够k个数，不用再
        // 递归了，剪枝即可
        for(int i = index; i + k - 1 - list.size() <=n; i++){
            list.add(i);
            if(list.size() == k) ans.add(new ArrayList<>(list));
            else {
                backTracking(i + 1, n, k, list, ans);
            }
            //回溯
            list.remove(list.size() - 1);
        }
    }

    /**
     * 方法二：字典序 (二进制排序) 组合
     * 不使用递归
     *
     * 官方的第二种解法，其实代码不太好理解
     * https://leetcode-cn.com/problems/combinations/solution/zu-he-by-leetcode/
     */
    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if(k > n || k <= 0) return ans;

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <=k; i++) {
            list.add(i);
        }
        list.add(n + 1);

        int j = 0;
        while (j < k) {
            ans.add(new ArrayList<>(list.subList(0, k)));
            j = 0;
            //说真的，这个特别不好理解，就是把最数字重新从1开始排，直到找到第一个list.get(j + 1) != list.get(j) + 1
            while ((j < k) && (list.get(j + 1) == list.get(j) + 1))
                list.set(j, j++ + 1);
            list.set(j, list.get(j) + 1);
        }

        return ans;
    }
}
