package com.example.leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 399. 除法求值
 */
public class CalcEquation {
    /**
     * 给出方程式 A / B = k, 其中 A 和 B 均为代表字符串的变量， k 是一个浮点型数字。根据已知方程式求解问题，并
     * 返回计算结果。如果结果不存在，则返回 -1.0。
     * <p>
     * 示例 :
     * 给定 a / b = 2.0, b / c = 3.0
     * 问题: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? 
     * 返回 [6.0, 0.5, -1.0, 1.0, -1.0 ]
     * <p>
     * 输入为: vector<pair<string, string>> equations, vector<double>& values,
     * vector<pair<string, string>> queries(方程式，方程式结果，问题方程式)， 其中 
     * equations.size() == values.size()，即方程式的长度与方程式结果长度相等（程式与结果一一对应），并且结果
     * 值均为正数。以上为方程式的描述。 返回vector<double>类型。
     * <p>
     * 基于上述例子，输入如下：
     * <p>
     * equations(方程式) = [ ["a", "b"], ["b", "c"] ],
     * values(方程式结果) = [2.0, 3.0],
     * queries(问题方程式) = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
     * 输入总是有效的。你可以假设除法运算中不会出现除数为0的情况，且不存在任何矛盾的结果。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/evaluate-division
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     *
     * 题目本身并不难想，不过code起来有些难度，不管哪种方法代码都很长
     * 方法一：并查集
     * 我自己想的方法，竟然一次过速度超过 100 %，开心
     */
    List<Map<String, Double>> collect = new ArrayList<>();

