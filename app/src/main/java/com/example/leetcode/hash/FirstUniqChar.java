package com.example.leetcode.hash;

/**
 * 字符串中的第一个唯一字符
 */
public class FirstUniqChar {
    /**
     * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
     *
     * 案例:
     *
     * s = "leetcode"
     * 返回 0.
     *
     * s = "loveleetcode",
     * 返回 2.
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/first-unique-character-in-a-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：hash表的对于字母的优化版 执行时间12ms
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        char[] array = s.toCharArray();
        int[] a2z = new int[26];
        for (int i = 0; i < array.length; i ++) {
            a2z[array[i] - 'a'] ++;
        }

        for (int i = 0; i < array.length; i ++) {
            if(a2z[array[i] - 'a'] == 1) return i;
        }
        return -1;
    }

    /**
     * 方法二 使用String 的方法,执行用时37ms
     * @param s
     * @return
     */
    public int firstUniqChar2(String s) {
        if (s.length() == 0) return -1;

        for (int i = 0; i < s.length(); i++) {
            if (s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i))) {
                return i;
            }
        }
        return -1;
    }
}
