package com.example.leetcode.search;


import java.util.PriorityQueue;

/**
 * 寻找峰值
 */
public class FindPeakElement {
    /**
     * 峰值元素是指其值大于左右相邻值的元素。
     *
     * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
     *
     * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
     *
     * 你可以假设 nums[-1] = nums[n] = -∞。
     *
     * 示例 1:
     *
     * 输入: nums = [1,2,3,1]
     * 输出: 2
     * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
     * 示例 2:
     *
     * 输入: nums = [1,2,1,3,5,6,4]
     * 输出: 1 或 5
     * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
     *      或者返回索引 5， 其峰值元素为 6。
     * 说明:
     *
     * 你的解法应该是 O(logN) 时间复杂度的。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-peak-element
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法：二分查找
     * 首先要注意题目条件，在题目描述中出现了 nums[-1] = nums[n] = -∞，这就代表着 只要数组中存在一个元素比
     * 相邻元素大，那么沿着它一定可以找到一个峰值
     * 根据上述结论，我们就可以使用二分查找找到峰值
     * 查找时，左指针 l，右指针 r，以其保持左右顺序为循环条件
     * 根据左右指针计算中间位置 m，并比较 m 与 m+1 的值，如果 m 较大，则左侧存在峰值，r = m，如果 m + 1 较大，则右侧存在峰值，l = m + 1
     *
     * 作者：guanpengchn
     * 链接：https://leetcode-cn.com/problems/find-peak-element/solution/hua-jie-suan-fa-162-xun-zhao-feng-zhi-by-guanpengc/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int findPeakElement(int[] nums) {
        //中心思想：二分查找大的那一半一定会有峰值
        if(nums == null || nums.length < 2) return 0;
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {      //我的理解是沿着切线找峰值
            int mid = (right + left) / 2;
            if(nums[mid] > nums[mid + 1]) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    /**
     * 852. 山脉数组的峰顶索引
     *
     * 我们把符合下列属性的数组 A 称作山脉：
     *
     * A.length >= 3
     * 存在 0 < i < A.length - 1 使得A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
     * 给定一个确定为山脉的数组，返回任何满足 
     * A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1] 的 i 的值。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：[0,1,0]
     * 输出：1
     * 示例 2：
     *
     * 输入：[0,2,1,0]
     * 输出：1
     *  
     *
     * 提示：
     *
     * 3 <= A.length <= 10000
     * 0 <= A[i] <= 10^6
     * A 是如上定义的山脉
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/peak-index-in-a-mountain-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * O(n)复杂度的方法很简单，就略过啦
     *
     * 方法一：二分查找
     */
    public int peakIndexInMountainArray(int[] A) {
        int left = 0;
        int right = A.length - 1;

        while (left < right) {
            int mid = (left + right) >> 1;
            //由于给定的峰值一定存在，一定会走到这个分支
            if(A[mid] > A[mid - 1] && A[mid] > A[mid + 1]) return mid;
            if(A[mid] > A[mid - 1] && A[mid] < A[mid + 1]) left = mid;
            else right = mid;
        }
        return left;
    }

    /**
     * 方法二：另一种二分查找
     *
     * 只需要查找A[i] < A[i+1] 的点即可
     */
    public int peakIndexInMountainArray2(int[] A) {
        int lo = 0, hi = A.length - 1;
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (A[mi] < A[mi + 1])
                lo = mi + 1;
            else
                hi = mi;
        }
        return lo;
    }


    public interface I {
        void dof();
    }
    public class A {
        public void m(int a1) {
            final long[] l1 = {0};
            I i = new I() {
                public void dof() {
                    int a1 = 5;
                    l1[0] += a1; }
            }; }
    }
}
