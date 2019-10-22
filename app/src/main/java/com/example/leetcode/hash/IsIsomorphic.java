package com.example.leetcode.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 同构字符串
 */
public class IsIsomorphic {
    /**
     * 方法一：hash表
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        if(s.length() != t.length()) return false;

        // 存储 t 到 s 的映射
        Map<Character, Character> map = new HashMap<>();
        //存储已经映射过的 s 的字符
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < t.length(); i++) {
            if(!map.containsKey(t.charAt(i))) {
                if(set.contains(s.charAt(i))) return false; // t中的两个字符同时映射了s中的一个字符
                else {
                    map.put(t.charAt(i), s.charAt(i));
                    set.add(s.charAt(i));
                }
            }else
                if(map.get(t.charAt(i)) != s.charAt(i)) return false; //s中的两个字符同时映射了t中的一个字符
        }

        return true;
    }

    /**
     * 方法二：字符串中，同一个位置的字符在本串中第一次出现的位置相同。我简单解释一下，本题判定为false有这些个
     * 情况，我们假设拿s串和t串作对比
     *
     * 1.s串中相同的字符，对应的t串中的字符并不相等
     * 2.s串中不同的字符，对应的t串中的字符却是相等的
     *
     * 所以判断的关键点就是相同的字符要对应相同的字符，那么相同字符处于后位置的字符的第一次出现的位置就应该相同。
     * 所以我们在判断时，只需要判断两个字符串同位置的字符是否相同即可。
     *
     * 作者：hao-fei-hao
     * 链接：https://leetcode-cn.com/problems/isomorphic-strings/solution/javake-neng-bi-jiao-jian-dan-de-xie-fa-by-hao-fei-/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean isIsomorphic2(String s, String t) {
        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if(s.indexOf(ch1[i]) != t.indexOf(ch2[i])){
                return false;
            }
        }
        return true;
    }
}
