package com.hannah.study.spi;

import com.hannah.study.annotation.Factory;

@Factory(type = Robot.class)
public class Bumblebee implements Robot {

	@Override
	public void sayHello() {
		System.out.println("Hello, I am Bumblebee.");
	}

}