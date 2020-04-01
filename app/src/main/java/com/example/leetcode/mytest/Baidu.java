package com.example.leetcode.mytest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Baidu {
    public static void main(String args[]) {
        Scanner cin = new Scanner(System.in);
        while (cin.hasNextInt()) {
            int n = cin.nextInt();
            int m = cin.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = cin.nextInt();
            }
            for(int i = 0; i < n; i++) {
                b[i] = cin.nextInt();
            }
            System.out.println(getMaxCount(n, m, a, b));
        }
    }

    private static long getMaxCount(int n, int m, int[] a, int[] b) {
        long ans = 0;
        List<Mnum> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            list.add(new Mnum(a[i], b[i]));
        }

        for (int i = 0; i < m; i++) {
            Collections.sort(list, new Comparator<Mnum>() {
                @Override
                public int compare(Mnum o1, Mnum o2) {
                    if(o1.a < o2.a) return 1;
                    else if(o1.a == o2.a) return 0;
                    else return -1;
                }
            });
            ans += list.get(0).a;
            list.remove(0);
            for (Mnum mnum : list) {
                mnum.a = mnum.a - mnum.b;
            }
        }
        return ans;
    }

    public static class Mnum {
        long a;
        long b;
        public Mnum(long _a, long _b) {
            a = _a;
            b = _b;
        }
    }
}