    public double[] calcEquation(List<List<String>> equations, double[] values,
                                 List<List<String>> queries) {
        //先建立并查集 Map<String, Double>中 double对应该字符串的权重
        for (int i = 0; i < values.length; i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            int m1 = findColl(a);
            int m2 = findColl(b);
            if (m1 == -1 && m2 == -1) { //将两个字符串加到新建的集合
                Map<String, Double> map = new HashMap<>();
                map.put(a, values[i]);
                map.put(b, 1.0);
                collect.add(map);
            } else if (m1 == -1) {  //将第二个字符串加进集合
                collect.get(m2).put(a, collect.get(m2).get(b) * values[i]);
            } else if (m2 == -1) {  //将第一个字符串加进集合
                collect.get(m1).put(b, collect.get(m1).get(a) / values[i]);
            } else if (m1 != m2) {  //将两个集合合并
                unionColl(m1, m2, collect.get(m2).get(b) * values[i] / collect.get(m1).get(a));
            }
        }

        //查找输出即可
        double[] ans = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);
            int m1 = findColl(a);
            int m2 = findColl(b);
            if (m1 == m2 && m1 != -1) ans[i] = collect.get(m1).get(a) / collect.get(m2).get(b);
            else ans[i] = -1.0;
        }
        return ans;
    }

    //查找该字符串属于第几个集合，若不存在返回-1
    private int findColl(String str) {
        for (int i = 0; i < collect.size(); i++) {
            if (collect.get(i).containsKey(str)) return i;
        }
        return -1;
    }

    //将第 m1 个集合合并到第 m2 个集合中，需要将m1中的数乘以scale倍
    private void unionColl(int m1, int m2, double scale) {
        Map<String, Double> map2 = collect.get(m2);
        for (Map.Entry<String, Double> entry : collect.get(m1).entrySet()) {
            map2.put(entry.getKey(), entry.getValue() * scale);
        }
        collect.remove(m1);
    }

    /**
     * 方法二：根据图的知识，图的BFS，图的DFS或Floyd算法
     */
    //先定义一个数据结构
    class Node {
        String start;
        String end;
        double val;

        public Node(String start, String end, double val) {
            this.start = start;
            this.end = end;
            this.val = val;
        }
    }
    //使用邻接表
    private Map<String, List<Node>> graph = new HashMap<>();
    public double[] calcEquation2(List<List<String>> equations, double[] values,
                                 List<List<String>> queries) {

        //建图
        for(int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            if(!graph.containsKey(a)) graph.put(a, new ArrayList<>());
            if(!graph.containsKey(b)) graph.put(b, new ArrayList<>());
            graph.get(a).add(new Node(a, b, values[i]));
            graph.get(b).add(new Node(b, a, 1 / values[i]));
        }

        //遍历
        double[] ans = new double[queries.size()];
//        List<String> isVisited = new ArrayList<>();
        for (int i = 0; i < queries.size(); i++) {
            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);
            if(!graph.containsKey(a) || !graph.containsKey(b)) ans[i] = -1.0;
            else if(a.equals(b)) ans[i] = 1.0;
            else {
//                isVisited.clear();
//                double result = dfs(a, b, isVisited);
                double result = bfs(a, b);
                if (result == Double.MAX_VALUE)
                    ans[i] = -1.0;
                else ans[i] = result;
            }
        }

        return ans;
    }

    /**
     * 方法二（1）深度优先遍历，这里需要传入一个List<String> isVisited，便于递归时判断是否已经走过该节点
     */
    private double dfs(String start, String end,  List<String> isVisited) {
        List<Node> aEdges = graph.get(start);
        isVisited.add(start);
        for (Node node : aEdges) {
            if(isVisited.contains(node.end)) continue;
            if(node.end.equals(end)) {
                return node.val;
            }
            else {
                double x = dfs(node.end, end, isVisited);
                if(x != Double.MAX_VALUE) {
                    return x * node.val;
                }
            }
        }
        return Double.MAX_VALUE;
    }

    /**
     * 方法二（2）广度优先遍历，核心思想是使用一个队列存储辅助，同样为了避免环，使用List<String> isVisited，
     * 但是由于不是递归，使用局部变量即可
     */
    private double bfs(String start, String end) {
        List<String> isVisited = new ArrayList<>();
        isVisited.add(start);
        Queue<Node> queue = new LinkedList<>(graph.get(start));
        while (!queue.isEmpty()) {
            Node edge = queue.poll();
            if(edge.end.equals(end)) return edge.val;
            else for(Node nextNode : graph.get(edge.end)) {
                if(! isVisited.contains(nextNode.end)) {
                    isVisited.add(nextNode.end);
                    queue.offer(new Node(start, nextNode.end, edge.val * nextNode.val));
                }
            }
        }
        return Double.MAX_VALUE;
    }


    /**
     * 方法三：Floyd算法
     */
    public double[] calcEquation3(List<List<String>> equations, double[] values,
                                  List<List<String>> queries) {
        //建立邻接矩阵
        int k = 0;
        Map<String, Integer> indexMap = new HashMap<>();
        double[][] graph = new double[equations.size() * 2][equations.size() * 2];
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            if(! indexMap.containsKey(a)) indexMap.put(a, k++);
            if(! indexMap.containsKey(b)) indexMap.put(b, k++);
            graph[indexMap.get(a)][indexMap.get(b)] = values[i];
            graph[indexMap.get(b)][indexMap.get(a)] = 1 / values[i];
        }

        //Floyd算法寻找最短路径
        floyd(graph, k);

        //遍历输出即可
        double ans[] = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);
            if(! indexMap.containsKey(a) || ! indexMap.containsKey(b)) ans[i] = -1.0;
            else if(a.equals(b)) ans[i] = 1.0;
            else {
                double res = graph[indexMap.get(a)][indexMap.get(b)];
                ans[i] = res == 0.0 ? -1.0 : res;
            }
        }
        return ans;
    }

    /**
     * floyd算法求最短路径
     * @param graph 邻接矩阵
     * @param k 图的节点数
     */
    private void floyd(double[][] graph, int k) {
        //核心是以每个节点作为媒介，更新权值k次
        for(int i = 0; i < k; i++) {
            for(int j = 0; j < k; j++) {
                for(int m = 0; m < k; m++) {
                    if(graph[j][m] == 0.0 && graph[j][i] != 0.0 && graph[i][m] != 0.0) {
                        graph[j][m] = graph[j][i] * graph[i][m];
                    }
                }
            }
        }
    }
}
