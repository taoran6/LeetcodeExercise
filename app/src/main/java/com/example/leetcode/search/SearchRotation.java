package com.example.leetcode.search;

/**
 * 搜索旋转排序数组
 */
public class SearchRotation {
    /**
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     *
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     *
     * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     *
     * 你可以假设数组中不存在重复的元素。
     *
     * 你的算法时间复杂度必须是 O(log n) 级别。
     *
     * 示例 1:
     *
     * 输入: nums = [4,5,6,7,0,1,2], target = 0
     * 输出: 4
     * 示例 2:
     *
     * 输入: nums = [4,5,6,7,0,1,2], target = 3
     * 输出: -1
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param target
     * @return
     *
     * 核心为二分查找
     */
    public int search(int[] nums, int target) {
        if(nums == null) return -1;

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if(nums[mid] == target) return mid;
            if(nums[start] <= nums[mid]) {      //左半部分已排好序
                if(target >= nums[start] && target < nums[mid]) {
                    //找排序的左半部分
                    end = mid - 1;
                } else {
                    //找剩下的部分啦
                    start = mid + 1;
                }
            } else {    //右半部分已排好序
                if(target > nums[mid] && target <= nums[end]) {
                    //找排序的右半部分
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 153. 寻找旋转排序数组中的最小值
     *
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     *
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     *
     * 请找出其中最小的元素。
     *
     * 你可以假设数组中不存在重复元素。
     *
     * 示例 1:
     *
     * 输入: [3,4,5,1,2]
     * 输出: 1
     * 示例 2:
     *
     * 输入: [4,5,6,7,0,1,2]
     * 输出: 0
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int findMin(int[] nums) {
        if(nums.length == 1) return nums[0];
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] > nums[mid + 1]) return nums[mid + 1];
            else if(nums[mid] < nums[nums.length - 1]) {
                end = mid;
            } else {
                start = mid;
            }
        }

        return nums[start];
    }
}