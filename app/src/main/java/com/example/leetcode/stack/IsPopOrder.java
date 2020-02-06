package com.example.leetcode.stack;

import java.util.Stack;

/**
 * 栈的压入、弹出序列
 */
public class IsPopOrder {
    /**
     * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有
     * 数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但
     * 4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
     * @param pushA
     * @param popA
     * @return
     */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if(pushA.length != popA.length) return false;

        Stack<Integer> stack = new Stack<>();
        int pushIndex = 0;
        int popIndex = 0;
        while (popIndex < popA.length) {
            while (stack.isEmpty() || popA[popIndex] != stack.peek()) {
                if(pushIndex < pushA.length) stack.push(pushA[pushIndex ++]);
                else return false;
            }

            stack.pop();
            popIndex ++;
        }

        return true;
    }
}
