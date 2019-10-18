package com.example.leetcode.string;

/**
 * 实现 strStr()
 */
public class StrStr {
    /**
     * 实现 strStr() 函数。
     *
     * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     *
     * 示例 1:
     *
     * 输入: haystack = "hello", needle = "ll"
     * 输出: 2
     * 示例 2:
     *
     * 输入: haystack = "aaaaa", needle = "bba"
     * 输出: -1
     * 说明:
     *
     * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
     *
     * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/implement-strstr
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if(needle == null || needle.length() == 0)    return 0;
        if(haystack == null) return -1;
        if(haystack.length() < needle.length()) return -1;

        int index = 0;
        while (haystack.length() >= needle.length()){
            if(haystack.startsWith(needle)) {
                return index;
            } else {
                index ++;
                haystack = haystack.substring(1);
            }
        }
        return -1;
    }

    /**
     * 优化，使用substring
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr2(String haystack, String needle) {
        if(needle == null || needle.length() == 0)    return 0;
        if(haystack == null) return -1;
        if(haystack.length() < needle.length()) return -1;

        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (needle.charAt(0) == haystack.charAt(i)) {
                //if(haystack.substring(i).startsWith(needle)) return i; 下面这个更快
                if(haystack.substring(i, i + needle.length()).equals(needle)) return i;
            }
        }

        return -1;
    }

    /**
     * 转换成小写字母
     * @param str
     * @return
     */
    public String toLowerCase(String str) {
        StringBuilder builder = new StringBuilder(str);
        for(int i = 0; i < builder.length(); i++) {
            char c = builder.charAt(i);
            if(c >= 'A' && c <= 'Z') {
                builder.setCharAt(i, (char) (c + 'a' - 'A'));
            }
        }
        return builder.toString();
    }
}
