package com.hannah.educate.Grade1;

import com.hannah.study.algorithm.Arithmetic;

import java.math.BigDecimal;
import java.util.Random;

/**
 * 四则运算试题
 */
public class ArithmeticQuestion {

    private Random random = new Random(System.currentTimeMillis());
    private int expCount;
    private int resultLimit;

    public ArithmeticQuestion(int expCount, int resultLimit) {
        this.expCount = expCount;
        this.resultLimit = resultLimit;
    }

    public BigDecimal randomDigit() {
        return new BigDecimal(random.nextInt(resultLimit * 9 / 10) + resultLimit / 10 + 1);
    }

    public char randomOperator() {
        return random.nextBoolean() ? '+' : '-';
    }

    public ArithmeticExp randomExp(ArithmeticExp exp) {
        return new ArithmeticExp(exp.getExpression() + randomOperator() + randomDigit());
    }

    public ArithmeticExp createExpOfAddSub() {
        ArithmeticExp exp = new ArithmeticExp(randomDigit().toString());
        ArithmeticExp nextExp = null;
        for (int i = 1; i < expCount; i++) {
            do {
                nextExp = randomExp(exp);
            } while (nextExp.getValue().compareTo(new BigDecimal(resultLimit / 10)) <= 0
                    || nextExp.getValue().compareTo(new BigDecimal(resultLimit)) >= 0);
            exp = nextExp;
        }
        return exp;
    }

    public class ArithmeticExp {

        private String expression;
        private BigDecimal value;

        public ArithmeticExp(String expression) {
            this.expression = expression;
            this.value = Arithmetic.execute(expression);
        }

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }

        public BigDecimal getValue() {
            return value;
        }

        public void setValue(BigDecimal value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return expression;
        }
    }

    public static void main(String[] args) {
        ArithmeticQuestion question = new ArithmeticQuestion(3, 100);
        for (int i = 0; i < 1000; i++) {
            ArithmeticExp exp = question.createExpOfAddSub();
            if (i%2 == 0) System.out.print(exp);
            else System.out.println("\t?\t" + exp);
        }
    }
}
