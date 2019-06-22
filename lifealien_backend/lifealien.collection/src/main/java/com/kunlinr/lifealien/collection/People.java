package com.kunlinr.lifealien.collection;

import java.io.Serializable;
import com.alibaba.fastjson.JSON;

public class People implements Serializable{
	String name;
	int age;
	String sex;
	public People(String name, int age, String sex) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
	
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

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof People) {
			return name.equals(((People)obj).name);
		}
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public String toString() {
		System.out.println(JSON.toJSONString(this));
		return super.toString();
	}
}
