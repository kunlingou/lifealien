package com.kunlinr.lifealien.main.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kunlinr.lifealien.main.dao.repository.UserRespository;
import com.kunlinr.lifealien.main.entity.User;
import com.kunlinr.lifealien.main.service.UserService;

public class UserServiceImpl implements UserService{
	@Autowired
	private UserRespository userRpy;

	@Override
	public User save(User user) {
		return userRpy.save(user);
	}

	@Override
	public User findName(String name) {
		return userRpy.findName(name);
	}
	
	@Override
	public List<User> findAll() {
		return userRpy.findAll();
	}
}
