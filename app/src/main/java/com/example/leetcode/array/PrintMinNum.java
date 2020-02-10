package com.example.leetcode.array;

import java.util.Arrays;
import java.util.Comparator;

public class PrintMinNum {
    /**
     * 题目描述
     *
     * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组
     * {3，32，321}，则打印出这三个数字能排成的最小数字为321323。
     *
     * https://www.nowcoder.com/practice/8fecd3f8ba334add803bf2a06af1b993?tpId=13&tqId=11185&tPage=2&rp=2&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
     * @param numbers
     * @return
     */
    public String printNumbers(int[] numbers) {
        String[] sArray = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            sArray[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(sArray, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                char[] chars1 = o1.toCharArray();
                char[] chars2 = o2.toCharArray();
                int length = Math.max(chars1.length, chars2.length);
                for (int i = 0; i < length; i++) {
                    char a = i >= chars1.length ? chars1[chars1.length - 1] : chars1[i];
                    char b = i >= chars2.length ? chars2[chars2.length - 1] : chars2[i];
                    if(a != b) return a - b;
                }
                return 0;
            }
        });

        StringBuilder builder = new StringBuilder();
        for (String str : sArray) {
            builder.append(str);
        }
        return builder.toString();
    }


    /**
     * 方法二：实际上只需要比较连接后的字符串就好了
     */
    public String printNumbers2(int[] numbers) {
        String[] sArray = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            sArray[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(sArray, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String s1 = o1 + o2;
                String s2 = o2 + o1;
                return s1.compareTo(s2);
            }
        });

        StringBuilder builder = new StringBuilder();
        for (String str : sArray) {
            builder.append(str);
        }
        return builder.toString();
    }



}
