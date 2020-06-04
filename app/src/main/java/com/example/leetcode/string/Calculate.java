package com.example.leetcode.string;

import java.util.Stack;

/**
 * 实现计算器
 */
public class Calculate {
    /**
     * 227. 基本计算器 II
     *
     * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
     *
     * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
     *
     * 示例 1:
     *
     * 输入: "3+2*2"
     * 输出: 7
     * 示例 2:
     *
     * 输入: " 3/2 "
     * 输出: 1
     * 示例 3:
     *
     * 输入: " 3+5 / 2 "
     * 输出: 5
     * 说明：
     *
     * 你可以假设所给定的表达式都是有效的。
     * 请不要使用内置的库函数 eval。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/basic-calculator-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int calculateII(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char sign = '+';
        //记得首尾空格去掉
        s = s.trim();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 空格直接忽略
            if(c == ' ') continue;

            //数字的情况
            if(isNumber(c)) {
                num = 10 * num + (c - '0');
            }
            //符号的情况或者已到字符串最后,这里要注意到字符串最后的情况！
            if(! isNumber(c) || i == s.length() - 1){
                if (sign == '+') {
                    stack.push(num);
                } else if(sign == '-') {
                    stack.push(-num);
                } else if(sign == '*') {
                    //提前计算乘法，除法相同
                    int pre = stack.pop();
                    stack.push(pre * num);
                } else if(sign == '/') {
                    int pre = stack.pop();
                    stack.push(pre / num);
                }
                sign = c;
                //记得清零
                num = 0;
            }

        }

        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }

    /**
     * 判断是否是0-9字符
     * @param c
     * @return
     */
    boolean isNumber(char c) {
        return (c <= '9' && c >= '0');
    }

    boolean isSign(char c) {
        return (c == '+' || c == '-' || c == '*' || c == '/');
    }

    /**
     * 224. 基本计算器
     *
     * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
     *
     * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
     *
     * 示例 1:
     *
     * 输入: "1 + 1"
     * 输出: 2
     * 示例 2:
     *
     * 输入: " 2-1 + 2 "
     * 输出: 3
     * 示例 3:
     *
     * 输入: "(1+(4+5+2)-3)+(6+8)"
     * 输出: 23
     * 说明：
     *
     * 你可以假设所给定的表达式都是有效的。
     * 请不要使用内置的库函数 eval。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/basic-calculator
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 核心是递归
     */

    int index = 0;
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int num = 0;

        while (index < s.length()) {
            char c = s.charAt(index);
            index ++;       //提前更新index

            //数字的情况
            if(isNumber(c)) {
                num = 10 * num + (c - '0');
            }

            //符号的情况或者到最后一个字符
            if(c == '(') {
                //递归计算括号内的值
                num = calculate(s);
            }
            if((!isNumber(c) && c != ' ' && c != '(') || index == s.length()) {      //), +, -, *, /或者到最后一个字符,需要向stack放数字了
                if (sign == '+') {
                    stack.push(num);
                } else if(sign == '-') {
                    stack.push(-num);
                } else if(sign == '*') {
                    //提前计算乘法，除法相同
                    int pre = stack.pop();
                    stack.push(pre * num);
                } else if(sign == '/') {
                    int pre = stack.pop();
                    stack.push(pre / num);
                }
                sign = c;
                //记得清零
                num = 0;
            }

            //遇到')'直接返回计算结果
            if(c == ')') break;

            /**
             * 注意，空格会被忽略，但是最后一个空格会被计算num的值压入堆栈。同理，最后一个字符是')'时在最上层
             * 也会计算num
             *
             * 由于递归，index不能乱，空格不能trim()一起处理，实在不行就先replaceAll好了
             */

        }

        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;

    }
}
