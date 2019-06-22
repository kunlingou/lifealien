package com.kunlinr.lifealien.collection.set;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import com.alibaba.fastjson.JSON;

public class SetTest implements Serializable{
	/**
	 * 
	 */
	@Test
	public void TestRepeat() {
		Set<Object> hashSet = new HashSet<>();
		hashSet.add(new Integer(2));
		hashSet.add(new Integer(2));
		hashSet.add(new Integer(2));
		hashSet.add(new People("王",10,"男"));
		hashSet.add(new People("王",10,"男")); 
		hashSet.add(new People("李",10,"男"));
		System.out.println(hashSet);
	}
}
