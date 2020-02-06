package com.example.leetcode;

import com.example.leetcode.DP.ClimbStairs;
import com.example.leetcode.DP.NumDecodings;
import com.example.leetcode.array.FindKthLargest;
import com.example.leetcode.bit.ReverseBits;
import com.example.leetcode.graph.CalcEquation;
import com.example.leetcode.sort.ArrayPairSum;
import com.example.leetcode.stack.IsPopOrder;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试用
 */
public class MainTest {
    public static void main(String[] args) {
        //这里写测试用例
        List<List<String>> equ = new ArrayList<>();
        equ.add(new ArrayList<String>(){{add("a"); add("b");}});
        equ.add(new ArrayList<String>(){{add("b"); add("c");}});
        equ.add(new ArrayList<String>(){{add("e"); add("x");}});


        List<List<String>> quer = new ArrayList<>();
        quer.add(new ArrayList<String>(){{add("a"); add("c");}});
        quer.add(new ArrayList<String>(){{add("a"); add("e");}});
        quer.add(new ArrayList<String>(){{add("x"); add("x");}});


        boolean out = new IsPopOrder().IsPopOrder(new int[]{1,2,3,4,5}, new int[] {4,3,5,1,2});
        System.out.println(out);
    }
}
