package com.example.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 */
public class GenerateParenthesis {
    /**
     * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
     *
     * 例如，给出 n = 3，生成结果为：
     *
     * [
     *   "((()))",
     *   "(()())",
     *   "(())()",
     *   "()(())",
     *   "()()()"
     * ]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/generate-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     *
     * 方法一：动态规划
     *
     * 当我们清楚所有 i<n 时括号的可能生成排列后，对与 i=n 的情况，我们考虑整个括号排列中最左边的括号。
     * 它一定是一个左括号，那么它可以和它对应的右括号组成一组完整的括号 "( )"，我们认为这一组是相比n-1增加进来的括号。
     *
     * 那么，剩下 n-1 组括号有可能在哪呢？
     *
     * 【这里是重点，请着重理解】
     *
     * 剩下的括号要么在这一组新增的括号内部，要么在这一组新增括号的外部（右侧）。
     *
     * 既然知道了 i<n 的情况，那我们就可以对所有情况进行遍历：
     *
     * "(" + 【i=p时所有括号的排列组合】 + ")" + 【i=q时所有括号的排列组合】
     *
     * 其中 p + q = n-1，且 p q 均为非负整数。
     *
     * 事实上，当上述 p 从 0 取到 n-1，q 从 n-1 取到 0 后，所有情况就遍历完了。
     *
     * 注：上述遍历是没有重复情况出现的，即当 (p1,q1)≠(p2,q2) 时，按上述方式取的括号组合一定不同。
     *
     * 作者：yuyu-13
     * 链接：https://leetcode-cn.com/problems/generate-parentheses/solution/zui-jian-dan-yi-dong-de-dong-tai-gui-hua-bu-lun-da/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public List<String> generateParenthesis(int n) {

        List<List<String>> total = new ArrayList<>();

        total.add(new ArrayList<String>() {{add("");}});

        for(int i =1; i <= n; i++) {
            List<String> listi = new ArrayList<>();
            for (int p = 0 ; p < i; p++) {
                List<String> list_p = total.get(p);
                List<String> list_q = total.get(i - 1 - p);
                //双重迭代遍历p和q的所有排列组合
                for(String left : list_q){
                    for (String right : list_p) {
                        listi.add("(" + left + ")" + right);
                    }
                }
            }
            total.add(listi);
        }
        return total.get(n);
    }

    /**
     * 方法二：回溯
     *
     * 通过跟踪到目前为止放置的左括号和右括号的数目，如果我们还剩一个位置，我们可以开始放一个左括号。 如果它不超
     * 过左括号的数量，我们可以放一个右括号
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, String cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur);
            return;
        }

        if (open < max) {

            backtrack(ans, cur + "(", open + 1, close, max);
        }
        if (close < open) {

            backtrack(ans, cur + ")", open, close + 1, max);
        }
    }
}
