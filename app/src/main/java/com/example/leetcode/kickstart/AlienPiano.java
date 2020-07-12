package com.example.leetcode.kickstart;
import java.io.*;
import java.util.*;


public class AlienPiano {
    public static int countTimes(int n, int nums[]) {
        if(n <= 4) return 0;


        //初始化状态
        boolean flag = false;
        int asc = 0;
        int dsc = 0;
        int ans = 0;

        for (int i = 1; i < n; i++) {
            if(!flag) {
                if(nums[i] < nums[i-1]) dsc = 1;
                else if(nums[i] > nums[i-1]) asc = 1;
                flag = true;
            } else if(asc != 0) {
                if(nums[i] < nums[i-1]) {
                    flag = true; asc = 0; dsc = 1;
                } else if(nums[i] > nums[i-1]) {
                    asc ++;
                    if(asc == 4) {
                        flag = true; asc = 0; dsc = 0;
                        ans ++;
                    }
                }
            } else {
                if(nums[i] > nums[i-1]) {
                    flag = true; asc = 1; dsc = 0;
                } else if(nums[i] < nums[i-1]) {
                    dsc ++;
                    if(dsc == 4) {
                        flag = true; asc = 0; dsc = 0;
                        ans ++;
                    }
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
            int result = countTimes(N, nums);
            System.out.println("Case #" + t + ": " + result);
        }
    }
}
