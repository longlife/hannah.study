package com.hannah.study.design.decorator;

/**
 * 香肠装饰类
 */
public class SausageDecorator extends AbstractDecorator {

    public SausageDecorator(ABattercake aBattercake) {
        super(aBattercake);
    }

    @Override
    protected String order() {
        return super.order() + " 加一根香肠";
    }

    @Override
    protected int cost() {
        return super.cost() + 2;
    }

}