package com.example.leetcode.unionfind;

/**
 * 并查集
 */
public class UN {
    // 记录树的“重量”
    int[] sizes;
    // 存储若干棵树
    int[] parents;
    // 记录连通分量个数
    int count;

    public UN(int x) {
        parents = new int[x];
        count = x;
        sizes = new int[x];

        for (int i = 0; i < x; i++) {
            parents[i] = i;
        }
    }

    /**
     * 返回节点 x 的根节点
     * @param x
     * @return
     */
    private int findRoot(int x) {
        while (parents[x] != x) {
            // 进行路径压缩
            parents[x] = parents[parents[x]];
            x = parents[x];
        }
        return x;
    }

    public int getCount() {
        return count;
    }

    /**
     *  判断 x 和 y 是否互相连通
     * @param x
     * @param y
     * @return
     */
    public boolean isConnected(int x, int y) {
        int rootX = findRoot(x);
        int rootY = findRoot(y);
        return rootX == rootY;
    }

    /**
     * 将 x 和 y 连通
     * @param x
     * @param y
     */
    public void union(int x, int y) {
        int rootX = findRoot(x);
        int rootY = findRoot(y);
        if (rootX == rootY) {
            return;
        }

        // 小树接到大树下面，较平衡
        if (sizes[rootX] > sizes[rootY]) {
            parents[rootY] = rootX;
            sizes[rootX] += sizes[rootY];
        } else {
            parents[rootX] = rootY;
            sizes[rootX] = rootY;
        }
    }

}
