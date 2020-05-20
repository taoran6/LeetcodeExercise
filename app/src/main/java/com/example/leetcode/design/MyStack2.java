package com.example.leetcode.design;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 用队列实现栈 方法二
 *
 * push的时间复杂度O(n)
 *
 * 其实，用队列实现栈是没啥亮点的问题，总有一个操作是O(n)
 */
public class MyStack2 {
    /**
     * 仅用一个队列实现
     *
     * 每当入队一个新元素的时候，我们可以把队列的顺序反转过来。
     */

    private Queue<Integer> queue;

    /** Initialize your data structure here. */
    public MyStack2() {
        queue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.add(x);

        int size = queue.size() - 1;
        while (size > 0) {
            //循环把前面的数加到后面，保证新出队的是最新加入的元素
            queue.offer(queue.poll());
            size --;
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int peek = queue.poll();
        return peek;
    }

    /** Get the top element. */
    public int top() {
        if(queue.isEmpty()) return 0;

        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
