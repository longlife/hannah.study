package com.hannah.study.design.decorator;

/**
 * 煎饼果子 -> +鸡蛋
 */
public class BattercakeWithEgg extends Battercake {

    @Override
    public String order() {
        return super.order() + " 加一个鸡蛋";
    }

    @Override
    public int cost() {
        return super.cost() + 1;
    }

}