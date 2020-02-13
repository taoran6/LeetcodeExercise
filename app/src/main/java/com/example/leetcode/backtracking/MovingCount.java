package com.example.leetcode.backtracking;

/**
 * 机器人的运动范围
 */
public class MovingCount {
    /**
     * 地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，
     * 但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为
     * 3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
     */
    private int ans = 0;
    private boolean[][] isVisited;
    public int movingCount(int threshold, int rows, int cols) {
        //注意这里需要标志位，否则会重复计算导致堆栈溢出
        isVisited = new boolean[rows][cols];
        count(threshold, 0, 0, rows, cols);
        return ans;
    }

    private void count(int threshold, int x, int y, int row, int cols) {
        if(x >= row || y >= cols || isVisited[x][y]) return;
        isVisited[x][y] = true;
        if(canIn(threshold, x, y)){
            ans++;
            //这里只需要检查右和下的方向就好了
            count(threshold, x+1, y, row, cols);
            count(threshold, x, y+1, row, cols);
        }
    }

    private boolean canIn(int threshold, int x, int y) {
        int count = 0;
        while (x > 0) {
            count += x % 10;
            x = x / 10;
        }
        while (y > 0) {
            count += y % 10;
            y = y / 10;
        }
        return count <= threshold;
    }
}
