package com.example.leetcode.search;

/**
 * 在排序数组中查找元素的第一个和最后一个位置
 */
public class SearchRange {
    /**
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     *
     * 你的算法时间复杂度必须是 O(log n) 级别。
     *
     * 如果数组中不存在目标值，返回 [-1, -1]。
     *
     * 示例 1:
     *
     * 输入: nums = [5,7,7,8,8,10], target = 8
     * 输出: [3,4]
     * 示例 2:
     *
     * 输入: nums = [5,7,7,8,8,10], target = 6
     * 输出: [-1,-1]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：三次二分查找，执行时间0ms
     */

    public int[] searchRange1(int[] nums, int target) {
        int[] ans = new int[2];

        int left = 0;
        int right = nums.length - 1;
        int index = -1;

        //先二分法找到任意一个target的位置
        while (left <= right) {
            int mid = (left + right) / 2;       //计算 mid 时需要技巧防止溢出，即 mid=left+(right-left)/2
            if(nums[mid] == target) {
                index = mid;
                break;
            } else if(nums[mid] < target) left = mid + 1;
            else right = mid - 1;
        }

        //数组中不存在目标值
        if(index < 0) {
            ans[0] = -1;
            ans[1] = -1;
            return ans;
        }

        //一定存在目标值，先二分法找左端点
        left = 0; right = index;
        while (left <= right) {     //其实这里while(true) 也可以的，因为肯定存在目标值
            int mid = (left + right) / 2;
            if(nums[mid] == target) {
                if(mid == 0 || nums[mid - 1] < target) {
                    ans[0] = mid;
                    break;
                }
                else right = mid - 1;
            } else {    // nums[mid] < target
                left = mid + 1;
            }
        }

        //二分法找右端点
        left = index; right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target) {
                if(mid == nums.length - 1 || nums[mid + 1] > target) {
                    ans[1] = mid;
                    break;
                }
                else left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

    /**
     * 方法二：直接搜索左右边界
     *
     * 附上一篇正确理解二分查找的题解：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/solution/er-fen-cha-zhao-suan-fa-xi-jie-xiang-jie-by-labula/
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        // 二分查找
        int[] result = new int[2];
        result[0] = left_bound(nums, target);
        result[1] = right_bound(nums, target);

        return result;
    }

    int left_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length; // 注意

        while (left < right) { // 注意
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid; // 注意
            }
        }
        if (left == nums.length) return -1;
        // 类似之前算法的处理方式
        return nums[left] == target ? left : -1;
    }
    int right_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                left = mid + 1; // 注意
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        if (left == 0) return -1;
        return nums[left-1] == target ? (left-1) : -1;
    }

    /**
     * 方法三：我自己的写法，还是习惯闭区间
     * @param array
     * @param k
     * @return
     */
    public int GetNumberOfK(int [] array , int k) {
        if(array == null || array.length == 0) return 0;
        int left = leftBound(array, k);
        int right = rightBound(array, k);
        return left == -1 ? 0 : right - left + 1;
    }

    private int leftBound(int[] array, int k) {
        int start = 0;
        int end = array.length - 1;
        while(start < end) {
            int mid = start + (end - start)/2;
            if(array[mid] != k && array[mid + 1] == k) return mid+1;
            else if(array[mid] < k) start = mid+1;
            else end = mid;
        }
        return array[start] == k ? start : -1;
    }

    private int rightBound(int[] array, int k) {
        int start = 0;
        int end = array.length - 1;
        while(start < end) {
            int mid = start + (end - start) /2;
            if(array[mid] == k && array[mid + 1] != k) return mid;
            else if(array[mid] <= k) start = mid + 1;
            else end = mid;
        }
        return array[start] == k ? start : -1;
    }
}
