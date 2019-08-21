package com.example.leetcode.array;

import java.util.Arrays;

/**
 * 合并两个有序数组
 */
public class Merge {
    /**
     *给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。
     *
     * 说明:
     *
     * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
     * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     * 示例:
     *
     * 输入:
     * nums1 = [1,2,3,0,0,0], m = 3
     * nums2 = [2,5,6],       n = 3
     *
     * 输出: [1,2,2,3,5,6]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/merge-sorted-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] numTmp = Arrays.copyOf(nums1, m);

        int i = 0;
        int j = 0;
        int p = 0;
        while (i < m || j < n) {
            int numTmpi = i < m ? numTmp[i] : Integer.MAX_VALUE;
            int nums2j = j < n ? nums2[j] : Integer.MAX_VALUE;
            if (numTmpi <= nums2j) {
                nums1[p] = numTmp[i];
                p++;
                i++;
            } else {
                nums1[p] = nums2[j];
                p++;
                j++;
            }
        }
    }

    /**
     * 方法二：中途截断再复制，用时和内存稍微少一点，注意边界，很容易出错
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int[] numTmp = Arrays.copyOf(nums1, m);

        int i = 0;
        int j = 0;
        int p = 0;
        while (i < m && j < n) {
            if (numTmp[i] <= nums2[j]) {
                nums1[p] = numTmp[i];
                p++;
                i++;
            } else {
                nums1[p] = nums2[j];
                p++;
                j++;
            }
        }

        //注意这边的判断
        while (i < m) {
            nums1[p] = numTmp[i];
            i++;
            p++;
        }
        while (j < n) {
            nums1[p] = nums2[j];
            j++;
            p++;
        }
    }
}
