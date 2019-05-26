package com.kunlinr.lifealien.spring.springjdbc.properties;

import java.util.Arrays;
import java.util.List;

public interface DBPropertiesConstant {
	String K_USER = "user";
	String K_PASS = "password";
	String K_URL = "jdbcUrl";
	String K_DRIVERCLASS = "driverClass";
	String K_INIT_POOLSIZE = "initialPoolSize";
	String K_MAX_POOLSIZE = "maxPoolSize";
	
	List<String> KEYSLIST = Arrays.asList(new String[] {
			K_USER,
			K_PASS,
			K_URL,
			K_DRIVERCLASS,
			K_INIT_POOLSIZE,
			K_MAX_POOLSIZE
	});
}
