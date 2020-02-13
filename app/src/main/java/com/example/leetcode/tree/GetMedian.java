package com.example.leetcode.tree;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 数据流中的中位数
 */
public class GetMedian {
    /**
     * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
     * 如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。我们使用Insert()方法读取
     * 数据流，使用GetMedian()方法获取当前读取数据的中位数。
     *
     * 链接：https://www.nowcoder.com/questionTerminal/9be0172896bd43948f8a32fb954e1be1?f=discussion
     * 来源：牛客网
     *
     * 先用java集合PriorityQueue来设置一个小顶堆和大顶堆
     * 主要的思想是：因为要求的是中位数，那么这两个堆，大顶堆用来存较小的数，从大到小排列；
     * 小顶堆存较大的数，从小到大的顺序排序*，显然中位数就是大顶堆的根节点与小顶堆的根节点和的平均数。
     * - 保证：小顶堆中的元素都大于等于大顶堆中的元素，所以每次塞值，并不是直接塞进去，而是从另一个堆中poll出一个
     * 最大（最小）的塞值
     * - 当数目为偶数的时候，将这个值插入大顶堆中，再将大顶堆中根节点（即最大值）插入到小顶堆中；
     * - 当数目为奇数的时候，将这个值插入小顶堆中，再讲小顶堆中根节点（即最小值）插入到大顶堆中；
     * - 取中位数的时候，如果当前个数为偶数，显然是取小顶堆和大顶堆根结点的平均值；如果当前个数为奇数，显然是取小
     * 顶堆的根节点
     */

    PriorityQueue<Integer> bigHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });
    PriorityQueue<Integer> smallHeap = new PriorityQueue<>();

    public void Insert(Integer num) {
        //这里逻辑很巧妙
        if((bigHeap.size() + smallHeap.size()) % 2 == 0) {//偶数，加到大顶堆
            smallHeap.offer(num);
            bigHeap.offer(smallHeap.poll());
        } else {    //奇数，加到小顶堆
            bigHeap.offer(num);
            smallHeap.offer(bigHeap.poll());
        }
    }

    public Double GetMedian() {
        if((bigHeap.size() + smallHeap.size()) % 2 == 0) {
            return (bigHeap.peek() + smallHeap.peek()) / 2.0;
        } else {
            return bigHeap.peek() / 1.0;
        }
    }
}
