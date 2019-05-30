package com.kunlinr.lifealien.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kunlinr.lifealien.main.web.service.UserServiceImpl;

@Configuration
public class MainConfiguration {
	
	@Bean
	public UserServiceImpl userServiceImpl() {
		return new UserServiceImpl();
	}
}
