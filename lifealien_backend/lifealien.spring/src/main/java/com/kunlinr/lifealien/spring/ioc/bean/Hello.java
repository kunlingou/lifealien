package com.kunlinr.lifealien.spring.ioc.bean;

public class Hello {
	private String name;
	public void setName(String name) {
		this.name = name;
	}
	public void show() {
		System.out.println("hello,"+name);
	}
}
