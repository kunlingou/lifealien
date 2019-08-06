package com.kunlinr.lifealien.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
	
	@Autowired
	UserDao userDao;
	
	public User save(User user) {
		return userDao.save(user);
	}

}
