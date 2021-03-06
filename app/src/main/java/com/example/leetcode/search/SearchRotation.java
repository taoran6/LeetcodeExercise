package com.example.leetcode.search;

/**
 * 搜索旋转排序数组
 */
public class SearchRotation {
    /**
     * 33. 搜索旋转排序数组
     *
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
     * 81. 搜索旋转排序数组 II
     *
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     *
     * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
     *
     * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
     *
     * 示例 1:
     *
     * 输入: nums = [2,5,6,0,0,1,2], target = 0
     * 输出: true
     * 示例 2:
     *
     * 输入: nums = [2,5,6,0,0,1,2], target = 3
     * 输出: false
     * 进阶:
     *
     * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
     * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean searchII(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            //已找到
            if(nums[mid] == target) return true;
            //有重复，需要调整到不重复为止，这里牺牲了时间
            else if(nums[start] == nums[end]) end --;

            else if(nums[mid] > nums[start]) { //左半部分有序
                if(nums[mid] > target && nums[start] <= target) {
                    //在左半部分
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

            else {  //右半部分有序
                if(nums[mid] < target && nums[end] >= target) {
                    //在右半部分
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return false;
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
            //这里是假设了数组中不存在重复元素，如果存在重复元素，需要多加一个分支
            //比如[1,1,1,1,1,0,1] 和[1,0,1,1,1,1,1]这里就不好区分了。这是从牛客网受到的启发
//            else if(nums[start] == nums[end]) {
//                end --;
//            }
            else if(nums[mid] < nums[end]) {
                end = mid;
            } else {
                start = mid;
            }
        }

        return nums[start];
    }
}