package com.example.leetcode.stack;

import java.util.Stack;

/**
 * 394. 字符串解码
 */
public class DecodeString {
    /**
     * 给定一个经过编码的字符串，返回它解码后的字符串。
     *
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     *
     * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     *
     * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
     *
     * 示例:
     *
     * s = "3[a]2[bc]", 返回 "aaabcbc".
     * s = "3[a2[c]]", 返回 "accaccacc".
     * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/decode-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     *
     * 方法一：使用辅助栈
     */
    public String decodeString(String s) {
        Stack<Integer> intStack = new Stack<>();
        Stack<String> strStack = new Stack<>();
        StringBuilder builder = new StringBuilder();

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] >= '0' && chars[i] <= '9') {
                //获取重复次数，这里需要考虑10、100这样的多位数的情况
                if(i >0 && chars[i-1] >= '0' && chars[i-1] <= '9') {
                    intStack.push(intStack.pop() * 10 + chars[i] - '0');
                }else {
                    intStack.push(chars[i] - '0');
                }
                continue;
            }
            if(chars[i] == ']') {
                /*需要计算[]内重复字符串解码后压回栈内*/
                builder.delete(0, builder.length());
                while (! "[".equals(strStack.peek())) {
                    builder.insert(0, strStack.pop());
                }
                strStack.pop();
                strStack.push(repeatString(intStack.pop(), builder.toString()));
            } else {
                strStack.push(chars[i] + "");
            }
        }

        //最后栈内保留的是已解码好的字符串，串连起来即可
        builder.delete(0, builder.length());
        while (!strStack.empty()) {
            builder.insert(0, strStack.pop());
        }
        return builder.toString();
    }

    /**
     * 将str重复count次
     */
    private String repeatString(int count, String str) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i ++) {
            builder.append(str);
        }
        return builder.toString();
    }

    /**
     * 方法二：使用递归
     */
}
