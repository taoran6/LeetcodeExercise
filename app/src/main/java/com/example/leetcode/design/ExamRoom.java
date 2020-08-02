package com.example.leetcode.design;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 855. 考场就座
 */
public class ExamRoom {
    /**
     * 在考场里，一排有 N 个座位，分别编号为 0, 1, 2, ..., N-1 。
     *
     * 当学生进入考场后，他必须坐在能够使他与离他最近的人之间的距离达到最大化的座位上。如果有多个这样的座位，他会
     * 坐在编号最小的座位上。(另外，如果考场里没有人，那么学生就坐在 0 号座位上。)
     *
     * 返回 ExamRoom(int N) 类，它有两个公开的函数：其中，函数 ExamRoom.seat() 会返回一个 int （整型数据），
     * 代表学生坐的位置；函数 ExamRoom.leave(int p) 代表坐在座位 p 上的学生现在离开了考场。每次调用 
     * ExamRoom.leave(p) 时都保证有学生坐在座位 p 上。
     *
     *  
     *
     * 示例：
     *
     * 输入：["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
     * 输出：[null,0,9,4,2,null,5]
     * 解释：
     * ExamRoom(10) -> null
     * seat() -> 0，没有人在考场里，那么学生坐在 0 号座位上。
     * seat() -> 9，学生最后坐在 9 号座位上。
     * seat() -> 4，学生最后坐在 4 号座位上。
     * seat() -> 2，学生最后坐在 2 号座位上。
     * leave(4) -> null
     * seat() -> 5，学生最后坐在 5 号座位上。
     *  
     *
     * 提示：
     *
     * 1 <= N <= 10^9
     * 在所有的测试样例中 ExamRoom.seat() 和 ExamRoom.leave() 最多被调用 10^4 次。
     * 保证在调用 ExamRoom.leave(p) 时有学生正坐在座位 p 上。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/exam-room
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 这个边界太难处理了0，N-1的情况
     */

    Map<Integer, int[]> startMap;
    Map<Integer, int[]> endMap;
    TreeSet<int[]> treeSet;
    int N;

    public ExamRoom(int N) {
        this.N = N;
        startMap = new HashMap<>();
        endMap = new HashMap<>();
        treeSet = new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int distance1 = distance(o1);
                int distance2 = distance(o2);
                if(distance1 == distance2) {
                    //索引小的在前
                    return o1[0] - o2[0];
                } else {
                    //距离大的在前
                    return distance2 - distance1;
                }
            }
        });

        //初始化
        addInterval(new int[]{-1, N});
    }

    public int seat() {
        int[] longest = treeSet.first();
        int mid;

        if(longest[0] == -1) {
            mid = 0;
        }

        else if(longest[1] == N) {
            mid =  N - 1;
        } else {
            mid = (longest[1] - longest[0]) / 2 + longest[0];
        }

        removeInterval(longest);
        addInterval(new int[] {longest[0], mid});
        addInterval(new int[] {mid, longest[1]});
        return mid;
    }

    public void leave(int p) {
        int[] startInterVal = startMap.get(p);
        int[] endInterval = endMap.get(p);
        removeInterval(startInterVal);
        removeInterval(endInterval);
        addInterval(new int[]{endInterval[0], startInterVal[1]});
    }

    private void addInterval(int[] a) {
        startMap.put(a[0], a);
        endMap.put(a[1], a);
        treeSet.add(a);
    }

    private void removeInterval(int[] a){
        //不存在key也可以，不会抛异常
        startMap.remove(a[0], a);
        endMap.remove(a[1], a);
        treeSet.remove(a);
    }

    /**
     * 比较两个数组谁中点离两边距离大
     *
     * 注意题目中是"与离他最近的人之间的距离达到最大", 不是判断线段最长，即[0, 4]和[0, 5]其实距离是一样大的
     * @param a
     * @return
     */
    private int distance(int[] a) {
        //端点的情况单独考虑
        if(a[0] == -1) {
            return a[1];
        }
        if(a[1] == N) {
            return a[1] - a[0] - 1;
        }
        int da = (a[1] - a[0]) / 2;
        return da;
    }
}

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(N);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */