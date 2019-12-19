package com.example.leetcode.search;

/**
 *  搜索插入位置
 */
public class SearchInsert {
    //中规中矩的二分查找
    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     *
     * 你可以假设数组中无重复元素。
     *
     * 示例 1:
     *
     * 输入: [1,3,5,6], 5
     * 输出: 2
     * 示例 2:
     *
     * 输入: [1,3,5,6], 2
     * 输出: 1
     * 示例 3:
     *
     * 输入: [1,3,5,6], 7
     * 输出: 4
     * 示例 4:
     *
     * 输入: [1,3,5,6], 0
     * 输出: 0
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/search-insert-position
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length;
        int middle;
        while (start < end) {
            middle = (start + end) / 2;

            if (nums[middle] == target) return middle;
            else if (nums[middle] > target) {
                end = middle;
            } else {
                start = middle + 1;
            }
        }

        return start;
    }

    /**
     * 278. 第一个错误的版本
     *
     * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本
     * 都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     *
     * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     *
     * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函
     * 数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     *
     * 示例:
     *
     * 给定 n = 5，并且 version = 4 是第一个错误的版本。
     *
     * 调用 isBadVersion(3) -> false
     * 调用 isBadVersion(5) -> true
     * 调用 isBadVersion(4) -> true
     *
     * 所以，4 是第一个错误的版本。 
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/first-bad-version
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int firstBadVersion(int n) {
        //这个是我自己写的版本，由于isBadVersion()比较耗时，所以执行时间只打败了5%
        int start = 1;
        int end = n;
        while (start < end) {
            //需要这样写否则会溢出超时
            int mid = start + (end - start) / 2;
            if(!isBadVersion(mid) && isBadVersion(mid + 1)) return mid + 1;
            if(isBadVersion(mid))  end = mid;
            else start = mid;
        }
        return start;
    }

    /**
     * 方法二：优化版本
     * @param n
     * @return
     */
    public int firstBadVersion2(int n) {
        int start = 1;
        int end = n;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if(isBadVersion(mid)) end = mid;
            else start = mid + 1;
        }
        return start;
    }

    boolean isBadVersion(int version) {
        return version >= 8;
    }
}
