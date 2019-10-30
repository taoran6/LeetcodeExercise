package com.example.leetcode.string;

/**
 * 判断子序列
 */
public class IsSubsequence {
    /**
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     * <p>
     * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串
     * （长度 <=100）。
     * <p>
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
     * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     * <p>
     * 示例 1:
     * s = "abc", t = "ahbgdc"
     * <p>
     * 返回 true.
     * <p>
     * 示例 2:
     * s = "axc", t = "ahbgdc"
     * <p>
     * 返回 false.
     * <p>
     * 后续挑战 :
     * <p>
     * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种
     * 情况下，你会怎样改变代码？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/is-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @param t
     * @return
     *
     * 方法一：双指针，用时9ms
     */
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;

        int sLength = s.length();
        int tLength = t.length();

        char[] shortChars = s.toCharArray();
        char[] longChars = t.toCharArray();
        int p = 0;
        for (int i = 0; i < tLength; i++) {
            if (longChars[i] == shortChars[p]) {
                if (p == sLength - 1) return true;
                else p++;
            }
        }
        return false;
    }

    /**
     * 用时：1ms
     * TODO 使用indexOf(), 为什么比原生的查找快很多？
     */
    public boolean isSubsequence2(String s, String t) {
        int index = 0;
        int i = 0;
        while (index < s.length() && t.indexOf(s.charAt(index), i) >= i) {
            i = t.indexOf(s.charAt(index), i) + 1;
            index ++;
        }
        return index == s.length();
    }
}
