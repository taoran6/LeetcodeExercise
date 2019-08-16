package com.example.leetcode.middle;

/**
 *  无重复字符的最长子串
 */
public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) return 0;
        if(s.length() == 1) return 1;

        int result = 1;
        String sub = s.substring(0, 1);


        return 1;
    }
}
