package com.example.leetcode.mytest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class AliBaBa {
    /**
     * 一个n x m的矩阵，每列每行都是等差数列，公差为整数。
     * 输入n x m个数，如果为0表示不知道，否则知道。
     * q个查询，特定位置（下标1开始）的值是否可确定。
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();
        int[][] matrix = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                matrix[i][j] = sc.nextInt();
            }
        }
        getMatrix2(matrix);
        for (int i = 0; i < q; i++) {
            int x = sc.nextInt() - 1;
            int y = sc.nextInt() - 1;
            if(matrix[x][y] != 0) {     //呜呜呜竟然把判断条件写反了（matrix[x][y] != 0），我怎么这么不小心
                System.out.println(matrix[x][y]);
            }else {
                System.out.println("Unknown");
            }
        }

    }

    public static int[][] getMatrix2(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        // 记录该行或列是否可以被全部推出
        boolean[] row_OK = new boolean[n];
        boolean[] col_OK = new boolean[m];
        // 记录该行或列只有一个已知的该数的下标
        int[] rowIndex = new int[n];
        int[] colIndex = new int[m];
        Arrays.fill(rowIndex, -1);
        Arrays.fill(colIndex, -1);

        for(int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(matrix[i][j] != 0) {
                    if(col_OK[j] && row_OK[i]) continue;    //直接返回节省时间

                    if(!col_OK[j]) {    //该列还没有被推导出来
                        if(colIndex[j] == -1) {
                            colIndex[j] = i;    // 更新该列已知的第一个值
                        } else {
                            col_OK[j] = true;
                            //获得等差的差值，更新该列
                            int gap = (matrix[i][j] - matrix[colIndex[j]][j]) / (i - colIndex[j]);
                            for (int r = i - 1; r >= 0; r--) {
                                matrix[r][j] = matrix[r+1][j] - gap;
                            }
                            for (int r = i + 1; r < n; r++) {
                                matrix[r][j] = matrix[r-1][j] + gap;
                            }
                        }
                    }

                    if(!row_OK[i]) {    //该行还没有被推导出来，思路和列的推导一样
                        if(rowIndex[i] == -1) {
                            rowIndex[i] = j;
                        } else {
                            row_OK[i] = true;
                            int gap = (matrix[i][j] - matrix[i][rowIndex[i]]) / (j - rowIndex[i]);
                            for (int r = j - 1; j >=0; j--) {
                                matrix[i][r] = matrix[i][r+1] - gap;
                            }
                            for(int r = j + 1; j < m; j++) {
                                matrix[i][r] = matrix[i][r-1] + gap;
                            }
                        }
                    }
                }
            }
        }
        return matrix;
    }

    public static int[][] getMatrix(int[][] matrix, int m, int n) {
        for (int i = 0; i < m; i++) {
            int count = 0;
            int y1 = 0, y2 = 0;
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] != 0) {
                    count ++;
                    if(count == 1) {
                        y1 = j;
                    }
                    if(count == 2) {
                        y2 = j;
                        break;
                    }
                }
            }
            if(count == 2) {
                int del = (matrix[i][y2] - matrix[i][y1])/(y2 - y1);
                for(int j = 0; j < n; j++) {
                    matrix[i][j] = matrix[i][y1] + (j - y1) * del;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int count = 0;
            int x1 = 0, x2 = 0;
            for(int j = 0; j < m; j++) {
                if(matrix[j][i] != 0) {
                    count ++;
                    if(count == 1) {
                        x1 = j;
                    }
                    if(count == 2) {
                        x2 = j;
                        break;
                    }
                }
            }
            if(count == 2) {
                int del = (matrix[x2][i] - matrix[x1][i])/(x2 - x1);
                for(int j = 0; j < n; j++) {
                    matrix[j][i] = matrix[x1][i] + (j - x1) * del;
                }
            }
        }
        return matrix;
    }


    public static void main0(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[][] matrix = new long[3][n];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < n; j++){
                matrix[i][j] = sc.nextLong();
            }
        }
        System.out.println(minSum(matrix, n));
    }


    /**
     * 作者：HailHYDRA!
     * 链接：https://www.nowcoder.com/discuss/392312?type=all&order=time&pos=&page=1
     * 来源：牛客网
     *
     * 给3xn的矩阵，每列选择其中的一个数, 形成最后的数组。
     * 使得最后的
     * sum(b[i+1] - b[i])
     * 最小。
     * 输出最小的这个值。
     * @param matrix
     * @param n
     * @return
     */
    public static long minSum(long[][] matrix, int n) {
        //呜呜呜，没考虑好大数据，应该用long类型的

        //动态规划，dp[i]表示以当前第i行的这个数结尾的sum的最小值，这里做了空间优化
        long[] dp = new long[3];
        for (int i = 1; i < n; i ++) {
            long[] newDp = new long[3];
            for (int j = 0; j < 3; j++) {
                long min = Long.MAX_VALUE;
                for(int k = 0; k < 3; k++) {
                    long abs = Math.abs(matrix[j][i] - matrix[k][i-1]);
                    min = Math.min(min, dp[k] + abs);
                }
                newDp[j] = min;
            }
            dp = newDp;
        }

        long ans = Long.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            ans = Math.min(dp[i], ans);
        }
        return ans;
    }

    static class Migong{
        static class Node{
            int row;
            int col;
            int jumpCount;
            int pathLen;
            Node (int row, int col, int jumpCount, int pathLen){
                this.row = row;
                this.col = col;
                this.jumpCount = jumpCount;
                this.pathLen = pathLen;
            }
        }
        public static int shortestPath(char[][] grid, int k){
            int m = grid.length;
            int n = grid[0].length;
            LinkedList<Node> queue = new LinkedList<>();
            // get start
            boolean found = false;
            int startX = 0;
            int startY = 0;
            for(int i=0;i<m;i++) {
                for(int j=0;j<n;j++) {
                    if(grid[i][j] == 'S') {
                        startX = i;
                        startY = j;
                        found = true;
                        break;
                    }
                }
                if(found) break;
            }

            Node rootNode = new Node(startX,startY,0,0);
            queue.add(rootNode);
            Node[][] visited = new Node[m][n];
            visited[0][0] = rootNode;

            int dx[] = new int[]{-1,1,0,0};
            int dy[] = new int[]{0,0,1,-1};

            while(!queue.isEmpty()){
                int count = queue.size();
                while(count-- > 0){
                    Node tmp = queue.poll();
                    int row = tmp.row;
                    int col = tmp.col;
                    int jumpCount = tmp.jumpCount;
                    int pathLen = tmp.pathLen;
                    if(grid[row][col] == 'E'){
                        return pathLen;
                    }
                    //four directions
                    for(int i = 0;i < 4;i++){
                        int newRow = row + dx[i];
                        int newCol = col + dy[i];
                        if(newRow<0 || newRow>=m || newCol<0 || newCol>=n ){
                            continue;
                        }
                        if(jumpCount > k){
                            continue;
                        }
                        boolean addNode = false;
                        int newPathLen = -1;
                        if(grid[row][col] != '#') {
                            newPathLen = pathLen+1;
                            addNode = true;
                        }
                        Node newNode = new Node(newRow, newCol, jumpCount,newPathLen);
                        if(addNode) queue.add(newNode);
                        if(visited[newRow][newCol] == null){
                            visited[newRow][newCol] = newNode;
                        }
                    }
                    // jump
                    int newRow = m - 1-row;
                    int newCol = n - 1-col;
                    if(newRow<0 || newRow>=m || newCol<0 || newCol>=n || jumpCount>5){
                        ;
                    }
                    else {
                        boolean addNode = false;
                        int newPathLen = -1;
                        if(grid[row][col] != '#') {
                            newPathLen = pathLen+1;
                            addNode = true;
                        }
                        Node newNode = new Node(newRow, newCol, jumpCount+1,newPathLen);
                        if(addNode) queue.add(newNode);
                        if(visited[newRow][newCol] == null|| jumpCount+1<visited[newRow][newCol].jumpCount){
                            visited[newRow][newCol] = newNode;
                        }
                    }
                }

            }
            return -1;
        }

        public static void main(String[] args){
            char[][] grid = {{'#', 'S', '.', '.','.'},
                    {'E', '#', '#', '.','.'},
                    {'#', '#', '#', '.','.'},
                    {'#', '#', '#', '.','.'}};
            int result = shortestPath(grid,5);
            System.out.println(result);
        }
    }
}
