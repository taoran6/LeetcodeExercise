package com.example.leetcode.hash;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 290. 单词规律
 */
public class WordPattern {
    /**
     * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
     *
     * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
     *
     * 示例1:
     *
     * 输入: pattern = "abba", str = "dog cat cat dog"
     * 输出: true
     * 示例 2:
     *
     * 输入:pattern = "abba", str = "dog cat cat fish"
     * 输出: false
     * 示例 3:
     *
     * 输入: pattern = "aaaa", str = "dog cat cat dog"
     * 输出: false
     * 示例 4:
     *
     * 输入: pattern = "abba", str = "dog dog dog dog"
     * 输出: false
     * 说明:
     * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。    
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/word-pattern
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean wordPattern(String pattern, String str) {
        HashMap<Character, String> hashMap = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        String[] words = str.split(" ");
        char[] chars = pattern.toCharArray();
        if(words.length != chars.length) return false;

        for (int i = 0;  i< chars.length; i++) {
            if (hashMap.containsKey(chars[i])) {
                if(! hashMap.get(chars[i]).equals(words[i])) return false;
            } else {
                if(set.contains(words[i])) return false;
                hashMap.put(chars[i], words[i]);
                set.add(words[i]);
            }
        }
        return true;
    }

    /**
     * 383. 赎金信
     *
     * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串ransom能不能由第二个字符串
     * magazines里面的字符构成。如果可以构成，返回 true ；否则返回 false。
     *
     * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。)
     *
     * 注意：
     *
     * 你可以假设两个字符串均只含有小写字母。
     *
     * canConstruct("a", "b") -> false
     * canConstruct("aa", "ab") -> false
     * canConstruct("aa", "aab") -> true
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/ransom-note
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] aChars = new int[26];
        int[] bChars = new int[26];
        for (char c : ransomNote.toCharArray()) {
            aChars[c - 'a'] ++;
        }
        for (char c : magazine.toCharArray()) {
            bChars[c - 'a'] ++;
        }

        for (int i = 0; i < 26; i++) {
            if(aChars[i] > bChars[i]) return false;
        }
        return true;
    }
}
