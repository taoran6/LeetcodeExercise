package com.example.leetcode.kickstart;

import java.io.*;
import java.util.*;

public class BeautyTree {

    public static double beauty(int n, int A, int B, int[] parent) {
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                boolean[] visited = new boolean[n + 1];
                int nextNode = i;
                while (nextNode != 0) {
                    if(!visited[nextNode]) {
                        sum ++;
                        visited[nextNode] = true;
                    }
                    nextNode = getNextNode(parent, nextNode, A);
                }

                nextNode = j;
                while (nextNode != 0) {
                    if(!visited[nextNode]) {
                        sum ++;
                        visited[nextNode] = true;
                    }
                    nextNode = getNextNode(parent, nextNode, B);
                }
            }
        }

        return sum / (double)(n * n);
    }

    private static int getNextNode(int[] parent, int current, int stride) {
        int step = 0;
        while (current != 0 && step < stride) {
            step ++;
            current = parent[current];
        }
        return current;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int t = 1; t <= T; t++) {
            int N = in.nextInt();
            int A = in.nextInt();
            int B = in.nextInt();
            int[] nums = new int[N + 1];
            for (int i = 2; i <= N; i++) {
                nums[i] = in.nextInt();
            }
            double result = beauty(N, A, B, nums);
            System.out.println("Case #" + t + ": " + result);
        }
    }


}