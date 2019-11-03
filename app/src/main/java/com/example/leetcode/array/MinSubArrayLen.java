package com.example.leetcode.array;

/**
 * 长度最小的子数组
 */
public class MinSubArrayLen {
    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存
     * 在符合条件的连续子数组，返回 0。
     *
     * 示例: 
     *
     * 输入: s = 7, nums = [2,3,1,2,4,3]
     * 输出: 2
     * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
     * 进阶:
     *
     * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *
     *
     * 方法一：时间复杂度：O(n) 。每个指针移动都需要 O(n) 的时间。
     * 每个元素至多被访问两次，一次被右端点访问，一次被左端点访问。
     */
    public int minSubArrayLen(int s, int[] nums) {
        if(nums.length == 0) return 0;

        int left = 0;
        int right = 0;
        int sum = 0;

        while (right < nums.length && sum < s) {
            sum += nums[right ++];
        }
        right --;   //此时[left, right]闭区间上的和定大于等于s

        //整个数组之和小于s
        if (sum < s) return 0;

        while (sum - nums[left] >= s) {
            sum -= nums[left ++];
        }
        int minLen = right - left + 1;

        for(right = right + 1; right < nums.length; right++) {
            sum += nums[right];
            while (sum - nums[left] >= s) {
                sum -= nums[left ++];
            }
            minLen = Math.min(minLen, right - left + 1);    //[left, right]闭区间
        }
        return minLen;
    }

    /**
     * 方法二：前缀和 + 二分法
     * 根据题目要求 "请尝试 O(n log n) 时间复杂度的解法"
     * 时间复杂度O(n log n)。。个人感觉很奇怪，已经有O(n)了为啥还尝试O(n log n)，可能是考察二分法的思想吧？
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen2(int s, int[] nums) {
        int min = nums.length;
        if (min == 0) return 0;
        min++;
        int[] sums = new int[nums.length + 1];
        sums[0] = 0;

        //先求前缀和
        for (int i = 1; i <= nums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        //根据前缀和以及s寻找
        for (int i = 1; i <= nums.length; i++) {
            int toFind = s + sums[i - 1];
            int bound = binarySearch(sums, toFind);
            if (bound > 0) min = Math.min(min, bound - i + 1);
        }
        return min > nums.length ? 0 : min;
    }
    private int binarySearch(int[] arr, int key) {
        int i = 0;
        int j = arr.length - 1;
        while (i < j - 1) {
            int mid = i + (j - i)/2;
            if (arr[mid] < key){
                i = mid;
            }else if (arr[mid] > key) {
                j = mid;
            }else {
                return mid;
            }
        }
        if (arr[j] >= key) return j;
        else return -1;
    }
}
