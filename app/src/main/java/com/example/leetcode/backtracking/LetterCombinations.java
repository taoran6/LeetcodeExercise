package com.example.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 电话号码的字母组合
 */
public class LetterCombinations {
    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     *
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     * 示例:
     *
     * 输入："23"
     * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
     * 说明:
     * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if(digits.length() == 0) return ans;

        ans.add("");
        char[][] letter = {{'a', 'b', 'c', 0}, {'d', 'e', 'f', 0}, {'g', 'h', 'i', 0},
                {'j', 'k', 'l', 0}, {'m', 'n', 'o', 0}, {'p', 'q', 'r', 's'}, {'t', 'u', 'v', 0},
                {'w', 'x', 'y', 'z'}};

        for(char c : digits.toCharArray()) {
            int size = ans.size();
            int index = c - '2';
            for (int i = 0; i < size; i++) {
                String pre = ans.get(0);
                for(int j = 0; j < 4; j ++) {
                    if(letter[index][j] != 0) {
                        ans.add(pre + letter[index][j]);
                    }
                }
                ans.remove(0);
            }
        }

        return ans;
    }

    /**
     * 520. 检测大写字母
     *
     * 给定一个单词，你需要判断单词的大写使用是否正确。
     *
     * 我们定义，在以下情况时，单词的大写用法是正确的：
     *
     * 全部字母都是大写，比如"USA"。
     * 单词中所有字母都不是大写，比如"leetcode"。
     * 如果单词不只含有一个字母，只有首字母大写， 比如 "Google"。
     * 否则，我们定义这个单词没有正确使用大写字母。
     *
     * 示例 1:
     *
     * 输入: "USA"
     * 输出: True
     * 示例 2:
     *
     * 输入: "FlaG"
     * 输出: False
     *
     * 注意: 输入是由大写和小写拉丁字母组成的非空单词。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/detect-capital
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：正着遍历，在下面写到的就是方法一
     * 方法二：反着遍历
     * 正着遍历的话，需要确定前两个字母才可以确定该单词的组成，太麻烦。
     * 所以我们先看最后一个字母，如果最后一个字母是大写，那该单词如果正确，那所有字母都是大写。
     * 如果最后一个字母小写，那除了第一个字母无所谓之外，其余所有字母必须小写。
     *
     * 作者：hao-fei-hao
     * 链接：https://leetcode-cn.com/problems/detect-capital/solution/java1ms100-by-hao-fei-hao/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean detectCapitalUse(String word) {
        //方法一
        if(word.length() < 2) return true;

        char[] words = word.toCharArray();
        boolean isUpAll = false;
        if(words[0] >= 'A' && words[0] <= 'Z' && words[1] >= 'A' && words[1] <= 'Z') {
            isUpAll = true;
        }

        //i从1开始，第一个字母大小写无所谓
        for (int i = 1; i < words.length; i++) {
            if(isUpAll) {
                if(!(words[i] >= 'A' && words[i] <= 'Z')) return false;
            } else if(!(words[i] >= 'a' && words[i] <= 'z')) return false;
        }

        return true;

    }

}
