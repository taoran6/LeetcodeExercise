package com.example.leetcode.hash;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 451. 根据字符出现频率排序
 */
public class FrequencySort {
    /**
     * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
     *
     * 示例 1:
     *
     * 输入:
     * "tree"
     *
     * 输出:
     * "eert"
     *
     * 解释:
     * 'e'出现两次，'r'和't'都只出现一次。
     * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
     * 示例 2:
     *
     * 输入:
     * "cccaaa"
     *
     * 输出:
     * "cccaaa"
     *
     * 解释:
     * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
     * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
     * 示例 3:
     *
     * 输入:
     * "Aabb"
     *
     * 输出:
     * "bbAa"
     *
     * 解释:
     * 此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
     * 注意'A'和'a'被认为是两种不同的字符。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sort-characters-by-frequency
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public String frequencySort(String s) {
        //统计频率
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        //使用PriorityQueue 堆排序
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(
                new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        queue.addAll(map.entrySet());

        //拼接字符串,String是final class，每次字符串拼接的时候就会创建一个新的字符串，当处理很多字符的时候内存
        // 就会超出，所以用StringBuilder拼接
        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()){
            int value = queue.peek().getValue();
            char key = queue.poll().getKey();
            for (int j = 0; j < value; j++) {
                builder.append(key);
            }
        }
        return builder.toString();
    }
}
