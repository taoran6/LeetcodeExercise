package com.example.leetcode.slidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 最小覆盖子串
 */
public class MinWindow {
    /**
     * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
     *
     * 示例：
     *
     * 输入: S = "ADOBECODEBANC", T = "ABC"
     * 输出: "BANC"
     * 说明：
     *
     * 如果 S 中不存这样的子串，则返回空字符串 ""。
     * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-window-substring
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if(t.isEmpty() ) return "";
        int start = 0;
        int minLength = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> needs = new HashMap<>();

        for(int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            needs.put(c, needs.getOrDefault(c,0) + 1);
        }

        int match = 0;

        while (right < s.length()) {
            char c1 = s.charAt(right);
            if(needs.containsKey(c1)) {
                window.put(c1, window.getOrDefault(c1, 0) + 1);
                if(window.get(c1).equals(needs.get(c1))) match++;
            }
            right ++;

            while (match == needs.size()) {
                if ((right - left) < minLength) {
                    start = left;
                    minLength = right - left;
                }

                char c2 = s.charAt(left);
                if(needs.containsKey(c2)) {
                    window.put(c2, window.get(c2) - 1);
                    if(window.get(c2) < needs.get(c2)) match--;
                }
                left ++;
            }
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(start, start + minLength);
    }

    public String minWindow2(String s, String t) {
        if(t == null || s == null || t == "" || s == "") return "";
        int left = 0;
        int right = 0;
        String ans = "";
        int length = Integer.MAX_VALUE;

        // 存储t字符串的统计
        Map<Character, Integer> strMap = new HashMap();
        // 存储滑动窗口内的字符统计
        Map<Character, Integer> winMap = new HashMap();

        for(char c : t.toCharArray()) {
            strMap.put(c, strMap.getOrDefault(c, 0) + 1);
        }

        int match = 0;//记录有多少种字符已成功匹配

        while(right < s.length()) {
            /**rirht 右移*/
            if(match < strMap.size()) {
                char c = s.charAt(right);
                if(strMap.containsKey(c)) {
                    winMap.put(c, winMap.getOrDefault(c, 0) + 1);

                    //这里一定要用equals，不能用 == ，Integer会缓存频繁使用的数值，
                    //数值范围为-128到127，在此范围内直接返回缓存值。
                    //超过该范围就会new 一个对象。所以测试用例较大时不通过
                    //浪费了我一个小时时间啊啊啊啊
                    //阿里巴巴开发手册说过了，Integer要比较的话必须用.equals方法，这个IDEA也是有提示的。
                    if(winMap.get(c).equals(strMap.get(c))) match ++;   //匹配数加1
                }
                right ++;
            }

            /**left 左移*/
            while(match == strMap.size()) {
                if(right - left < length) {
                    length = right - left;
                    ans = s.substring(left, right);
                }

                char c2 = s.charAt(left);
                if(strMap.containsKey(c2)) {
                    winMap.put(c2, winMap.get(c2) - 1);
                    if(winMap.get(c2) == (strMap.get(c2) - 1)) match --;    //匹配数减1
                }
                left ++;
            }

        }

        return ans;
    }
}
