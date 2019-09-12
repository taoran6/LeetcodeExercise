package com.example.leetcode;

import com.example.leetcode.array.SpiralOrder;

/**
 * 测试用
 */
public class MainTest {
    public static void main(String[] args) {
        int[][] x = new int[][]{new int[]{1}, new int[]{5}, new int[]{9}};
        System.out.println(new SpiralOrder().spiralOrder(x));
    }
}
