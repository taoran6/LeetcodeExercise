package com.example.leetcode.compute;

import java.util.HashSet;
import java.util.Set;

/**
 * 快乐数，很有意思的一道题，三种解法
 */
public class HappyNumber {
    /**
     * 编写一个算法来判断一个数是不是“快乐数”。
     *
     * 一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这
     * 个数变为 1，也可能是无限循环但始终变不到 1。如果可以变为 1，那么这个数就是快乐数。
     *
     * 示例: 
     *
     * 输入: 19
     * 输出: true
     * 解释:
     * 12 + 92 = 82
     * 82 + 22 = 68
     * 62 + 82 = 100
     * 12 + 02 + 02 = 1
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/happy-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：使用hash表,用时 7ms
     *
     * @param n
     * @return
     */
    public boolean isHappy1(int n) {
        Set<Integer> set = new HashSet<>();
        while (!set.contains(n)) {
            set.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    /**
     * 求每个位置上的数字的平方和
     * @param n
     * @return
     */
    private int getNext(int n) {
        int ans = 0;
        while (n != 0) {
            ans += (n % 10) * (n % 10);
            n = n /10;
        }
        return ans;
    }

    /**
     * 方法二：递归,用时 2ms
     *
     * 不是快乐数的数称为不快乐数(unhappy number)，所有不快乐数的数位平方和计算，最后都会进入
     * 4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4 的循环中
     * 已知规律： [1 ~ 4] 中只有 1 是快乐数，[5 ~ ∞] 的数字要么回归到 1 要么回归到 4 ，3，2（技巧性还是很高的）
     * 因此仅需在 n > 4 时调用递归
     *
     *
     * 为什么用时这么短呢？因为我们用的是尾递归！
     * 这里看到题解的一段话：如果一个函数中所有递归形式的调用都出现在函数的末尾，我们称这个递归函数是尾递归的。
     * 当递归调用是整个函数体中最后执行的语句且它的返回值不属于表达式的一部分时，这个递归调用就是尾递归。尾递
     * 归函数的特点是在回归过程中不用做任何操作，这个特性很重要，因为大多数现代的编译器会利用这种特点自动生成优
     * 化的代码。
     *
     * 作者：QQqun902025048
     * 链接：https://leetcode-cn.com/problems/happy-number/solution/python-1xing-by-knifezhu-9/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public boolean isHappy2(int n) {
        if(n <= 4) return n == 1;
        return isHappy2(getNext(n));
    }

    /**
     * 方法三：快慢指针 4ms
     *
     * 使用“快慢指针”思想找出循环：“快指针”每次走两步，“慢指针”每次走一步，当二者相等时，即为一个循环周期。此时，
     * 判断是不是因为1引起的循环，是的话就是快乐数，否则不是快乐数。
     * 注意：此题不建议用集合记录每次的计算结果来判断是否进入循环，因为这个集合可能大到无法存储；另外，也不建议
     * 使用递归，同理，如果递归层次较深，会直接导致调用栈崩溃。不要因为这个题目给出的整数是int型而投机取巧。
     *
     * 作者：rachy
     * 链接：https://leetcode-cn.com/problems/happy-number/solution/shi-yong-kuai-man-zhi-zhen-si-xiang-zhao-chu-xun-h/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public boolean isHappy3(int n) {
        int slow = n;
        int fast = getNext(n);
        while (slow != fast) {
            slow = getNext(slow);
            fast = getNext(fast);
            fast = getNext(fast);
        }
        return slow == 1;
    }
}
