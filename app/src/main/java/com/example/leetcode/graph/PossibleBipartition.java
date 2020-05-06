package com.example.leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;

public class PossibleBipartition {
    /**
     * 886. 可能的二分法
     *
     * 给定一组 N 人（编号为 1, 2, ..., N）， 我们想把每个人分进任意大小的两组。
     *
     * 每个人都可能不喜欢其他人，那么他们不应该属于同一组。
     *
     * 形式上，如果 dislikes[i] = [a, b]，表示不允许将编号为 a 和 b 的人归入同一组。
     *
     * 当可以用这种方法将每个人分进两组时，返回 true；否则返回 false。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：N = 4, dislikes = [[1,2],[1,3],[2,4]]
     * 输出：true
     * 解释：group1 [1,4], group2 [2,3]
     * 示例 2：
     *
     * 输入：N = 3, dislikes = [[1,2],[1,3],[2,3]]
     * 输出：false
     * 示例 3：
     *
     * 输入：N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
     * 输出：false
     *  
     *
     * 提示：
     *
     * 1 <= N <= 2000
     * 0 <= dislikes.length <= 10000
     * 1 <= dislikes[i][j] <= N
     * dislikes[i][0] < dislikes[i][1]
     * 对于 dislikes[i] == dislikes[j] 不存在 i != j 
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/possible-bipartition
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 思想：二分图，查找该图是否是二分图（能够用两种颜色为所有顶点着色，使得任何一条边的两个顶点颜色不同。）
     */
    public boolean possibleBipartition(int N, int[][] dislikes) {
        ArrayList<Integer>[] graph = new ArrayList[N+1];

        for(int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        // 建立图的邻接表
        for (int[] dislike : dislikes) {
            graph[dislike[0]].add(dislike[1]);
            graph[dislike[1]].add(dislike[0]);
        }

        //存储每个节点需要涂什么颜色
        HashMap<Integer, Boolean> colorMap = new HashMap<>();
        for(int i = 1; i <= N; i++) {
            // 如果这个节点没涂过色，深度递归给其涂色，最初始的那个涂false颜色
            if(!colorMap.containsKey(i) && !dfs(i, false, colorMap, graph)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 给节点node涂色为color,并深度遍历给其邻节点涂色，若无法涂色为二分图返回false
     * @param node
     * @param color
     * @return
     */
    private boolean dfs(int node, boolean color, HashMap<Integer, Boolean> colorMap, ArrayList<Integer>[] graph) {
        // 之前已经涂色过了，验证一下有没有冲突
        if(colorMap.containsKey(node)) {
            return color == colorMap.get(node);
        }

        colorMap.put(node, color);
        for (int nextNode : graph[node]) {
            // 给相邻节点涂色为另一种颜色。同时验证是否冲突
            if(!dfs(nextNode, !color, colorMap, graph)) {
                return false;
            }
        }
        return true;
    }
}
