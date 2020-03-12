package com.example.leetcode.array;

/**
 * 数组中出现次数超过一半的数字
 */
public class MoreThanHalfNum {
    /**
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组
     * {1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
     *
     * 注：题目没有表述清楚，刚好一半的时候不符合条件，必须大于一半
     *
     * 解题思路：
     * 如果有符合条件的数字，则它出现的次数比其他所有数字出现的次数和还要多。
     * 在遍历数组时保存两个值：一是数组中一个数字，一是次数。遍历下一个数字时，若它与之前保存的数字相同，则次数
     * 加1，否则次数减1；若次数为0，则保存下一个数字，并将次数置为1。遍历结束后，所保存的数字即为所求。然后再判
     * 断它是否符合条件即可。
     * https://www.nowcoder.com/practice/e8a1b01a2df14cb2b228b30ee6a92163?tpId=13&tqId=11181&tPage=2&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     */
    public int MoreThanHalfNum_Solution(int [] array) {
        if(array == null || array.length == 0) return 0;

        int res = array[0];
        int times = 0;
        for (int i = 0; i < array.length; i++) {
            if(times == 0) {
                res = array[i];
            }
            if(res == array[i]) times++;
            //出现次数相抵消
            else times--;
        }

        //重新遍历一遍确定是否符合超过一半
        times = 0;
        for (int i = 0; i < array.length; i++) {
            if(array[i] == res) times++;
        }

        return (times > (array.length / 2)) ? res : 0;
    }
}
