package com.example.leetcode.sort;

import java.util.Arrays;

/**
 * 数组中的逆序对
 */
public class InversePairs {
    /**
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这
     * 个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
     * 输入描述:
     * 题目保证输入的数组中没有的相同的数字
     *
     * 数据范围：
     *
     * 	对于%50的数据,size<=10^4
     *
     * 	对于%75的数据,size<=10^5
     *
     * 	对于%100的数据,size<=2*10^5
     *
     * 示例1
     * 输入
     * 1,2,3,4,5,6,7,0
     * 输出
     * 7
     *
     * https://www.nowcoder.com/practice/96bd6684e04a44eb80e6a68efc0ec6c5?tpId=13&tqId=11188&tPage=2&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     * @param array
     * @return
     *
     * 核心思想是归并排序
     */
    public int InversePairs(int [] array) {
        int[] copy = Arrays.copyOf(array, array.length);
        return InversePairsHelp(array, copy, 0, array.length-1);
    }

    private int InversePairsHelp(int[] array, int[] copy, int start, int end) {
        if(start == end) return 0;

        int mid = start + (end - start) / 2;

        long leftCount = InversePairsHelp(array, copy, start, mid);
        long rightCount = InversePairsHelp(array, copy, mid+1, end);

        long count = 0;
        int lp = mid; int rp = end;
        int cp = end;
        //这里要用逆序
        while (lp >= start && rp > mid) {
            if(array[lp] > array[rp]) {
                count = count + rp - mid;
                copy[cp --] = array[lp --];

            } else {
                copy[cp --] = array[rp --];
            }
        }

        while (lp >= start) {
            copy[cp --] = array[lp --];
        }
        while (rp > mid) {
            copy[cp --] = array[rp --];
        }
        for (int i = start; i <= end; i++)  {
            array[i] = copy[i];
        }

        return (int)((leftCount + rightCount + count) % 1000000007);
    }
}
