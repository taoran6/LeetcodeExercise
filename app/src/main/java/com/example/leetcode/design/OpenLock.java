package com.example.leetcode.design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 752. 打开转盘锁
 */
public class OpenLock {
    /**
     * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7',
     * '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一
     * 位数字。
     *
     * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
     *
     * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法
     * 再被旋转。
     *
     * 字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。
     *
     *  
     *
     * 示例 1:
     *
     * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
     * 输出：6
     * 解释：
     * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
     * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
     * 因为当拨动到 "0102" 时这个锁就会被锁定。
     * 示例 2:
     *
     * 输入: deadends = ["8888"], target = "0009"
     * 输出：1
     * 解释：
     * 把最后一位反向旋转一次即可 "0000" -> "0009"。
     * 示例 3:
     *
     * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
     * 输出：-1
     * 解释：
     * 无法旋转到目标数字且不被锁定。
     * 示例 4:
     *
     * 输入: deadends = ["0000"], target = "8888"
     * 输出：-1
     *  
     *
     * 提示：
     *
     * 死亡列表 deadends 的长度范围为 [1, 500]。
     * 目标数字 target 不会在 deadends 之中。
     * 每个 deadends 和 target 中的字符串的数字会在 10,000 个可能的情况 '0000' 到 '9999' 中产生。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/open-the-lock
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 方法一：BFS
     */
    public int openLock(String[] deadends, String target) {
        // 记录已经穷举过的密码，防止走回头路
        Set<String> visitedNode = new HashSet<>();
        // 记录需要跳过的死亡密码
        Set<String> deadEnds = new HashSet<>();
        for (String str : deadends) {
            deadEnds.add(str);
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        visitedNode.add("0000");
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            /* 将当前队列中的所有节点向周围扩散 */
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if(curr.equals(target)) {
                    return step;
                }
                if(deadEnds.contains(curr)) {
                    continue;
                }

                /* 将一个节点的未遍历相邻节点加入队列 */
                for(int j = 0; j < 4; j++) {
                    String up = addOne(curr, j);
                    String down = minusOne(curr, j);
                    if (!visitedNode.contains(up)) {
                        queue.offer(up);
                        //注意这里记得加上 isVisited
                        visitedNode.add(up);
                    }
                    if (!visitedNode.contains(down)) {
                        queue.offer(down);
                        visitedNode.add(down);
                    }
                }
            }
            /* 在这里增加步数 */
            step ++;
        }
        return -1;
    }

    // 将 s[j] 向上拨动一次
    private String addOne(String str, int j) {
        char[] chars = str.toCharArray();
        if (chars[j] == '9') {
            chars[j] = '0';
        } else {
            chars[j] ++;
        }
        return new String(chars);
    }

    // 将 s[i] 向下拨动一次
    private String minusOne(String str, int j) {
        char[] chars = str.toCharArray();
        if (chars[j] == '0') {
            chars[j] = '9';
        } else {
            chars[j] --;
        }
        return new String(chars);
    }

    /**
     * 方法二：双边BFS
     *
     * 从起点和终点同时开始BFS，这样可以降低空间复杂度
     */
    public int openLock2(String[] deadends, String target) {
        // 记录已经穷举过的密码，防止走回头路
        Set<String> visitedNode = new HashSet<>();
        // 记录需要跳过的死亡密码
        Set<String> deadEnds = new HashSet<>();
        for (String str : deadends) {
            deadEnds.add(str);
        }

        Set<String> s1 = new HashSet<>();
        Set<String> s2 = new HashSet<>();
        s1.add("0000");
        s2.add(target);
        int step = 0;
        //s1和s2有一个为空就可以退出
        while (!s1.isEmpty() && !s2.isEmpty()) {

            Set<String> tmp = new HashSet<>();
            /* 将当前队列中的所有节点向周围扩散 */
            for (String curr : s1) {
                // 注意这里添加visited的方式跟方法一有所不同
                visitedNode.add(curr);
                if(s2.contains(curr)) {
                    return step;
                }
                if(deadEnds.contains(curr)) {
                    continue;
                }

                /* 将一个节点的未遍历相邻节点加入队列 */
                for(int j = 0; j < 4; j++) {
                    String up = addOne(curr, j);
                    String down = minusOne(curr, j);
                    if (!visitedNode.contains(up)) {
                        tmp.add(up);
                    }
                    if (!visitedNode.contains(down)) {
                        tmp.add(down);
                    }
                }
            }
            /* 在这里增加步数 */
            step ++;
            s1 = s2;
            s2 = tmp;
        }
        return -1;
    }
}
