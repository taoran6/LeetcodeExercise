package com.example.leetcode.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * 煎饼排序
 */
public class PancakeSort {
    /**
     * 969. 煎饼排序
     *
     * 给定数组 A，我们可以对其进行煎饼翻转：我们选择一些正整数 k <= A.length，然后反转 A 的前 k 个元素的顺序。
     * 我们要执行零次或多次煎饼翻转（按顺序一次接一次地进行）以完成对数组 A 的排序。
     *
     * 返回能使 A 排序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在 10 * A.length 范围内的有效答
     * 案都将被判断为正确。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：[3,2,4,1]
     * 输出：[4,2,4,3]
     * 解释：
     * 我们执行 4 次煎饼翻转，k 值分别为 4，2，4，和 3。
     * 初始状态 A = [3, 2, 4, 1]
     * 第一次翻转后 (k=4): A = [1, 4, 2, 3]
     * 第二次翻转后 (k=2): A = [4, 1, 2, 3]
     * 第三次翻转后 (k=4): A = [3, 2, 1, 4]
     * 第四次翻转后 (k=3): A = [1, 2, 3, 4]，此时已完成排序。
     * 示例 2：
     *
     * 输入：[1,2,3]
     * 输出：[]
     * 解释：
     * 输入已经排序，因此不需要翻转任何内容。
     * 请注意，其他可能的答案，如[3，3]，也将被接受。
     *  
     *
     * 提示：
     *
     * 1 <= A.length <= 100
     * A[i] 是 [1, 2, ..., A.length] 的排列
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/pancake-sorting
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public List<Integer> pancakeSort(int[] A) {
        if(A == null || A.length == 0) return new ArrayList<>();

        List<Integer> ans = new ArrayList<>();
        for(int i = A.length - 1; i > 0; i--) {
            //寻找最大的饼
            int largestIndex = findLargest(A, 0, i);
            //最大的饼已经在最下面，可以优化
            if(largestIndex == i) continue;

            //最大的饼在最上面也可以优化
            if(largestIndex != 0) {
                ans. add(largestIndex + 1);
                reverseArray(A, 0, largestIndex);
            }

            ans.add(i + 1);
            reverseArray(A, 0, i);
        }
        return ans;
    }

    private int findLargest(int[] A, int start, int end) {
        int ans = start;
        for (int i = start; i <= end; i++) {
            if(A[i] > A[ans]) ans = i;
        }
        return ans;
    }

    private void reverseArray(int[] A, int start, int end) {
        while (start < end) {
            int tmp = A[start];
            A[start] = A[end];
            A[end] = tmp;
            start ++;
            end --;
        }
    }
}
