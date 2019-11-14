package com.example.leetcode.design;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 225. 用队列实现栈
 */
public class MyStack {
    /**
     * 使用队列实现栈的下列操作：
     *
     * push(x) -- 元素 x 入栈
     * pop() -- 移除栈顶元素
     * top() -- 获取栈顶元素
     * empty() -- 返回栈是否为空
     * 注意:
     *
     * 你只能使用队列的基本操作-- 也就是 push to back, peek/pop from front, size, 和 is empty 这些操作是合法的。
     * 你所使用的语言也许不支持队列。 你可以使用 list 或者 deque（双端队列）来模拟一个队列 , 只要是标准的队列操作即可。
     * 你可以假设所有操作都是有效的（例如, 对一个空的栈不会调用 pop 或者 top 操作）。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/implement-stack-using-queues
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：使用另一个队列辅助
     * push的时间复杂度O(1),pop的时间复杂度O(n)
     *
     * 另一种方法是push的时间复杂度O(n),pop的时间复杂度O(1),也是用一个队列辅助，思路都是差不多的
     *
     * 方法三: 参见{@link MyStack2}
     */

    private Queue<Integer> queue;
    private Queue<Integer> queueHelp;
    private int top;


    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<>();
        queueHelp = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.offer(x);
        top = x;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if(queue.isEmpty()) return 0;

        for (int i = 0; i < queue.size() - 2; i++) {
            queueHelp.offer(queue.poll());
        }
        if(queue.size() > 1) {
            top = queue.poll();
            queueHelp.offer(top);
        } else {
            top = 0;
        }
        //交换queue和queueHelp，避免拷贝
        Queue<Integer> tmp = queue;
        queue = queueHelp;
        queueHelp = tmp;
        return queueHelp.poll();
    }

    /** Get the top element. */
    public int top() {
        return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }

    /**
     * Your MyStack object will be instantiated and called as such:
     * MyStack obj = new MyStack();
     * obj.push(x);
     * int param_2 = obj.pop();
     * int param_3 = obj.top();
     * boolean param_4 = obj.empty();
     */
}
