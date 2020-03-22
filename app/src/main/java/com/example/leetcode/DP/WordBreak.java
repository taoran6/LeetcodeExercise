package com.example.leetcode.DP;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 139. 单词拆分
 */
public class WordBreak {
    /**
     * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典
     * 中出现的单词。
     *
     * 说明：
     *
     * 拆分时可以重复使用字典中的单词。
     * 你可以假设字典中没有重复的单词。
     * 示例 1：
     *
     * 输入: s = "leetcode", wordDict = ["leet", "code"]
     * 输出: true
     * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
     * 示例 2：
     *
     * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
     * 输出: true
     * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
     *      注意你可以重复使用字典中的单词。
     * 示例 3：
     *
     * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
     * 输出: false
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/word-break
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：动态规划
     * 时间复杂度O(n2)
     * 空间复杂度O(n)
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s.length() == 0) return true;

        //isBreak[i]存储的是字符串的前i个字符是否可以被单词划分
        boolean[] isBreak = new boolean[s.length() + 1];
        //这个很重要
        isBreak[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            for (String word : wordDict) {
                int pre = i - word.length();
                if(pre >=0 && s.substring(pre,i).equals(word)) {
                    if(isBreak[pre]) {
                        isBreak[i] = true;
                        //isBreak[i]已经为true了，提前结束
                        break;
                    }
                }
            }
        }

        return isBreak[s.length()];
    }

    /**
     * 方法二：LeetCode题解的动态规划
     *
     * 其实思想都是差不多的,优化就是用了一个HashSet存储字典，不过new一个HashSet还是比较耗时的
     */
    public boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if(dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    /**
     * 使用栈，深度优先遍历，这个竟然超时了，看了一下题解，还是蛮容易超时的
     * 超时的测试用例
     * "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"
     * ["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
     *
     * 时间复杂度：O(n^n)。考虑最坏情况 s = aaaaaaa 。每一个前缀都在字典中，此时回溯树的复杂度会达到 n^n
     *
     * 空间复杂度：O(n) 。回溯树的深度最深达到 n 。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/word-break/solution/dan-ci-chai-fen-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * 个人觉得动态规划的方法适合字典很小而字符串很大的情况
     * 而这个适合字典很大而字符串较小的情况
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak3(String s, List<String> wordDict) {
       int sLength = s.length();
       if(sLength == 0) return true;

       Stack<Integer> wordStack = new Stack<>();
       Stack<Integer> stringStack = new Stack<>();
       wordStack.push(0);
       stringStack.push(0);

       while (!wordStack.isEmpty()) {
           int wordIndex = wordStack.pop();
           int stringIndex = stringStack.pop();

           for (int i = wordIndex; i < wordDict.size(); i++) {
               int nextStringIndex = wordDict.get(i).length() + stringIndex;
               if(nextStringIndex <= sLength &&
                       s.substring(stringIndex, nextStringIndex).equals(wordDict.get(i))) {
                   if(sLength == nextStringIndex) return true;
                   else {
                       wordStack.push(i + 1);
                       wordStack.push(0);
                       stringStack.push(stringIndex);
                       stringStack.push(nextStringIndex);
                   }
               }
           }
       }

       return false;
    }


    /**
     * 1160. 拼写单词
     *
     * 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
     *
     * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了
     * 这个单词。
     *
     * 注意：每次拼写时，chars 中的每个字母都只能用一次。
     *
     * 返回词汇表 words 中你掌握的所有单词的 长度之和。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：words = ["cat","bt","hat","tree"], chars = "atach"
     * 输出：6
     * 解释：
     * 可以形成字符串 "cat" 和 "hat"，所以答案是 3 + 3 = 6。
     * 示例 2：
     *
     * 输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
     * 输出：10
     * 解释：
     * 可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
     *  
     *
     * 提示：
     *
     * 1 <= words.length <= 1000
     * 1 <= words[i].length, chars.length <= 100
     * 所有字符串中都仅包含小写英文字母
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-words-that-can-be-formed-by-characters
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int countCharacters(String[] words, String chars) {
        int[] charArray = new int[26];
        for (char c : chars.toCharArray()) {
            charArray[c - 'a'] ++;
        }

        int ans = 0;
        for (String word : words) {
            int[] copy = Arrays.copyOf(charArray, charArray.length);
            boolean flag = false;
            for (char c : word.toCharArray()) {
                if(copy[c - 'a'] == 0) {
                    flag = true;
                    break;
                }else {
                    copy[c - 'a'] --;
                }
            }
            if(!flag) ans += word.length();
        }
        return ans;
    }
}
