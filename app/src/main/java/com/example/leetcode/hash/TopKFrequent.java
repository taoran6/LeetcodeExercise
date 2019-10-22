package com.example.leetcode.hash;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *  前 K 个高频元素
 */
public class TopKFrequent {
    /**
     * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
     *
     * 示例 1:
     *
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     * 示例 2:
     *
     * 输入: nums = [1], k = 1
     * 输出: [1]
     * 说明：
     *
     * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
     * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param k
     * @return
     *
     * 方法一：大顶堆 用时18ms
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], hashMap.getOrDefault(nums[i], 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        for (Map.Entry entry : hashMap.entrySet()) {
            priorityQueue.offer(entry);
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ans.add(priorityQueue.poll().getKey());
        }
        return ans;
    }

    /**
     * 方法二：桶排序 执行时间16ms，稍微快一点
     *
     * 首先依旧使用哈希表统计频率，统计完成后，创建一个数组，将频率作为数组下标，对于出现频率不同的数字集合，存
     * 入对应的数组下标即可。
     */
    public List<Integer> topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> hashMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], hashMap.getOrDefault(nums[i], 0) + 1);
        }

        List[] buck = new List[nums.length + 1];    //!这里注意是nums.length + 1
        for (int key : hashMap.keySet()) {
            int frequent =  hashMap.get(key);
            if(buck[frequent] == null) {
                buck[frequent] = new ArrayList();
            }
            buck[frequent].add(key);
        }

        List<Integer> ans = new ArrayList<>();
        for(int i = buck.length - 1; i >= 0 && ans.size() < k; i --) {
            if(buck[i] != null) {
                //由于题目保证k总是合理的，不存在最后加完多于k个元素的情况，可直接加
                ans.addAll(buck[i]);
            }
        }
        return ans;
    }

}
