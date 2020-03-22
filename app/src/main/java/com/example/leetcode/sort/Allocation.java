package com.example.leetcode.sort;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Allocation {

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int b = in.nextInt();
            int[] houses = new int[n];
            for (int j = 0; j < houses.length; j++) {
                houses[j] = in.nextInt();
            }
            System.out.println("Case #" + i + ": " + getAllocationNum(b, houses));
        }
    }
    public static int getAllocationNum(int sum, int[] cost){
        Arrays.sort(cost);
        int now = 0;
        int ans = 0;
        for (int i : cost) {
            if(now + i <= sum) {
                ans ++;
                now += i;
            }else {
                break;
            }
        }
        return ans;
    }
}
