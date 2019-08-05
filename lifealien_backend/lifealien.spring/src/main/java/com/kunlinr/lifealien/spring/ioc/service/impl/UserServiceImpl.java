package com.kunlinr.lifealien.spring.ioc.service.impl;

import com.kunlinr.lifealien.spring.ioc.dao.UserDao;
import com.kunlinr.lifealien.spring.ioc.dao.impl.UserDaoMySqlImpl;
import com.kunlinr.lifealien.spring.ioc.service.UserService;

public class UserServiceImpl implements UserService{
	
	private UserDao userDao = null;
	
	@Override
	public void getUser() {
		userDao.getUser();
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
