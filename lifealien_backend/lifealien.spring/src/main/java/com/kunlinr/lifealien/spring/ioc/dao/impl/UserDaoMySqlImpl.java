package com.kunlinr.lifealien.spring.ioc.dao.impl;

import com.kunlinr.lifealien.spring.ioc.dao.UserDao;

public class UserDaoMySqlImpl implements UserDao {

	@Override
	public void getUser() {
		System.out.println("mysql获取用户数据");
	}

}
