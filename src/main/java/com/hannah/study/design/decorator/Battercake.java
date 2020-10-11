package com.hannah.study.design.decorator;

/**
 * 装饰者模式：煎饼果子 -> +鸡蛋/+香肠/...
 */
public class Battercake extends ABattercake {

    @Override
    protected String order() {
        return "煎饼果子";
    }

    @Override
    protected int cost() {
        return 8;
    }

    public static void main(String[] args) {
        Battercake battercake = new Battercake();
        System.out.println(battercake.order() + " 销售价格:" + battercake.cost());

        Battercake battercakeWithEgg = new BattercakeWithEgg();
        System.out.println(battercakeWithEgg.order() + " 销售价格:" + battercakeWithEgg.cost());

        Battercake battercakeWithSausage = new BattercakeWithEggSausage();
        System.out.println(battercakeWithSausage.order() + " 销售价格:" + battercakeWithSausage.cost());

        // 加两个鸡蛋&&一根香肠
        runDecorator();
    }

    public static void runDecorator() {
        ABattercake aBattercake;
        aBattercake = new Battercake();
        aBattercake = new EggDecorator(aBattercake);
        aBattercake = new EggDecorator(aBattercake);
        aBattercake = new SausageDecorator(aBattercake);

        System.out.println(aBattercake.order() + " 销售价格:" + aBattercake.cost());
    }

}