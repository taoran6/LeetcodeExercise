package com.example.leetcode.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 207. 课程表
 */
public class CanFinish {
    /**
     * 现在你总共有 n 门课需要选，记为 0 到 n-1。
     *
     * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他
     * 们: [0,1]
     *
     * 给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？
     *
     * 示例 1:
     *
     * 输入: 2, [[1,0]]
     * 输出: true
     * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
     * 示例 2:
     *
     * 输入: 2, [[1,0],[0,1]]
     * 输出: false
     * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。
     * 这是不可能的。
     * 说明:
     *
     * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
     * 你可以假定输入的先决条件中没有重复的边。
     * 提示:
     *
     * 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行
     * 学习。
     * 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
     * 拓扑排序也可以通过 BFS 完成。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/course-schedule
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 本题可约化为：课程安排图是否是 有向无环图(DAG)
     * 方法一：我自己想的方法啦啦啦，虽然才打败13%的用户
     */
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        //先建立邻接表
        List<LinkedList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            LinkedList<Integer> linkedList = new LinkedList<>();
            linkedList.add(i);
            graph.add(linkedList);
        }
        for (int i = 0; i < prerequisites.length; i++) {
            graph.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }

        //判断是否存在环
        int emptyNode = testEmpty(graph);
        while (emptyNode != -1) {
            for (LinkedList<Integer> linkedList : graph) {
                //在图中删除该节点
                linkedList.remove(new Integer(emptyNode));
            }
            emptyNode = testEmpty(graph);
        }

        return graph.isEmpty();
    }

    //判断是否有节点的入度为0，即不存在先修课程
    private int testEmpty(List<LinkedList<Integer>> graph) {
        for (int i = 0; i < graph.size(); i++) {
            if(graph.get(i).size() == 1) {
                int ret = graph.get(i).getFirst();
                graph.remove(i);
                return ret;
            }
        }
        return -1;
    }

    /**
     * 方法二：入度表法，广度优先遍历
     *
     * 1.统计课程安排图中每个节点的入度，生成 入度表 indegrees。
     * 2.借助一个队列 queue，将所有入度为 0 的节点入队。
     * 3.当 queue 非空时，依次将队首节点出队，在图中删除此节点，在删除时将入度新变为0的节点入队
     *
     * 本方法和方法一的区别仅仅是没有建立邻接表，然后通过课程数numCourses自减来判断是否已经删去了全部顶点，
     * 减小了开销
     *
     * 时间复杂度O(m*n)，m 为边数，n 为节点数
     * 空间复杂度O(n)
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        for (int[] edge : prerequisites) {
            inDegree[edge[0]] ++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            //入度为0的节点入队
            if(inDegree[i] == 0) queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int node = queue.poll();
            //课程数自减
            numCourses --;
            //直接遍历图的边
            for (int[] edge : prerequisites) {
                if(edge[1] == node) {
                    if(-- inDegree[edge[0]] == 0) {
                        queue.offer(edge[0]);
                    }
                }
            }
        }
        return numCourses == 0;
    }

    /**
     * 方法三：深度优先遍历
     *
     * （思路是通过 DFS 判断图中是否有环）：
     * 1.借助一个标志列表 flags，用于判断每个节点 i （课程）的状态：
     *      1.未被 DFS 访问：i == 0；
     *      2.已被其他节点启动的DFS访问：i == -1；
     *      3.已被当前节点启动的DFS访问：i == 1。
     * 2.对 numCourses 个节点依次执行 DFS，判断每个节点起步 DFS 是否存在环，若存在环直接返回 False。DFS 流程；
     *  终止条件：
     *      当 flag[i] == -1，说明当前访问节点已被其他节点启动的 DFS 访问，无需再重复搜索，直接返回 True。
     *      当 flag[i] == 1，说明在本轮 DFS 搜索中节点 i 被第 2 次访问，即 课程安排图有环，直接返回 False。
     *  将当前访问节点 i 对应 flag[i] 置 1，即标记其被本轮 DFS 访问过；
     *  递归访问当前节点 i 的所有邻接节点 j，当发现环直接返回 False；
     *  当前节点所有邻接节点已被遍历，并没有发现环，则将当前节点 flag 置为 −1 并返回 True。
     * 3.若整个图 DFS 结束并未发现环，返回 True。
     *
     * 复杂度分析：
     * 时间复杂度 O(N + M)：遍历一个图需要访问所有节点和所有临边，N 和 M 分别为节点数量和边数量；
     * 空间复杂度 O(N^2)，为建立邻接矩阵所需额外空间。
     *
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/course-schedule/solution/course-schedule-tuo-bu-pai-xu-bfsdfsliang-chong-fa/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean canFinish3(int numCourses, int[][] prerequisites) {
        //建立邻接矩阵
        int[][] graph = new int[numCourses][numCourses];
        for (int[] edge : prerequisites) {
            graph[edge[1]][edge[0]] = 1;
        }

        //深度优先遍历
        int[] flags = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if(!dfs(graph, flags, i)) return false;
        }

        return true;
    }

    private boolean dfs(int[][] graph, int[] flags, int node) {
        //当前路径遍历过
        if(flags[node] == 1) return false;
        //之前的DFS遍历过不存在环
        if(flags[node] == -1) return true;

        flags[node] = 1;
        for (int i = 0; i < graph.length; i++) {
            //递归
            if(graph[node][i] == 1 && !dfs(graph, flags, i)) return false;
        }
        flags[node] = -1;
        return true;
    }

}
