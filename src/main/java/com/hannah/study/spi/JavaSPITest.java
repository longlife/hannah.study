package com.hannah.study.spi;

import com.hannah.study.annotation.FactoryProxy;

import java.util.ServiceLoader;

public class JavaSPITest {

    public static void main(String[] args) {
    	ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
    	System.out.println("Java SPI");
    	serviceLoader.forEach(Robot::sayHello);

    	// TODO FactoryProcessor未执行
		Robot bumblebee = FactoryProxy.create(Robot.class, "Bumblebee");
		bumblebee.sayHello();
	}

}