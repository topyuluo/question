package com.yuluo.question.entity;

/**
 * 定义映射Model
 *
 * @author: YuLuo
 * @date: 2019/4/5
 * @since: JDK 1.8
 * @version: v1.0
 */

public class Person {

	private String name ;
	private int age ;
	private String address ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + name + '\'' +
				", age=" + age +
				", address='" + address + '\'' +
				'}';
	}
}
