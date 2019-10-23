package com.example.leetcode.array;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 存在重复元素
 */
public class Duplicate {
    /**
     * 给定一个整数数组，判断是否存在重复元素。
     *
     * 如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。
     *
     * 方法一： hash表
     * @param nums
     * @return
     */
    public boolean containsDuplicate1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
        }
        return false;
    }

    /**
     * 方法二： 排序
     */
    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) return true;
        }
        return false;
    }

    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     *
     * 示例 1:
     *
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     * 示例 2:
     *
     * 输入: s = "rat", t = "car"
     * 输出: false
     * 说明:
     * 你可以假设字符串只包含小写字母。
     *
     * 进阶:
     * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
     *
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-anagram
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：排序
     * 方法二：hash表
     * 方法三：只包含小写字母，使用长度为26的数组替代hash表
     * 三种方法都比较简单，就只写一种啦
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        //注意这里可以提前判断
        if(s.length() != t.length()) return false;

        char[] sa = s.toCharArray();
        char[] ta = t.toCharArray();
        Arrays.sort(sa);
        Arrays.sort(ta);
        return Arrays.equals(sa, ta);
    }

    /**
     * 存在重复元素ii
     *
     * 这题中文翻译不好，曲解了题目意思，直接用英文题干吧
     *
     * Given an array of integers and an integer k, find out whether there are two distinct indices
     * i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j
     * is at most k.
     *
     *
     * 示例 1:
     *
     * 输入: nums = [1,2,3,1], k = 3
     * 输出: true
     * 示例 2:
     *
     * 输入: nums = [1,0,1,1], k = 1
     * 输出: true
     * 示例 3:
     *
     * 输入: nums = [1,2,3,1,2,3], k = 2
     * 输出: false
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/contains-duplicate-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param k
     * @return
     *
     * 方法一：最朴素的方法，HashMap
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if(!map.containsKey(nums[i])) {
                map.put(nums[i], new ArrayList<>());
            }
            map.get(nums[i]).add(i);
        }

        for (List<Integer> list : map.values()) {
            for (int i = 1; i < list.size(); i++) {
                if(list.get(i) - list.get(i - 1) <= k)
                    return true;
            }
        }
        return false;
    }

    /**
     * 方法二：使用HashSet
     *
     * 维护一个哈希表，里面始终最多包含 k 个元素，当出现重复值时则说明在 k 距离内存在重复元素，相当于滑动窗口
     * 每次遍历一个元素则将其加入哈希表中，如果哈希表的大小大于 k，则移除最前面的数字
     *
     * 作者：guanpengchn
     * 链接：https://leetcode-cn.com/problems/contains-duplicate-ii/solution/hua-jie-suan-fa-219-cun-zai-zhong-fu-yuan-su-ii-by/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        if(k <=0) return false;

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if(!set.add(nums[i])) {
                return true;
            }
            if(set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

}
