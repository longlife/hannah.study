package com.hannah.study.design.decorator;

/**
 * 抽象基类
 */
public abstract class ABattercake {

    /**
     * 下单
     * @return
     */
    protected abstract String order();

    /**
     * 销售价
     * @return
     */
    protected abstract int cost();

}