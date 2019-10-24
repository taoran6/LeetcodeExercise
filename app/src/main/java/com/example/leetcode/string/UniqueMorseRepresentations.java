package com.example.leetcode.string;

import java.util.HashSet;
import java.util.Set;

/**
 * 唯一摩尔斯密码词
 */
public class UniqueMorseRepresentations {
    public int uniqueMorseRepresentations(String[] words) {
        String[] morse = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---",
                "-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--",
                "-..-","-.--","--.."};

        Set<String> set = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            StringBuilder builder = new StringBuilder();
            for (char c : words[i].toCharArray()) {
                builder.append(morse[c -'a']);
            }
            set.add(builder.toString());
        }
        return set.size();
    }
}
