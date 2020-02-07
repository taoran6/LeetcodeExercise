package com.example.leetcode.tree;

/**
 * 二叉搜索树的后序遍历序列
 */
public class VerifySquenceOfBST {
    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数
     * 组的任意两个数字都互不相同。
     */
    public boolean VerifySquenceOfBST(int [] sequence) {
        //牛客网这个题目表述贼坑，也不说清楚长度为0满足不满足，单独拿出来才通过提交
        if(sequence.length == 0) return false;

        return help(sequence, 0, sequence.length - 1);
    }

    private boolean help(int[] sequence, int start, int end) {
        if(start >= end) return true;
        int root = sequence[end];

        int mid = end;
        for(int i = start; i < end; i++) {
            if(sequence[i] > root) {
                mid = i;
                break;
            }
        }

        for (int i = mid + 1; i < end; i++){
            if(sequence[i] <= root) return false;
        }

        return help(sequence, start, mid - 1) && help(sequence, mid, end - 1);
    }
}
