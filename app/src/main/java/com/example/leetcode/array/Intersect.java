package com.example.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 两个数组的交集
 */
public class Intersect {
    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     *
     * 示例 1:
     *
     * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出: [2,2]
     * 示例 2:
     *
     * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出: [4,9]
     * 说明：
     *
     * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
     * 我们可以不考虑输出结果的顺序。
     * 进阶:
     *
     * 如果给定的数组已经排好序呢？你将如何优化你的算法？
     * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
     * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *
     * 方法一：排序，对于已排好序的数组
     */
    public int[] intersect1(int[] nums1, int[] nums2) {
        List<Integer> anslist = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int p1 = 0; int p2 = 0;
        while (p1 < nums1.length && p2 < nums2.length) {
            if(nums1[p1] == nums2[p2]) {
                anslist.add(nums1[p1]);
                p1 ++; p2 ++;
            }else if(nums1[p1] < nums2[p2]) p1 ++;
            else p2 ++;
        }
        int[] ans = new int[anslist.size()];
        for(int i = 0 ; i < anslist.size(); i ++) {
            ans[i] = anslist.get(i);
        }
        return ans;
    }

    /**
     * 方法二：hash
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect2(int[] nums1, int[] nums2) {
        int l1 = nums1.length; int l2 = nums2.length;
        int[] tmp = new int[l1 > l2 ? l1 : l2];
        int index = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < l1; i ++) {
            map.put(nums1[i], map.getOrDefault(nums1[i], 0) + 1);
        }
        for (int i = 0; i < l2; i++) {
            if(map.isEmpty()) break;
            if(map.containsKey(nums2[i])) {
                tmp[index ++] = nums2[i];
                int count = map.get(nums2[i]);
                if(count == 1) {
                    map.remove(nums2[i]);
                }else {
                    map.put(nums2[i], count - 1);
                }
            }
        }

        int[] ans = new int[index];
        for(int i = 0; i < index; i ++) ans[i] = tmp[i];
        return ans;
    }



}
