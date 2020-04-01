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
     *
     * 方法一：回溯
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

    /**
     * 方法二：动态规划
     */
    public boolean isMatch(String text, String pattern) {
        if(text == null || pattern == null) return false;
        //dp[i][j]保存text的前i个字符和pattern的前j个字符是否匹配
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        char[] textChar = text.toCharArray();
        char[] patternChar = pattern.toCharArray();
        //两个空字符串一定匹配
        dp[0][0] = true;
        //这里必须初始化第一行也就是dp[0][j];因为""和"c*"匹配
        for(int j = 2; j <= patternChar.length; j++) {
            dp[0][j] = patternChar[j-1] == '*' && dp[0][j-2];
        }
        for (int i = 0; i < text.length(); i++){
            for (int j = 0; j < pattern.length(); j++) {
                if(textChar[i] == patternChar[j] || patternChar[j] == '.') {
                    //没有'*',直接匹配的情况
                    dp[i + 1][j  + 1] = dp[i][j];
                }else if(patternChar[j] == '*'){    //模式最后一个是'*'
                    if(j >= 1 && patternChar[j - 1] == textChar[i] || patternChar[j - 1] == '.') {
                        // '*'前面字母匹配
                        dp[i + 1][j + 1] = dp[i + 1][j - 1] || dp[i + 1][j] || dp[i][j + 1];
                        //分别对应a*不匹配，a*匹配一个，a*匹配多个
                    } else if (j >= 1){
                        // '*'前面字母不匹配
                        dp[i + 1][j + 1] = dp[i + 1][j - 1];
                    }
                }
            }
        }

        return dp[text.length()][pattern.length()];
    }

    /**
     * 44. 通配符匹配
     *
     * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
     *
     * '?' 可以匹配任何单个字符。
     * '*' 可以匹配任意字符串（包括空字符串）。
     * 两个字符串完全匹配才算匹配成功。
     *
     * 说明:
     *
     * s 可能为空，且只包含从 a-z 的小写字母。
     * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
     * 示例 1:
     *
     * 输入:
     * s = "aa"
     * p = "a"
     * 输出: false
     * 解释: "a" 无法匹配 "aa" 整个字符串。
     * 示例 2:
     *
     * 输入:
     * s = "aa"
     * p = "*"
     * 输出: true
     * 解释: '*' 可以匹配任意字符串。
     * 示例 3:
     *
     * 输入:
     * s = "cb"
     * p = "?a"
     * 输出: false
     * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
     * 示例 4:
     *
     * 输入:
     * s = "adceb"
     * p = "*a*b"
     * 输出: true
     * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
     * 示例 5:
     *
     * 输入:
     * s = "acdcb"
     * p = "a*c?b"
     * 输入: false
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/wildcard-matching
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean isMatch2(String s, String p) {
        if(s == null || p == null) return false;

        //dp[i][j]表示s的前i-1个字符和p的前j-1个字符是否匹配
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        char[] sArray = s.toCharArray();
        char[] pArray = p.toCharArray();

        dp[0][0] = true;
        //对于空字符串的初始化
        for (int i = 0; i < pArray.length; i++) {
            if(pArray[i] == '*') dp[0][i+1] = true;
            else break;
        }


        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {
                if(sArray[i] == pArray[j] || pArray[j] == '?') {
                    //当前字符匹配
                    dp[i+1][j+1] = dp[i][j];
                } else if(pArray[j] == '*') {
                    //dp[i][j+1]表示*匹配当前字符，dp[i+1][j]表示*匹配空字符串
                    dp[i+1][j+1] = dp[i][j+1] || dp[i+1][j];
                }
            }
        }

        return dp[s.length()][p.length()];
    }
}
