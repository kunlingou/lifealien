package com.kunlinr.lifealien.spring.ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kunlinr.lifealien.spring.ioc.dao.impl.UserDaoMySqlImpl;
import com.kunlinr.lifealien.spring.ioc.dao.impl.UserDaoOracleImpl;
import com.kunlinr.lifealien.spring.ioc.service.UserService;
import com.kunlinr.lifealien.spring.ioc.service.impl.UserServiceImpl;

public class UserServiceTest {
	@Test
	public void Test1() {
		UserServiceImpl userService = new UserServiceImpl();
		userService.setUserDao(new UserDaoMySqlImpl());
		userService.getUser();
		userService.setUserDao(new UserDaoOracleImpl());
		userService.getUser();
	}
	
	@Test
	public void Test2() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		UserService bean = (UserService) context.getBean("userService");
		bean.getUser();
	}
}
