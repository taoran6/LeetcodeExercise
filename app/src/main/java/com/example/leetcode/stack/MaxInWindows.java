package com.example.leetcode.stack;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;


/**
 * 滑动窗口的最大值
 */
public class MaxInWindows {
    /**
     * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动
     * 窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑
     * 动窗口有以下6个： {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}，
     * {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
     *
     * 用一个双端队列，队列第一个位置保存当前窗口的最大值，当窗口滑动一次
     * 1.判断当前最大值是否过期
     * 2.新增加的值从队尾开始比较，把所有比他小的值丢掉
     * 思想和最小栈的思想有一点点相似
     *
     * https://www.nowcoder.com/practice/1624bc35a45c42c0bc17d17fa0cba788?tpId=13&tqId=11217&tPage=4&rp=4&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     */
    public ArrayList<Integer> maxInWindows(int [] num, int size){
        ArrayList<Integer> ans = new ArrayList<>();
        if(num.length == 0 || size == 0 || num.length < size) return ans;

        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            while (!deque.isEmpty() && deque.peekLast() < num[i]) {
                deque.pollLast();
            }
            deque.offerLast(num[i]);
        }

        ans.add(deque.peekFirst());

        for(int i = size; i < num.length; i++) {
            //判断当前最大值是否过期
            if(num[i - size] == deque.peekFirst()) deque.pollFirst();
            //新增加的值从队尾开始比较，把所有比他小的值丢掉
            while (!deque.isEmpty() && deque.peekLast() < num[i]) {
                deque.pollLast();
            }
            deque.offerLast(num[i]);
            ans.add(deque.peekFirst());
        }

        return ans;

    }
}
