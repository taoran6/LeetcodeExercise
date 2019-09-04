package com.example.leetcode.array;

/**
 * 两数之和
 */
public class TwoSum {
    /**
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
    public int[] twoSum(int[] numbers, int target) {

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
    public int[] twoSum2(int[] numbers, int target) {
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
}
