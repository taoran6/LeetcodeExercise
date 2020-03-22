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

    /**
     * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
     *
     * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     *
     * 示例:
     *
     * X X X X
     * X O O X
     * X X O X
     * X O X X
     * 运行你的函数后，矩阵变为：
     *
     * X X X X
     * X X X X
     * X X X X
     * X O X X
     * 解释:
     *
     * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界
     * 上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/surrounded-regions
     *
     * 这题提示很给力，用深度优先搜索就好
     */
    public void solve(char[][] board) {
        if( board.length == 0 || board[0].length == 0) return;
        //先把边界上及和边界相邻的点填充为'*'
        for (int i = 0; i < board.length; i++) {
            if(board[i][0] == 'O') dfs(board, i, 0);
            if(board[i][board[0].length - 1] == 'O') dfs(board, i, board[0].length - 1);
        }

        for(int i = 0; i < board[0].length; i++) {
            if(board[0][i] == 'O') dfs(board, 0, i);
            if(board[board.length - 1][i] == 'O') dfs(board, board.length - 1, i);
        }

        //带'*'的填充为'O'，其他的都是'X'
        for(int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] == 'O') board[i][j] = 'X';
                else if(board[i][j] == '*') board[i][j] = 'O';
            }
    }

    private void dfs(char[][] board, int x, int y) {
        board[x][y] = '*';
        if(x > 0 && board[x-1][y] == 'O') dfs(board, x-1, y);
        if(x + 1 < board.length && board[x+1][y] == 'O') dfs(board, x+1, y);
        if(y > 0 && board[x][y-1] == 'O') dfs(board, x, y-1);
        if(y + 1 < board[0].length && board[x][y+1] == 'O') dfs(board, x, y+1);
    }

    /**
     * 695. 岛屿的最大面积
     *
     * 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构
     * 成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
     *
     * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
     *
     * 示例 1:
     *
     * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
     *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
     *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
     *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
     *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
     *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
     *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
     *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
     * 对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
     *
     * 示例 2:
     *
     * [[0,0,0,0,0,0,0,0]]
     * 对于上面这个给定的矩阵, 返回 0。
     *
     * 注意: 给定的矩阵grid 的长度和宽度都不超过 50。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/max-area-of-island
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    private int count = 0;
    public int maxAreaOfIsland(int[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) return 0;

        int max = 0;
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] != 1) continue;
                count = 0;
                getArea(grid, i, j);
                max = count > max ? count : max;
            }
        }

        return max;
    }

    private void getArea(int[][] grid, int x, int y) {
        if(grid[x][y] != 1) return;
        count ++;
        grid[x][y] = -1;
        if(x - 1 > 0) getArea(grid, x-1, y);
        if(y - 1 > 0) getArea(grid, x, y-1);
        if(x + 1 < grid.length) getArea(grid,x + 1, y);
        if(y + 1 < grid[0].length) getArea(grid, x, y + 1);
    }
}
