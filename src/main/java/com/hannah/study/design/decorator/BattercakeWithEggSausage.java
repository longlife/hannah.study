package com.hannah.study.design.decorator;

/**
 * 煎饼果子 -> +香肠
 */
public class BattercakeWithEggSausage extends BattercakeWithEgg {

    @Override
    public String order() {
        return super.order() + " 加一根香肠";
    }

    @Override
    public int cost() {
        return super.cost() + 2;
    }

}
