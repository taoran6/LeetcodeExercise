package com.example.leetcode.DP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Plates {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int k = in.nextInt();
            int p = in.nextInt();
            int[][] value = new int[n][k+1];
            for (int j = 0; j < n; j++) {
                for (int l = 1; l <= k; l++) {
                    value[j][l] = in.nextInt();
                }

            }
            dp = new int[n][p+1];
            System.out.println("Case #" + i + ": " + platesFun(value, n, k, p));
        }
    }

    public static int[][] dp;
    public static int platesFun(int[][] stack, int stackNum, int height, int plates) {
        for (int i = 0; i < stackNum; i++) {
            for (int j = 1; j <= height; j++) {
                stack[i][j] += stack[i][j-1];
            }
        }

        return tracBack(stack, 0, plates, stackNum, height);
    }

    private static int tracBack(int[][] stack, int index, int plates, int stackNum, int height) {
        if(plates == 0) return 0;

        else if(dp[index][plates] > 0) return dp[index][plates];


        else if(index == stackNum - 1) {
            if(height >= plates) {
                dp[index][plates] = stack[index][plates];
            }
        } else {
            // 还有多的盘子
            int max = 0;
            for(int i = 0; (i <= plates && i <= height); i++) {
                int iMax = stack[index][i] + tracBack(
                        stack, index + 1, plates - i, stackNum, height);
                max = Math.max(iMax, max);
            }
            dp[index][plates] = max;
        }
        return dp[index][plates];
    }

}
