package com.example.leetcode.graph;

/**
 * 岛屿数量
 */
public class NumIslands {
    /**
     * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向
     * 或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。
     *
     * 示例 1:
     *
     * 输入:
     * 11110
     * 11010
     * 11000
     * 00000
     *
     * 输出: 1
     * 示例 2:
     *
     * 输入:
     * 11000
     * 11000
     * 00100
     * 00011
     *
     * 输出: 3
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-islands
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int ans = 0;
        int m = grid.length;
        if(m == 0) return 0;
        int n = grid[0].length;
        if(n == 0) return 0;

        boolean[][] isVisited = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '1' && !isVisited[i][j]) {
                    ans ++;
                    markGrid(i, j, grid, isVisited);
                }
            }
        }

        return ans;
    }

    /**
     * 深度优先搜索
     *
     * @param i
     * @param j
     * @param grid
     * @param isVisited
     */
    private void markGrid(int i, int j, char[][] grid, boolean[][] isVisited) {
        if(grid[i][j] == '1' && !isVisited[i][j]) {
            isVisited[i][j] = true;
            if(i > 0) markGrid(i - 1, j, grid, isVisited);
            if(j > 0) markGrid(i, j -1, grid, isVisited);
            if(i < grid.length - 1) markGrid(i + 1, j, grid, isVisited);
            if(j < grid[0].length - 1) markGrid(i, j+ 1, grid, isVisited);
        }
    }

    /**
     * 方法二：优化，可以省去 boolean[][] isVisited 这个空间，用grid[i][j] = '0'
     */
    public int numIslands2(char[][] grid) {
        int ans = 0;
        int m = grid.length;
        if(m == 0) return 0;
        int n = grid[0].length;
        if(n == 0) return 0;

        boolean[][] isVisited = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '1') {
                    ans ++;
                    markGrid2(i, j, grid);
                }
            }
        }

        return ans;
    }

    private void markGrid2(int i, int j, char[][] grid) {
        if(grid[i][j] == '1') {
            grid[i][j] = '0';
            if(i > 0) markGrid2(i - 1, j, grid);
            if(j > 0) markGrid2(i, j -1, grid);
            if(i < grid.length - 1) markGrid2(i + 1, j, grid);
            if(j < grid[0].length - 1) markGrid2(i, j+ 1, grid);
        }
    }
}
