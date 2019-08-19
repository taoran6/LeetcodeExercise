package com.example.leetcode.middle;


import java.util.HashSet;
import java.util.Set;

/**
 *  无重复字符的最长子串
 */
public class LengthOfLongestSubstring {
    //todo 其他解法待写
    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 示例 1:
     *
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     *
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     *
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) return 0;
        if(s.length() == 1) return 1;

        int result = 1;
        String pre = s.substring(0, 1);
        for (int i = 1; i < s.length(); i++) {
            String newString = s.substring(i, i + 1);
            int index = pre.indexOf(newString);
            if ( index == -1) { //不存在重复
                result ++;
                pre = pre + newString;
            } else {        //存在重复
                //从重复的地方开始截取
                String subString = s.substring(index + 1);
                if (subString.length() <= result) return result;    //  提前结束
                /**采用递归时间和内存消耗很大*/
                int subResult = lengthOfLongestSubstring(subString);
                return result >=  subResult ? result : subResult;

            }
        }

        return result;
    }

    public int lengthOfLongestSubstring2(String s) {
        if(s == null || s.length() == 0) return 0;
        if(s.length() == 1) return 1;

        int start = 0;
        int end = 1;
        int result = 1;
        Set<Character> set = new HashSet<>();
        set.add(s.charAt(0));

        while(start < s.length() && end < s.length()) {
            if (! set.contains(s.charAt(end))) {
                set.add(s.charAt(end));
                end ++;
                result = Math.max(result, end - start);
            } else {
                set.remove(s.charAt(start));
                start ++;
            }
        }

        return result;
    }
}
