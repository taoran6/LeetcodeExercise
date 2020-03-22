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

    /**
     * 131. 分割回文串
     *
     * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
     *
     * 返回 s 所有可能的分割方案。
     *
     * 示例:
     *
     * 输入: "aab"
     * 输出:
     * [
     *   ["aa","b"],
     *   ["a","a","b"]
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：回溯
     */
    public List<List<String>> partition1(String s) {
        List<List<String>> ans = new ArrayList<>();
        List<String> firstList = new ArrayList<>();
        partitionHelp1(s, ans, firstList);
        return ans;
    }

    private void partitionHelp1(String s, List<List<String>> ans, List<String> pre) {
        if(s.length() == 0) {
            ans.add(new ArrayList<>(pre));
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if(isPara(0, i, s.toCharArray())) {
                //加入回文前缀
                pre.add(s.substring(0, i+1));
                //递归调用后半部分
                partitionHelp1(s.substring(i+1), ans, pre);
                //回溯
                pre.remove(pre.size() - 1);
            }
        }
    }

    //判断是否是回文串
    private boolean isPara(int start, int end, char[] charArray) {
        while (start < end) {
            if(charArray[start] != charArray[end]) return false;
            start ++;
            end --;
        }
        return true;
    }

    /**
     * 优化，使用动态规划存储是否是回文串，可以参考 647. 回文子串
     */
    boolean[][] dp;
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();

        int n = s.length();

        //提前计算是否是回文串，dp[i][j]表示以i开头，j结尾的字符串是否是回文
        dp = new boolean[n][n];
        for(int j = 0; j < n; j++) {
            for (int i = 0; i <= j; i++){
                if(i == j) dp[i][j] = true;
                else dp[i][j] =  s.charAt(i) == s.charAt(j) && (i == j-1 || dp[i+1][j-1]);
            }
        }

        List<String> firstList = new ArrayList<>();
        //开始回溯
        partitionHelp(s, 0, ans, firstList);
        return ans;
    }

    private void partitionHelp(String s, int start, List<List<String>> ans, List<String> pre) {
        for (int i = 0; start + i < s.length(); i++) {
            if(dp[start][start + i]) {
                //加入回文前缀
                pre.add(s.substring(start, start + i + 1));

                if(start + i == s.length() - 1) {   //已经遍历到字符串尾部，直接这么添加是为了减少递归栈深度
                    //这里记得是新建ArrayList添加
                    ans.add(new ArrayList<>(pre));
                } else {
                    //递归调用后半部分
                    partitionHelp(s, start + i + 1, ans, pre);
                }

                //回溯
                pre.remove(pre.size() - 1);
            }
        }
    }
}
