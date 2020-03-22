package com.example.leetcode;
import java.util.*;
public class Meituan
{
    public static void main(String args[])
    {
//        Scanner cin = new Scanner(System.in);
//        while(cin.hasNextLine())
//        {
//            String ns = cin.nextLine();
//            int n = Integer.valueOf(ns);
//            String a = cin.nextLine();
//            String b = cin.nextLine();
//            char[][] map = new char[2][n];
//            for(int i = 0; i < n; i++) map[0][i] = a.charAt(i);
//            for(int i = 0; i < n; i++) map[1][i] = b.charAt(i);
//            int ans = getNum(map, n);
//            System.out.println(ans);
//        }
        handleInputMaxNum();
    }

    private static int getNum(char[][] map, int n) {
        if(n == 0) return -1;
        int[][] dp = new int[2][n];
        dp[0][0] = 1;
        if(map[0][0] == 'X') return -1;
        for(int j = 0; j < n; j++) {
            for(int i = 0; i < 2; i++) {
                if(map[i][j] == 'X') dp[i][j] = -1;
                else {
                    if(j-1 >= 0 && dp[i][j-1] != -1) dp[i][j] += dp[i][j-1];
                    if(i-1 >= 0 && j-1 >= 0 && dp[i-1][j-1] != -1) dp[i][j] += dp[i-1][j-1];
                    if(i+1 < 2 && j-1 >= 0 && dp[i+1][j-1] != -1) dp[i][j] += dp[i+1][j-1];
                }
                if(dp[i][j] == 0) dp[i][j] = -1;
            }
        }

        return dp[1][n-1];
    }

    private static void handleInputMaxNum() {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNextInt()) {
            int n = cin.nextInt();
            int x = cin.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = cin.nextInt();
            }
            System.out.println(getMaxNum(x, nums));
        }
    }

    private static int getMaxNum(int x, int[] nums) {
        if(nums.length == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int k : nums) {
            map.put(k, map.getOrDefault(k, 0) + 1);
        }
        int maxKey = 0;
        int maxValue = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int tmp = entry.getValue();
            for (int i : nums) {
                if(i != entry.getKey() && (i | x) == entry.getKey()) tmp ++;
            }
            if(tmp > maxValue) maxValue = tmp;
        }

        return maxValue;
    }
}
