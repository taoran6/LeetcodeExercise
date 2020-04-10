package com.example.leetcode.string;

/**
 * 最长公共前缀
 */
public class LongestCommonPrefix {
    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     *
     * 如果不存在公共前缀，返回空字符串 ""。
     *
     * 示例 1:
     *
     * 输入: ["flower","flow","flight"]
     * 输出: "fl"
     * 示例 2:
     *
     * 输入: ["dog","racecar","car"]
     * 输出: ""
     * 解释: 输入不存在公共前缀。
     * 说明:
     *
     * 所有输入只包含小写字母 a-z 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-common-prefix
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        //记得判空
        if(strs == null || strs.length == 0) return "";

        String out = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (!strs[i].startsWith(out)) {
                if (out.length() == 1) return "";
                out = out.substring(0, out.length() - 1);
            }
        }
        return out;
    }


}
