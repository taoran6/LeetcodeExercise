package com.example.leetcode.string;

public class BoldWords {
    /**
     * 758. 字符串中的加粗单词
     *
     * 给定一个关键词集合 words 和一个字符串 S，将所有 S 中出现的关键词加粗。所有在标签 <b> 和 </b> 中的字母
     * 都会加粗。
     *
     * 返回的字符串需要使用尽可能少的标签，当然标签应形成有效的组合。
     *
     * 例如，给定 words = ["ab", "bc"] 和 S = "aabcd"，需要返回 "a<b>abc</b>d"。注意返回
     *  "a<b>a<b>b</b>c</b>d" 会使用更多的标签，因此是错误的。
     *
     *  
     *
     * 注：
     *
     * words 长度的范围为 [0, 50]。
     * words[i] 长度的范围为 [1, 10]。
     * S 长度的范围为 [0, 500]。
     * 所有 words[i] 和 S 中的字符都为小写字母。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/bold-words-in-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public String boldWords(String[] words, String S) {
        if(S == null || S.length() == 0 || words == null || words.length == 0) return S;

        // 首先记录要加粗的字母位置
        boolean[] flag = new boolean[S.length()];
        for(String word : words) {
            int index = S.indexOf(word);
            //注意这里是循环找出所有单词
            while (index != -1) {
                for (int i = 0; i < word.length(); i++) {
                    flag[i + index] = true;
                }
                //其实这里题目没有说清楚，重复的字母到底怎么算
                //["e","cab","de","cc","db"]  "cbccceeead"
                // 输出"cb<b>ccceee</b>ad"，这里ccc重复计算了一次
                index = S.indexOf(word,index + 1);
            }
        }

        //接下来根据边界条件插入加粗符号
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < S.length(); i++) {
            if(i > 0 && flag[i-1] == false && flag[i] == true) {
                builder.append("<b>");
            }
            builder.append(S.charAt(i));
            if(i < S.length() - 1 && flag[i] == true && flag[i+1] == false) {
                builder.append("</b>");
            }
        }
        //首尾单独考虑
        if(flag[0]) builder.insert(0, "<b>");
        if(flag[S.length() - 1]) builder.append("</b>");

        return builder.toString();
    }
}
