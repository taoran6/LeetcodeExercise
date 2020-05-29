package com.example.leetcode.string;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *  无重复字符的最长子串
 */
public class LengthOfLongestSubstring {
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

    /**
     * 方法二：滑动窗口
     * @param s
     * @return
     */
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

    public int lengthOfLongestSubstring5(String s) {
        if(s == null || s.length() == 0) return 0;

        int ans = 0;
        int left = 0; int right = 0;
        Set<Character> set = new HashSet<>();

        while(right < s.length()) {
            char c = s.charAt(right);
            boolean success = set.add(c);
            right ++;


            while(!success) {
                char c2 = s.charAt(left);

                if(c2 == c) {
                    //这里由于right已经++了，所以只需更新标志位，不需要remove
                    success = true;
                } else {
                    set.remove(c2);
                }
                left ++;
            }
            ans = Math.max(ans, right - left);
        }
        return ans;
    }

    /**
     * 方法三：优化的滑动窗口
     */
    public int lengthOfLongestSubstring3(String s) {
        if (s == null || s.length() == 0) return 0;
        int ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        map.put(s.charAt(0), 0);
        for(int end = 0, start = 0; end < s.length(); end++) {
            if(map.containsKey(s.charAt(end))) {
                start = Math.max(map.get(s.charAt(end)), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }

    /**
     * 方法四：用一个整数数组作为直接访问表来替换 Map
     */
    public int lengthOfLongestSubstring4(String s) {
        if (s == null || s.length() == 0) return 0;
        int ans = 0;
        int[] index = new int[128];
        for(int end = 0, start = 0; end < s.length(); end++) {
            start = Math.max(index[s.charAt(end)], start);
            ans = Math.max(ans, end - start + 1);
            index[s.charAt(end)] = end + 1;
        }
        return ans;
    }
}
