package com.example.leetcode.hash;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *  键盘行
 */
public class FindWords {
    /**
     * 给定一个单词列表，只返回可以使用在键盘同一行的字母打印出来的单词。
     *
     * 示例：
     *
     * 输入: ["Hello", "Alaska", "Dad", "Peace"]
     * 输出: ["Alaska", "Dad"]
     *  
     *
     * 注意：
     *
     * 你可以重复使用键盘上同一字符。
     * 你可以假设输入的字符串将只包含字母。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/keyboard-row
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 这题看上去很简单但是步骤还是有点繁琐的。
     *
     * 方法一：hash表保存每一行
     */
    public String[] findWords(String[] words) {
        List<String> ans = new ArrayList<>();

        String[] strings = new String[] {
                "qwertyuiop",
                "asdfghjkl",
                "zxcvbnm"
        };
        Set<Character>[] sets = new HashSet[]{new HashSet<Character>(),
                new HashSet<Character>(), new HashSet<Character>()};

        for(int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length(); j++) {
                char c = strings[i].charAt(j);
                sets[i].add(c);
                sets[i].add((char)(c + 'A' - 'a'));
            }
        }

        for (int i = 0; i < words.length; i ++) {
            if(words[i].length() == 0) continue;

            int setNum;     //第一个字母所在的行数
            char char0 = words[i].charAt(0);
            if(sets[0].contains(char0)) setNum = 0;
            else if(sets[1].contains(char0)) setNum = 1;
            else setNum = 2;

            boolean flag = false;
            for(int j = 1; j < words[i].length(); j++) {
                char c = words[i].charAt(j);
                if(! sets[setNum].contains(c)) {
                    flag = true;
                    break;
                }
            }
            if(!flag) ans.add(words[i]);
        }

        String[] ansArray = new String[ans.size()];
        for(int i = 0; i < ans.size(); i++) {
            ansArray[i] = ans.get(i);
        }
        return ansArray;
    }

    /**
     * 方法二：使用数组保存每一个字母所在行数
     */
    public String[] findWords2(String[] words) {
        if (words == null || words.length == 0) return new String[0];
        int[] map = {2, 3, 3, 2, 1, 2, 2, 2, 1, 2, 2, 2, 3, 3, 1, 1, 1, 1, 2, 1, 1, 3, 1, 3, 1, 3};
        List<String> list = new ArrayList<String>();
        for (String word : words) {
            String tempWord = word.toUpperCase();
            int temp = map[tempWord.charAt(0) - 65];
            boolean flag = true;
            //通过与首字母比较行号确定是否在同一行
            for (int i = 1; i < tempWord.length(); i++) {
                if (temp != map[tempWord.charAt(i) - 65]) {
                    flag = false;
                    break;
                }
            }
            if (flag) list.add(word);
        }

        //List转Array这么写
        return list.toArray(new String[list.size()]);
    }
}
