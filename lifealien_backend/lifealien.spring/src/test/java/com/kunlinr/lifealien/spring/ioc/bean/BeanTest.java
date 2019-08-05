package com.kunlinr.lifealien.spring.ioc.bean;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.kunlinr.lifealien.spring.ioc.bean.Hello;

public class BeanTest {

	@Test
	public void Test1() {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		Hello bean = (Hello) context.getBean("hello");
		bean.show();
	}
}
