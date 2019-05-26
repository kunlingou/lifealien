package com.kunlinr.lifealien.main.service;

import org.springframework.data.repository.query.Param;

import com.kunlinr.lifealien.main.entity.User;

public interface UserService {
	
	public User save(User user);
	
	public User findName(@Param("name") String name);
}
