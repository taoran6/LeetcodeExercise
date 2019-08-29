package com.example.leetcode.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 找到字符串中所有字母异位词
 */
public class FindAnagrams {
    /**
     * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
     *
     * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
     *
     * 说明：
     *
     * 字母异位词指字母相同，但排列不同的字符串。
     * 不考虑答案输出的顺序。
     * 示例 1:
     *
     * 输入:
     * s: "cbaebabacd" p: "abc"
     *
     * 输出:
     * [0, 6]
     *
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
     *  示例 2:
     *
     * 输入:
     * s: "abab" p: "ab"
     *
     * 输出:
     * [0, 1, 2]
     *
     * 解释:
     * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
     * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
     * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *
     * 这种方法超时了呜呜呜呜呜呜
     */
    //这种方法超时了呜呜呜呜呜呜
    //Integer 在大于127 的时候 不从常量池里拿，是个对象，所以换成equals
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ansList = new ArrayList<>();
        if (s == null || p == null || s.length() == 0 || p.length() == 0 || s.length() < p.length()) return ansList;

        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i< p.length(); i++) {
            char c = p.charAt(i);
            hashMap.put(c, hashMap.getOrDefault(c, 0) + 1);
        }

        for(int i = 0; i <= s.length() - p.length(); i++) {
            if(hashMap.containsKey(s.charAt(i))) {
                HashMap<Character, Integer> map2 = new HashMap<>(hashMap);
                boolean flag = true;
                for(int j = 0; j< p.length(); j++) {
                    char c = s.charAt(i + j);
                    if(!map2.containsKey(c)) {break;}

                    int num = map2.get(c);
                    if (num == 1) map2.remove(c);
                    else map2.put(c, num - 1);

                }
                if(map2.isEmpty()) ansList.add(i);
            }
        }

        return ansList;
    }

    /**
     * 方法二：滑动窗口思想
     */
    public List<Integer> findAnagrams2(String s, String p) {
        List<Integer> list = new ArrayList<>();
        if(p.isEmpty() ) return list;
        int left = 0;
        int right = 0;
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> needs = new HashMap<>();

        for(int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
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
                if ((right - left) == p.length()) {
                    list.add(left);
                }

                char c2 = s.charAt(left);
                if(needs.containsKey(c2)) {
                    window.put(c2, window.get(c2) - 1);
                    if(window.get(c2) < needs.get(c2)) match--;
                }
                left ++;
            }
        }

        return list;
    }

    /**
     * 方法三 遍历比较
     */
    public List<Integer> findAnagrams3(String s, String p) {
        List<Integer> result = new ArrayList<>();
        char [] sChar = s.toCharArray();
        char [] pChar = p.toCharArray();
        int [] curAToZ = new int[26];
        int [] aToZ = new int[26];
        for (char c : pChar) {
            aToZ[c - 'a']++;
        }
        for (int i = 0; i < sChar.length; i++) {
            if (i >= pChar.length) {
                curAToZ[sChar[i-pChar.length] - 'a']--;
            }
            curAToZ[sChar[i] - 'a']++;
            if (Arrays.equals(curAToZ, aToZ)) {
                result.add(i - pChar.length + 1);
            }
        }
        return result;
    }

}
