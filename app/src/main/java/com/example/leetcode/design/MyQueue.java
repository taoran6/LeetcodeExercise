package com.example.leetcode.design;

import java.util.Stack;

/**
 * 用栈实现队列
 */
public class MyQueue {
    /**
     * 使用栈实现队列的下列操作：
     *
     * push(x) -- 将一个元素放入队列的尾部。
     * pop() -- 从队列首部移除元素。
     * peek() -- 返回队列首部的元素。
     * empty() -- 返回队列是否为空。
     * 示例:
     *
     * MyQueue queue = new MyQueue();
     *
     * queue.push(1);
     * queue.push(2);
     * queue.peek();  // 返回 1
     * queue.pop();   // 返回 1
     * queue.empty(); // 返回 false
     * 说明:
     *
     * 你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
     * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
     * 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。
     *
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/implement-queue-using-stacks
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 使用两个栈 入队 - O(1)O(1)，出队 - 摊还复杂度 O(1)O(1)
     * 根据栈 LIFO 的特性，s1 中第一个压入的元素在栈底。为了弹出 s1 的栈底元素，我们得把 s1 中所有的元素全部
     * 弹出，再把它们压入到另一个栈 s2 中，这个操作会让元素的入栈顺序反转过来。通过这样的方式，s1 中栈底元素就
     * 变成了 s2 的栈顶元素，这样就可以直接从 s2 将它弹出了。一旦 s2 变空了，我们只需把 s1 中的元素再一次转移
     * 到 s2 就可以了。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/implement-queue-using-stacks/solution/yong-zhan-shi-xian-dui-lie-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    private Stack<Integer> stack;
    private Stack<Integer> helper;
    int front;

    /** Initialize your data structure here. */
    public MyQueue() {
        stack = new Stack<>();
        helper = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        if(stack.isEmpty()) front = x;
        stack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (helper.isEmpty()) {
            while (!stack.isEmpty()) {
                helper.push(stack.pop());
            }
        }
        return helper.pop();
    }

    /** Get the front element. */
    public int peek() {
        if(helper.isEmpty()) {
            return front;
        } else {
            return helper.peek();
        }
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.isEmpty() && helper.isEmpty();
    }
}
