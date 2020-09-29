package com.hannah.study.reference;

import java.lang.ref.WeakReference;

/**
 * Salad class 继承WeakReference，将Apple作为弱引用。 注意到时候回收的是Apple，而不是Salad
 *
 * @author BrightLoong
 * @date 2018/5/25
 */
public class Salad extends WeakReference<Apple> {

	public Salad(Apple apple) {
		super(apple);
	}

	public static void main(String[] args) {
		Salad salad = new Salad(new Apple("红富士"));
        //通过WeakReference的get()方法获取Apple
        System.out.println("Apple:" + salad.get());
        System.gc();
        try {
            //休眠一下，在运行的时候加上虚拟机参数-XX:+PrintGCDetails，输出gc信息，确定gc发生了。
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //如果为空，代表被回收了
        if (salad.get() == null) {
            System.out.println("clear Apple");
        }
    }
}
