package com.example.leetcode.mytest;
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
        C c = new C();
        System.out.println(c.nonStaticStr); //输出：A非静态属性
        System.out.println(c.staticStr);    //输出：A静态属性
        c.staticMethod();////输出：A静态方法。推出静态属性和静态方法可以被继承

        System.out.println("-------------------------------");

        A c1 = new C();
        System.out.println(c1.nonStaticStr);
        System.out.println(c1.staticStr);
        c1.staticMethod();//结果同上，输出的结果都是父类中的非静态属性、静态属性和静态方法,推出静态属性和静态方法可以被继承

        System.out.println("-------------------------------");
        B b = new B();
        System.out.println(b.nonStaticStr); //B改写后的非静态属性
        System.out.println(b.staticStr);    //B改写后的静态属性
        b.staticMethod();   //B改写后的静态方法

        System.out.println("-------------------------------");
        A b1 = new B();
        System.out.println(b1.nonStaticStr);    //A非静态属性。这里说明属性是不存在多态的
        System.out.println(b1.staticStr);       //A静态属性。
        b1.nonStaticMethod();       //B非静态方法。这里体现了多态
        b1.staticMethod();  //A静态方法.说明静态方法不可以被重写，不能实现多态
    }


    public static class A { //父类
        public static String staticStr = "A静态属性";
        public String nonStaticStr = "A非静态属性";

        public static void staticMethod() {
            System.out.println("A静态方法");
        }

        public void nonStaticMethod() {
            System.out.println("A非静态方法");
        }
    }

    public static class B extends A {//子类B
        public static String staticStr = "B改写后的静态属性";
        public String nonStaticStr = "B改写后的非静态属性";

        public static void staticMethod() {
            System.out.println("B改写后的静态方法");
        }
        @Override
        public void nonStaticMethod() {
            System.out.println("B非静态方法");
        }

    }


    public static class C extends A {//子类C继承A中的所有属性和方法
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
