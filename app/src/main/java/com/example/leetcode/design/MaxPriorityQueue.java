package com.example.leetcode.design;

/**
 * 二叉堆实现优先级队列
 *
 * 这里添加和删除元素的时间复杂度都是O（logn）即树的高度
 */
public class MaxPriorityQueue {     //这里实现一个大顶堆
    // 数组保存所有数据
    private int[] data;
    // 数据大小
    private int size;

    public MaxPriorityQueue(int capacity) {
        // 注意这里第0个数据不存储
        data = new int[capacity + 1];
    }

    /**
     * 删除并返回最大的数
     * @return
     */
    public int poll() {
        int ans = data[1];
        // 把最后的元素换到最前面
        data[1] = data[size];
        // 清空最后一位，是对象的话置为null
        data[size] = 0;
        size --;
        // 让 data[1] 下沉到正确位置
        sink(1);
        //返回最大元素
        return ans;
    }

    /**
     * 返回最大的数
     * @return
     */
    public int peek() {
        return data[1];
    }

    /**
     * 向队列插入一个数据
     */
    public void offer(int num) {
        size ++;
        // 先把新元素加到最后
        data[size] = num;
        // 然后让它上浮到正确的位置
        swim(size);
    }

    /**
     * 上浮第 k 个元素，以维护最大堆性质
     */
    private void swim(int k) {
        // 当k节点比他的父节点要大时，需要将k上浮
        while (k != 1 && isLess(parentIndex(k), k)) {
            swap(k, parentIndex(k));
            // 更新k,继续判断
            k = parentIndex(k);
        }
    }

    /**
     * 下沉第 k 个元素，以维护最大堆性质
     * @param k
     */
    private void sink(int k) {
        while (leftIndex(k) <= size) {
            int biggerIndex = leftIndex(k);
            if(rightIndex(k) <= size && isLess(leftIndex(k), rightIndex(k))) {
                biggerIndex = rightIndex(k);
            }
            if(isLess(k, biggerIndex)) {
                swap(k, biggerIndex);
                k = biggerIndex;
            }
        }

    }

    /**
     * 交换数组的两个元素
     * @param i 交换的index
     * @param j 交换的index
     */
    private void swap(int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    /**
     * data[i] 是否比 data[j] 小?
     *
     * 注：这里可以用泛型扩展
     * @param i
     * @param j
     */
    private boolean isLess(int i, int j) {
        return data[i] < data[j];
    }


    // 左孩子的索引
    private int leftIndex(int k) {
        return k * 2;
    }

    // 右孩子的索引
    private int rightIndex(int k) {
        return k * 2 + 1;
    }

    // 父节点的索引
    private int parentIndex(int k) {
        return k / 2;
    }
}
