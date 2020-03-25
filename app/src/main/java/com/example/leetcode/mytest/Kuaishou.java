package com.example.leetcode.mytest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Kuaishou {
    /**
     * 获取队中从前到后每个人与前方身高高于自己的人的最短距离
     * @param height int整型一维数组 队中从前到后每个人与前方身高高于自己的人的最短距离
     * @return int整型一维数组
     */
    public int[] DistanceToHigher (int[] height) {
        Stack<Integer> stackHeight = new Stack<>();
        Stack<Integer> stackInteger = new Stack<>();
        int[] ans = new int[height.length];

        for (int i = 0; i < height.length; i++) {
            while (!stackHeight.isEmpty() && stackHeight.peek() <= height[i]) {
                stackHeight.pop();
                stackInteger.pop();
            }
            if(stackHeight.isEmpty()) {
                ans[i] = 0;
                stackHeight.push(height[i]);
                stackInteger.push(i);
            } else {
                ans[i] = i - stackInteger.peek();
                stackHeight.push(height[i]);
                stackInteger.push(i);
            }

        }
        return ans;
    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String ns = sc.nextLine();
            String[] strings = ns.split(" ");
            int[] nums = new int[strings.length];
            for (int i = 0; i < strings.length; i++) {
                nums[i] = Integer.valueOf(strings[i]);
            }
            List<Integer> ans = findNums(nums);
            for (int i : ans) {
                System.out.print(i + " ");
            }
        }
    }

    public static List<Integer> findNums (int[] nums) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        if(nums.length <= 1) {
            ans.add(-1);
            return ans;
        }
        stack.push(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if(stack.peek() > nums[i]) {
                int top = stack.pop();
                if(stack.isEmpty()) {
                    ans.add(i);
                    stack.push(nums[i]);
                } else if(stack.peek() <= nums[i]) {
                    ans.add(i);
                    stack.pop();
                    stack.push(nums[i]);
                }
                stack.push(top);
            } else {
                stack.push(nums[i]);
            }
        }
        if(ans.isEmpty()) ans.add(-1);
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String ns = sc.nextLine();
            String[] strings = ns.split(",");
            Value[] values = sortPhoneNumber(strings);
            StringBuilder builder = new StringBuilder();
            for (Value value : values) {
                if(value.continus != 0) builder.append(strings[value.index]).append(",");
                else break;
            }
            if(builder.length() == 0) System.out.println("null");
            else {
                builder.deleteCharAt(builder.length() - 1);
                System.out.println(builder.toString());
            }
        }
    }

    static class Value{
        int index;
        int continus;
        int isBao;
        public Value(int _index) {
            index = _index;
        }
    }

    public static Value[] sortPhoneNumber(String[] phoneNumber) {
        Value[] phoneValues = new Value[phoneNumber.length];
        for (int i = 0; i < phoneNumber.length; i++) {
            Value value = new Value(i);
            char[] charPhone = phoneNumber[i].toCharArray();
            for (int k = 4; k < 10; k++) {
                if(charPhone[k] == charPhone[k - 1] && charPhone[k] == charPhone[k + 1]) {
                    int maxCont = 3;
                    for(int j = k + 2; j < 11; j++) {
                        if(charPhone[j] == charPhone[j-1]) maxCont ++;
                        else break;
                    }
                    value.continus = Math.max(value.continus, maxCont);
                    value.isBao = 1;
                }
            }
            for (int k = 4; k < 10; k++) {
                if(charPhone[k] == charPhone[k - 1] + 1 && charPhone[k] == charPhone[k + 1] - 1) {
                    int maxCont = 3;
                    for(int j = k + 2; j < 11; j++) {
                        if(charPhone[j] == charPhone[j-1] + 1) maxCont ++;
                        else break;
                    }
                    if(maxCont > value.continus) {
                        value.continus = maxCont;
                        value.isBao = 0;
                    }
                }
            }

            for (int k = 4; k < 10; k++) {
                if(charPhone[k] == charPhone[k - 1] - 1 && charPhone[k] == charPhone[k + 1] + 1) {
                    int maxCont = 3;
                    for(int j = k + 2; j < 11; j++) {
                        if(charPhone[j] == charPhone[j-1] - 1) maxCont ++;
                        else break;
                    }
                    if(maxCont > value.continus) {
                        value.continus = maxCont;
                        value.isBao = 0;
                    }
                }
            }

            phoneValues[i] = value;
        }
        Arrays.sort(phoneValues, new Comparator<Value>() {
            @Override
            public int compare(Value o1, Value o2) {
                if(o1.continus != o2.continus) {
                    return o2.continus - o1.continus;
                } else {
                    return o2.isBao - o1.isBao;
                }
            }
        });
        return phoneValues;
    }
}
