package com.kunlinr.lifealien.spring.ioc.dao.impl;

import com.kunlinr.lifealien.spring.ioc.dao.UserDao;

public class UserDaoOracleImpl implements UserDao{

	@Override
	public void getUser() {
		System.out.println("oracle获取用户数据");
	}
	
}