package com.hannah.study.algorithm;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Stack;

/**
 * 四则运算：基于双栈实现【操作数+运算符】
 */
public class Arithmetic {

    /**
     * 执行四则运算
     *
     * @param expression
     * @return
     */
    public static BigDecimal execute(String expression) {
        // 数字栈
        Stack<BigDecimal> numberStack = new Stack<BigDecimal>();
        // 运算符栈：+ - * / ( )
        Stack<Character> operatorStack = new Stack<Character>();
        // 记录数字的起始位置
        int start = 0;
        int end = 0;
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (isDigit(c) || c == POINT) {
                if (start == end) {
                    start = i;
                    end = i + 1;
                } else
                    end ++;
            } else {
                // 截取数字，压入数字栈
                if (start != end) {
                    BigDecimal d = new BigDecimal(expression.substring(start, end));
                    numberStack.push(d);
                    start = end;
                }
                if (isOperator(c)) {
                    if (operatorStack.empty()) {
                        operatorStack.push(c);
                        continue;
                    }
                    char priorOperator = operatorStack.peek();
                    // 如果当前运算符优先级等于小于栈顶的运算符优先级，那么取出数字栈栈顶的两个数字运算
                    if (getOperatorLevel(c) <= getOperatorLevel(priorOperator)) {
                        BigDecimal d2 = numberStack.pop();
                        BigDecimal d1 = numberStack.pop();
                        char operator = operatorStack.pop();
                        // 新的运算结果压入数字栈
                        numberStack.push(calculate(d1, d2, operator));
                        operatorStack.push(c);
                    } else
                        operatorStack.push(c);
                } else if (c == '(')
                    operatorStack.push(c);
                else if (c == ')') {
                    char operator;
                    // 循环运算直到找到 (
                    while ((operator = operatorStack.pop()) != '(') {
                        BigDecimal d2 = numberStack.pop();
                        BigDecimal d1 = numberStack.pop();
                        numberStack.push(calculate(d1, d2, operator));
                    }
                } else if (c == ' ')
                    continue;
                else
                    throw new RuntimeException("char '" + c + "' is illegal!");
            }
        }
        // 最后一个数字
        if (start != end) {
            BigDecimal d = new BigDecimal(expression.substring(start, end));
            numberStack.push(d);
        }
        // 运行最后的结果
        char operator;
        while (operatorStack.size() > 0) {
            BigDecimal d2 = numberStack.pop();
            BigDecimal d1 = numberStack.pop();
            operator = operatorStack.pop();
            numberStack.push(calculate(d1, d2, operator));
        }
        // 取出最后一个数字就是最终值
        if (numberStack.size() == 1 && operatorStack.size() == 0)
            return numberStack.peek();
        else
            throw new RuntimeException("arithmetic is illegal!\n" + "numberStack is: " + numberStack +
                    "\toperatorStack is: " + operatorStack);
    }

    private static final char POINT = '.';

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isOperator(char c) {
        switch (c) {
            case '+':
                return true;
            case '-':
                return true;
            case '*':
                return true;
            case '/':
                return true;
            default:
                return false;
        }
    }

    /**
     * 获取运算符优先级
     *
     * @param operator
     * @return
     */
    private static int getOperatorLevel(char operator) {
        switch (operator) {
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 2;
            default:
                return 0;
        }
    }

    /**
     * 计算两个数运算结果
     *
     * @param d1
     * @param d2
     * @param operator
     * @return
     */
    public static BigDecimal calculate(BigDecimal d1, BigDecimal d2, char operator) {
        switch (operator) {
            case '+':
                return d1.add(d2);
            case '-':
                return d1.subtract(d2);
            case '*':
                return d1.multiply(d2);
            case '/':
                return d1.divide(d2, 10, BigDecimal.ROUND_HALF_EVEN);    // 最大精度为10
            default:
                return BigDecimal.ZERO;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            BigDecimal result = Arithmetic.execute(sc.next());
            System.out.println(" = " + result);
        }
    }

}
