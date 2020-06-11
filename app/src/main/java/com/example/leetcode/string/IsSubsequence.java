package com.example.leetcode.string;

import java.util.ArrayList;

/**
 * 416 判断子序列
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

    public boolean isSubsequence3(String s, String t) {
        if (s.length() == 0) return true;

        int sLength = s.length();
        int tLength = t.length();

        int p = 0;
        for (int i = 0; i < tLength; i++) {
            if (t.charAt(i) == s.charAt(p)) {
                if (p == sLength - 1) return true;
                else p++;
            }
        }
        return false;
    }

    /**
     * 用时：1ms
     *
     * 时间复杂度相同，到底为啥会差这么多，只能说是用了系统API的原因吧
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

    /**
     * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种
     * 情况下，你会怎样改变代码？
     *
     * 可以将t的字符位置保存起来，用二分法查找
     */
    public boolean isSubsequence4(String s, String t) {
        //先保存字符的位置
        ArrayList<Integer>[] index = new ArrayList[256];
        for(int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if(index[c] == null) index[c] = new ArrayList<>();
            index[c].add(i);
        }

        int currentIndex = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 整个 t 压根儿没有字符 c
            if(index[c] == null) return false;

            int nextIndex = search(index[c], currentIndex);
            // 二分搜索区间中没有找到字符 c
            if(nextIndex == -1) return false;
            else {
                // 向前移动指针currentIndex
                currentIndex = index[c].get(nextIndex) + 1;
            }
        }
        return true;
    }

    /**
     * 在List中查找目标第一个大于等于target的位置的下标，如果找不到，返回-1
     * @param list
     * @param target
     * @return
     */
    private int search(ArrayList<Integer> list, int target) {
        int start = 0;
        int end = list.size() - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if(list.get(mid) == target) return mid;

            if(list.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return list.get(start) >= target ? start : -1;
    }


}
