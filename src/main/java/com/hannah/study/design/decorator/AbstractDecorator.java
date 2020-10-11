package com.hannah.study.design.decorator;

/**
 * 抽象装饰类
 */
public class AbstractDecorator extends ABattercake {

    private ABattercake aBattercake;

    public AbstractDecorator(ABattercake aBattercake) {
        this.aBattercake = aBattercake;
    }

    @Override
    protected String order() {
        return this.aBattercake.order();
    }

    @Override
    protected int cost() {
        return this.aBattercake.cost();
    }

}
