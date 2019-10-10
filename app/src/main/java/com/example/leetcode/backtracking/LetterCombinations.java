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

}
