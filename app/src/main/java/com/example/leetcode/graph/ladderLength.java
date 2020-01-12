package com.example.leetcode.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 127. 单词接龙
 */
public class ladderLength {
    /**
     * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转
     * 换需遵循如下规则：
     * <p>
     * 每次转换只能改变一个字母。
     * 转换过程中的中间单词必须是字典中的单词。
     * 说明:
     * <p>
     * 如果不存在这样的转换序列，返回 0。
     * 所有单词具有相同的长度。
     * 所有单词只由小写字母组成。
     * 字典中不存在重复的单词。
     * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
     * 示例 1:
     * <p>
     * 输入:
     * beginWord = "hit",
     * endWord = "cog",
     * wordList = ["hot","dot","dog","lot","log","cog"]
     * <p>
     * 输出: 5
     * <p>
     * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     * 返回它的长度 5。
     * 示例 2:
     * <p>
     * 输入:
     * beginWord = "hit"
     * endWord = "cog"
     * wordList = ["hot","dot","dog","lot","log"]
     * <p>
     * 输出: 0
     * <p>
     * 解释: endWord "cog" 不在字典中，所以无法进行转换。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/word-ladder
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * emmmm这题代码还是有点繁琐的
     *
     *
     * 方法一：直接BFS
     * <p>
     * 用时1048ms
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>();
        int ans = 2;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String str = queue.poll();
                for (String word : wordList) {
                    if (!set.contains(word) && isConvert(str, word)) {
                        if (word.equals(endWord)) return ans;
                        set.add(word);
                        queue.offer(word);
                    }
                }
            }
            ans++;
        }
        return 0;
    }

    private boolean isConvert(String start, String end) {
        char[] sArray = start.toCharArray();
        char[] eArray = end.toCharArray();
        boolean flag = false;
        for (int i = 0; i < sArray.length; i++) {
            if (sArray[i] != eArray[i]) {
                if (flag) return false;
                flag = true;
            }
        }
        return true;
    }

    /**
     * 方法二：优化 存储所有的邻接单词 113ms
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        //获取所有的邻接状态
        Map<String, Set<String>> patternMap = buildMap(wordList);
        Set<String> visited = new HashSet<>();
        int ans = 2;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String str = queue.poll();
                for(int j = 0; j < str.length(); j++) {
                    //直接从存储的邻接单词中获取
                    Set<String> adjWord = patternMap.get(
                            str.substring(0, j) + "*" + str.substring(j+1));
                    //这里要判空处理
                    if(adjWord == null) continue;
                    for(String word : adjWord) {
                        if(word.equals(endWord)) return ans;
                        if(!visited.contains(word)) {
                            queue.offer(word);
                            visited.add(word);
                        }
                    }
                }
            }
            ans++;
        }
        return 0;

    }

    private Map<String, Set<String>> buildMap(List<String> wordList) {
        //key是通用状态; value是拥有该通用状态的词
        Map<String, Set<String>> map = new HashMap<>();
        for (int i = 0; i < wordList.size(); i++) {
            String word = wordList.get(i);
            for (int j = 0; j < word.length(); j++) {
                String key = word.substring(0, j) + "*" + word.substring(j + 1);
                if(!map.containsKey(key)) map.put(key, new HashSet<>());
                //关键是这里
                map.get(key).add(word);
            }
        }
        return map;
    }

    /**
     * 方法三：再优化，使用双向BFS 72ms
     */
    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        if(!wordList.contains(endWord))
            return 0;

        Map<String, Set<String>> patternMap = buildMap(wordList);
        Set<String> beginSet = new HashSet<>(); beginSet.add(beginWord);
        Set<String> endSet = new HashSet<>(); endSet.add(endWord);
        //记录访问过的节点。这里两端搜索只需要一个visited就可以了，可以理解为路径最短
        Set<String> visited = new HashSet<>();
        visited.add(beginWord); visited.add(endWord);
        int ans = 2;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            //核心:控制当前循环从哪个方向进行bfs; 让begin指向size更小的集合, 这样就不会一直从一个方向bfs了
            if(beginSet.size() < endSet.size()) {
                Set<String> tmp = beginSet;
                beginSet = endSet;
                endSet = tmp;
            }

            Set<String> nextSet = new HashSet<>();
            for (String str : beginSet) {
                for(int j = 0; j < str.length(); j++) {
                    //直接从存储的邻接单词中获取
                    Set<String> adjWord = patternMap.get(
                            str.substring(0, j) + "*" + str.substring(j+1));
                    //这里要判空处理
                    if(adjWord == null) continue;
                    for(String word : adjWord) {
                        //提前结束条件需要变一下
                        if(endSet.contains(word)) return ans;
                        if(!visited.contains(word)) {
                            nextSet.add(word);
                            visited.add(word);
                        }
                    }
                }
            }

            beginSet = nextSet;
            ans ++;
        }

        return 0;
    }
}
