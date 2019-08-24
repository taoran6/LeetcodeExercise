package com.example.leetcode.stack;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 * 示例:
 *
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/min-stack
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinStack {
    private Stack<Integer> data;
    private Stack<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        data = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        data.push(x);
        if(minStack.isEmpty() || x <= minStack.peek()) {
            minStack.push(x);
        }
    }

    public void pop() {
        if (data.isEmpty()) throw new RuntimeException("error");
        if (data.peek() <= minStack.peek()) minStack.pop();
        data.pop();

    }

    public int top() {
        if (data.isEmpty()) throw new RuntimeException("error");
        return data.peek();
    }

    public int getMin() {
        if(minStack.isEmpty()) throw new RuntimeException("error");
        return minStack.peek();
    }
}
