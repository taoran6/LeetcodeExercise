package com.example.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 最短无序连续子数组
 */
public class FindUnsortedSubarray {
    /**
     * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
     *
     * 你找到的子数组应是最短的，请输出它的长度。
     *
     * 示例 1:
     *
     * 输入: [2, 6, 4, 8, 10, 9, 15]
     * 输出: 5
     * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
     * 说明 :
     *
     * 输入的数组长度范围在 [1, 10,000]。
     * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 解法一最优解：从左到右循环，记录最大值为 max，若 nums[i] < max, 则表明位置 i 需要调整, 循环结束，记录需要调整的最大
     * 位置 i 为 maxp; 同理，从右到左循环，记录最小值为 min, 若 nums[i] > min, 则表明位置 i 需要调整，
     * 循环结束，记录需要调整的最小位置 i 为 minp.
     *
     * 解法二：先排序，然后对比两个数组
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        if(nums == null || nums.length == 0 || nums.length == 1) return 0;

        int max = nums[0];
        int maxp = -1;
        int min = nums[nums.length - 1];
        int minp = -1;
        for(int i = 1; i< nums.length; i++) {
            if (nums[i] < max) maxp = i;
            else max = nums[i];
        }
        if(maxp == -1) return 0;
        for (int i = nums.length -2; i>=0; i--){
            if(nums[i] > min) minp = i;
            else min = nums[i];
        }
        return maxp - minp + 1;
    }

    /**
     * 560. 和为K的子数组
     *
     * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
     *
     * 示例 1 :
     *
     * 输入:nums = [1,1,1], k = 2
     * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
     * 说明 :
     *
     * 数组的长度为 [1, 20,000]。
     * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：双层遍历O(n^2)的方法我就不写啦
     * 方法二：使用HashMap存储所有和的次数，时间复杂度O(n)
     *
     * 基于这些想法，可以使用了一个哈希表 map，它用于存储所有可能的索引的累积总和以及相同累加和发生的次数。我
     * 们以下形式存储数据：（sum，sum出现的次数）。我们遍历数组 nums 并继续寻找累积总和。每当我们遇到一个新的和
     * 时，我们在hashmap中创建一个与该总和相对应的新条目。如果再次出现相同的和，我们增加与map中的和相对应的计数。
     * 此外，对于遇到的每个总和，我们还确定已经发生 sum-k 总和的次数，因为它将确定具有总和 kk 的子阵列发生到当前
     * 索引的次数。我们将count 增加相同的量。
     *
     * 在完成便利数组后，countcount 记录了所需结果
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/subarray-sum-equals-k/solution/he-wei-kde-zi-shu-zu-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        //初始化（0，1）即和为0的sum出现了一次
        map.put(0,1);
        int ans = 0;
        int sum = 0;
        for(int i : nums) {
            sum += i;
            if(map.containsKey(sum - k)) {
                ans += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }
}
