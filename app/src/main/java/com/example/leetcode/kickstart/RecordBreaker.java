package com.example.leetcode.kickstart;

import java.io.*;
import java.util.*;

public class RecordBreaker {
    public static int record(int n, int[] nums) {
        int max = Integer.MIN_VALUE;
        int ans = 0;
        for (int i = 0; i < n; i ++) {
            if(nums[i] > max) {
                max = nums[i];
                if(i == n-1 || nums[i] > nums[i + 1]) {
                    ans ++;
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int T = in.nextInt();
        for (int t = 1; t <= T; t++) {
            int N = in.nextInt();
            int[] nums = new int[N];
            for (int i = 0; i < N; i++) {
                nums[i] = in.nextInt();
            }
            int result = record(N, nums);
            System.out.println("Case #" + t + ": " + result);
        }
    }
}
