package com.example.leetcode.string;

/**
 * 表示数值的字符串
 */
public class IsNumeric {
    /**
     * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100","5e2","-123","3.1416"
     * 和"-1E-16"都表示数值。 但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
     */
    public boolean isNumeric(char[] str) {
        boolean hasE = false;
        boolean isDecimal = false;
        boolean hasSign = false;
        int index = 0;
        while (index < str.length) {
            if(str[index] == '+' || str[index] == '-'){
                if(hasSign) return false;
                hasSign = true;
            } else if(str[index] >= '0' && str[index] <= '9'){
                //出现过数字以后不能出现符号
                hasSign = true;
            } else if(str[index] == '.') {
                // E 后面不能有小数点
                if(isDecimal || hasE) return false;
                hasSign = true;
                isDecimal = true;
            } else if(str[index] == 'E' || str[index] == 'e') {
                if(hasE || index == str.length - 1) return false;
                //符号和小数点可以再出现了
                hasSign = false;
                isDecimal = false;
                hasE = true;
            } else return false;

            index ++;
        }
        return true;
    }

    /**
     * 方法二：正则表达式
     * 链接：https://www.nowcoder.com/questionTerminal/6f8c901d091949a5837e24bb82a731f2?f=discussion
     * 来源：牛客网
     */
    public boolean isNumeric2(char[] str) {
        String string = String.valueOf(str);
        return string.matches("[\\+\\-]?\\d*(\\.\\d+)?([eE][\\+\\-]?\\d+)?");

    }

    /**
     * 正则表达式匹配
     *
     * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可
     * 以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和
     * "ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
     */
    public boolean match(char[] str, char[] pattern)
    {
        if(str == null || pattern == null) {
            return false;
        }
        return matchCore(str, 0, pattern, 0);
    }

    private boolean matchCore(char[] str, int strIndex, char[] patten, int paIndex){
        if(strIndex == str.length && paIndex == patten.length) return true;

        //patten先到尾，匹配失败
        if(strIndex < str.length && paIndex == patten.length) return false;

        //模式第2个是*，且字符串第1个跟模式第1个匹配,分2种匹配模式；如不匹配，模式后移2位
        if(paIndex + 1 < patten.length && patten[paIndex + 1] == '*') {
            if(strIndex != str.length &&
                    (str[strIndex] == patten[paIndex] || patten[paIndex] == '.')){
                return matchCore(str, strIndex, patten, paIndex + 2)// 不匹配任何*
                        || matchCore(str, strIndex + 1, patten, paIndex);//继续匹配*
            } else {
                return matchCore(str, strIndex, patten, paIndex + 2);
            }
        }

        //模式第2个不是*，且字符串第1个跟模式第1个匹配，则都后移1位
        if((strIndex != str.length) && (str[strIndex] == patten[paIndex] || patten[paIndex] == '.')) {
            return matchCore(str, strIndex + 1, patten, paIndex + 1);
        }
        return false;
    }

}
