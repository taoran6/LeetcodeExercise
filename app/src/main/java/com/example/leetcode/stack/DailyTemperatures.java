package com.example.leetcode.stack;

import java.util.Stack;

/**
 * 739. 每日温度
 */
public class DailyTemperatures {

    /**
     * 根据每日 气温 列表，请重新生成一个列表，对应位置的输入是你需要再等待多久温度才会升高超过该日的天数。如果之
     * 后都不会升高，请在该位置用 0 来代替。
     *
     * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 
     * [1, 1, 4, 2, 1, 1, 0, 0]。
     *
     * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/daily-temperatures
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *
     * 单调递增栈，从栈顶元素到栈底元素递增
     * 同时为了方便，我们在栈中放置索引，而不是温度值本身
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        Stack<Integer> stack = new Stack<>();

        for(int i = T.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && T[stack.peek()] <= T[i]) {
                stack.pop();
            }
            if(stack.isEmpty()) ans[i] = 0;
            else ans[i] = stack.peek() - i;

            stack.push(i);
        }
        return ans;
    }

    /**
     * 这里有一个优化使用数组代替栈，思路是一样的
     */
    public int[] dailyTemperatures2(int[] T) {
        int[] ans = new int[T.length];
        int[] stack = new int[T.length];
        //top表示栈顶下标
        int top = -1;
        for(int i = T.length - 1; i >= 0; i--) {
            while (top != -1 && T[stack[top]] <= T[i]){
                top --;
            }
            //三目运算
            ans[i] = top == -1 ? 0 : stack[top] - i;
            stack[++ top] = i;
        }
        return ans;
    }

    /**
     * 另一种方法，迭代，从右往左，重用之前的计算
     */
    public int[] dailyTemperatures3(int[] T) {
        int[] res = new int[T.length];
        res[T.length - 1] = 0;
        for (int i = T.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < T.length; j += res[j]) {
                if (T[i] < T[j]) {
                    res[i] = j - i;
                    break;
                } else if (res[j] == 0) {
                    res[i] = 0;
                    break;
                }
            }
        }
        return res;
    }
}
