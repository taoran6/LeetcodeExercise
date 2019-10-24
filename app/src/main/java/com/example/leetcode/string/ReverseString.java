package com.example.leetcode.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 反转字符串
 */
public class ReverseString {
    /**
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
     *
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     *
     * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：["h","e","l","l","o"]
     * 输出：["o","l","l","e","h"]
     * 示例 2：
     *
     * 输入：["H","a","n","n","a","h"]
     * 输出：["h","a","n","n","a","H"]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     */
    public void reverseString(char[] s) {
        if(s == null) return;
        int start = 0;
        int end = s.length - 1;

        while (start < end) {
            char tmp = s[start];
            s[start ++] = s[end];
            s[end --] = tmp;
        }
    }

    /**
     * 反转字符串中的单词 III
     *
     * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
     *
     * 示例 1:
     *
     * 输入: "Let's take LeetCode contest"
     * 输出: "s'teL ekat edoCteeL tsetnoc" 
     * 注意：在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string-iii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public String reverseWords3(String s) {
        s = s.trim();
        String[] strings = s.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String s1 : strings) {
            builder.append(new StringBuilder(s1).reverse()).append(" ");
        }
        return builder.toString().trim();
    }

    /**
     * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
     *
     * 示例 1:
     *
     * 输入: "hello"
     * 输出: "holle"
     * 示例 2:
     *
     * 输入: "leetcode"
     * 输出: "leotcede"
     * 说明:
     * 元音字母不包含字母"y"。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-vowels-of-a-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public String reverseVowels(String s) {
        if(s.length() <= 1) return s;

        char[] chars = s.toCharArray();

        Set<Character> vowels = new HashSet<Character>(){
            {add('a'); add('e');add('i');add('o');add('u');
                add('A'); add('E');add('I');add('O');add('U');}};

        int left = 0;
        int right = chars.length - 1;
        while (left < right && ! vowels.contains(chars[left])) left ++;
        while (left < right && ! vowels.contains(chars[right])) right --;
        while (left < right) {
            char tmp = chars[left];
            chars[left] = chars[right];
            chars[right] = tmp;
            left ++; right --;
            while (left < right && ! vowels.contains(chars[left])) left ++;
            while (left < right && ! vowels.contains(chars[right])) right --;
        }

        return String.valueOf(chars);
    }
}
