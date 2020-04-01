package com.example.leetcode.DP;

import java.util.Scanner;

public class LongestSubString {
//    public static void main(String args[]){
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNextLine()) {
//            String s1 = scanner.nextLine();
//            String s2 = scanner.nextLine();
//            System.out.println(getLongestSubString(s1, s2));
//        }
//    }
    public static String getLongestSubString (String s1, String s2) {
        if(s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) return "-1";
        char[] s1Array = s1.toCharArray();
        char[] s2Array = s2.toCharArray();

        int max = 0;
        int index = -1;
        int[][] dp = new int[s1Array.length + 1][s2Array.length + 1];
        for (int i = 0; i < s1Array.length; i++) {
            for (int j = 0; j < s2Array.length; j++) {
                if(s1Array[i] == s2Array[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    if(dp[i + 1][j + 1] > max) {
                        max = dp[i + 1][j + 1];
                        index = j;
                    }
                }
            }
        }
        if(index == -1) return "-1";
        else return s2.substring(index - max + 1, index + 1);
    }
}
