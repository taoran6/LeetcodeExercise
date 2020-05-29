package com.example.leetcode.dualpointer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 两数之和
 */
public class TwoSum {
    /**
     * 167. 两数之和 II - 输入有序数组
     *
     * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
     *
     * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
     *
     * 说明:
     *
     * 返回的下标值（index1 和 index2）不是从零开始的。
     * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
     * 示例:
     *
     * 输入: numbers = [2, 7, 11, 15], target = 9
     * 输出: [1,2]
     * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：二分搜索
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSumII(int[] numbers, int target) {

        for (int index1 = 0; numbers[index1] * 2 <= target; index1 ++) {
            int index2 = findNum(numbers, target - numbers[index1], index1 + 1, numbers.length);
            if(index2 != -1)
                return new int[]{index1 + 1, index2  +1};
        }
        return new int[2];
    }

    /**
     * 二分查找
     */
    private int findNum(int[] numbers, int target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (numbers[mid] == target) return mid;
            else if (numbers[mid] > target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 方法二：双指针
     *
     * 我们使用两个指针，初始分别位于第一个元素和最后一个元素位置，比较这两个元素之和与目标值的大小。如果和等于
     * 目标值，我们发现了这个唯一解。如果比目标值小，我们将较小元素指针增加一。如果比目标值大，我们将较大指针减
     * 小一。移动指针后重复上述比较直到找到答案。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/solution/liang-shu-zhi-he-ii-shu-ru-you-xu-shu-zu-by-leetco/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSumII2(int[] numbers, int target) {
        int start = 0;
        int end = numbers.length - 1;
        while (start < end) {
            int sum = numbers[start] + numbers[end];
            if(sum == target) return new int[]{start + 1, end + 1};
            else if(sum > target) end--;
            else start ++;
        }
        return new int[2];
    }

    /**
     * 1. 两数之和
     *
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     *
     *  
     *
     * 示例:
     *
     * 给定 nums = [2, 7, 11, 15], target = 9
     *
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法：使用Hash表
     * 时间复杂度和空间复杂度都是O(n)
     */
    public int[] twoSumI(int[] nums, int target) {
        //key是具体的数字，value存储的是下标
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            int other = target - nums[i];
            if(map.containsKey(other)) {
                return new int[]{ map.get(other), i};
            } else {
                //这里就算有重复的数字也没有关系，因为如果两个重复的数相加满足target的话在上面就会提前返回。
                // 如果答案不是两个重复的数相加，那返回任意一个下标都无所谓
                map.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 170. 两数之和 III - 数据结构设计
     *
     * 设计并实现一个 TwoSum 的类，使该类需要支持 add 和 find 的操作。
     *
     * add 操作 -  对内部数据结构增加一个数。
     * find 操作 - 寻找内部数据结构中是否存在一对整数，使得两数之和与给定的数相等。
     *
     * 示例 1:
     *
     * add(1); add(3); add(5);
     * find(4) -> true
     * find(7) -> false
     * 示例 2:
     *
     * add(3); add(1); add(2);
     * find(3) -> true
     * find(6) -> false
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum-iii-data-structure-design
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 这里由于find()可能需要很多次，所以选择add的时间复杂度是O(n)，find的时间复杂度是O(1)
     */
    class TwoSumIII {

        //存储所有的和
        Set<Integer> sumSet;
        //存储所有add的数字
        Set<Integer> numSet;

        /** Initialize your data structure here. */
        public TwoSumIII() {
            sumSet = new HashSet<>();
            numSet = new HashSet<>();
        }

        /** Add the number to an internal data structure.. */
        public void add(int number) {
            for (int num : numSet) {
                //跟twoSumI一样，重复数字在这里可以被计算添加
                sumSet.add(num + number);
            }
            numSet.add(number);
        }

        /** Find if there exists any pair of numbers which sum is equal to the value. */
        public boolean find(int value) {
            return sumSet.contains(value);
        }
    }

    /**
     * 如果find较少而add较多
     *
     * 选择add的时间复杂度是O(1)，find的时间复杂度是O(n)
     */
    class TwoSumIII2 {

        //存储所有add的数字及出现次数
        Map<Integer, Integer> numMap;

        /** Initialize your data structure here. */
        public TwoSumIII2() {
            numMap = new HashMap<>();
        }

        /** Add the number to an internal data structure.. */
        public void add(int number) {
            numMap.put(number, numMap.getOrDefault(number, 0) + 1);
        }

        /** Find if there exists any pair of numbers which sum is equal to the value. */
        public boolean find(int value) {
            for(int key : numMap.keySet()) {
                int other = value - key;
                if(other == key && numMap.get(key) > 1) {
                    return true;
                } else if(other != key && numMap.containsKey(other)) {
                    return true;
                }
            }
            return false;
        }
    }
}
