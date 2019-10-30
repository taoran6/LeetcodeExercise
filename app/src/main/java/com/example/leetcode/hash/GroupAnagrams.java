package com.example.leetcode.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字母异位词分组
 */
public class GroupAnagrams {
    /**
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     * <p>
     * 示例:
     * <p>
     * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
     * 输出:
     * [
     * ["ate","eat","tea"],
     * ["nat","tan"],
     * ["bat"]
     * ]
     * 说明：
     * <p>
     * 所有输入均为小写字母。
     * 不考虑答案输出的顺序。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/group-anagrams
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param strs
     * @return
     *
     * 方法一：使用两个列表，对比
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        List<int[]> charsList = new ArrayList<>();
        List<List<String>> ans = new ArrayList<>();

        for (int i = 0; i < strs.length; i++) {
            int[] chars = new int[26];  //存储26个字母出现的次数
            for (char c : strs[i].toCharArray()) {
                chars[c - 'a']++;
            }

            boolean isContains = false;
            for (int j = 0; j < charsList.size(); j++) {
                if (Arrays.equals(charsList.get(j), chars)) {    //迭代比较数组是否相等，这里比较费时，优化可以看方法三
                    ans.get(j).add(strs[i]);
                    isContains = true;
                    break;
                }
            }

            if (!isContains) {
                charsList.add(chars);
                List<String> newList = new ArrayList<>();
                newList.add(strs[i]);
                ans.add(newList);
            }
        }

        return ans;
    }

    /**
     * 方法二：排序数组分类
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            //排序
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String sortStr = String.valueOf(chars);

            if(! map.containsKey(sortStr)) {
                map.put(sortStr, new ArrayList<>());
            }
            map.get(sortStr).add(strs[i]);
        }

        return new ArrayList<>(map.values());
    }

    /**
     * 方法三：方法一中的比较数组相等可以转化为将数组内容转化为字符串，然后将其作为HashMap的key值
     *
     * 可以看做是两种方法的结合哈哈哈
     */
    public List<List<String>> groupAnagrams3(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            int[] charsCount = new int[26];  //存储26个字母出现的次数
            for (char c : strs[i].toCharArray()) {
                charsCount[c - 'a']++;
            }

            //数组内容转化为字符串
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < charsCount.length; j++) {
                builder.append(charsCount[j]);
            }
            String key = builder.toString();

            if(! map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(strs[i]);
        }

        return new ArrayList<>(map.values());
    }

    /**
     * 方法四：算术基本定理，又称为正整数的唯一分解定理，即：每个大于1的自然数，要么本身就是质数，要么可以写为2个
     * 以上的质数的积，而且这些质因子按大小排列之后，写法仅有一种方式。
     *
     * 作者：windliang
     * 链接：https://leetcode-cn.com/problems/group-anagrams/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--16/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * 不过这种方法可能会导致溢出
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<Integer, List<String>> hash = new HashMap<>();
        //每个字母对应一个质数
        int[] prime = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73,
                79, 83, 89, 97, 101, 103 };
        for (int i = 0; i < strs.length; i++) {
            int key = 1;
            //累乘得到 key
            for (int j = 0; j < strs[i].length(); j++) {
                key *= prime[strs[i].charAt(j) - 'a'];
            }
            if (hash.containsKey(key)) {
                hash.get(key).add(strs[i]);
            } else {
                List<String> temp = new ArrayList<String>();
                temp.add(strs[i]);
                hash.put(key, temp);
            }

        }
        return new ArrayList<List<String>>(hash.values());
    }


}
