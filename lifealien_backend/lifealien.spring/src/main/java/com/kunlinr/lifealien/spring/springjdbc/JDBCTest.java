package com.kunlinr.lifealien.spring.springjdbc;

import static com.kunlinr.lifealien.spring.springjdbc.properties.DBPropertiesConstant.K_DRIVERCLASS;
import static com.kunlinr.lifealien.spring.springjdbc.properties.DBPropertiesConstant.K_INIT_POOLSIZE;
import static com.kunlinr.lifealien.spring.springjdbc.properties.DBPropertiesConstant.K_MAX_POOLSIZE;
import static com.kunlinr.lifealien.spring.springjdbc.properties.DBPropertiesConstant.K_PASS;
import static com.kunlinr.lifealien.spring.springjdbc.properties.DBPropertiesConstant.K_URL;
import static com.kunlinr.lifealien.spring.springjdbc.properties.DBPropertiesConstant.K_USER;

import java.util.Map;
import java.util.Properties;

import javax.naming.spi.DirStateFactory.Result;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCTest {
	JdbcTemplate template;

	@Test
	public void testQuery() {
		Properties props = new Properties();
		props.setProperty(K_USER, "root");
		props.setProperty(K_PASS, "mybatis");
		props.setProperty(K_URL, "jdbc:mysql://localhost:3306/lifeaide");
		props.setProperty(K_DRIVERCLASS, "com.mysql.jdbc.Driver");
		props.setProperty(K_INIT_POOLSIZE, "5");
		props.setProperty(K_MAX_POOLSIZE, "10");
		if(template == null) {
			template = DBManager.getTemplate(props);
		}
		String sql = "SELECT id, name FROM user";
		Map<String, Object> maps = template.queryForMap(sql);
		template.query(sql,e->{
			System.out.println(e.getString("id"));
		});
//		System.out.println(maps);
	}
}
