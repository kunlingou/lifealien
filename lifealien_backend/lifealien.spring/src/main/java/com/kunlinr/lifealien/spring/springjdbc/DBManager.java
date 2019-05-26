package com.kunlinr.lifealien.spring.springjdbc;

import static com.kunlinr.lifealien.spring.springjdbc.properties.DBPropertiesConstant.K_DRIVERCLASS;
import static com.kunlinr.lifealien.spring.springjdbc.properties.DBPropertiesConstant.K_INIT_POOLSIZE;
import static com.kunlinr.lifealien.spring.springjdbc.properties.DBPropertiesConstant.K_MAX_POOLSIZE;
import static com.kunlinr.lifealien.spring.springjdbc.properties.DBPropertiesConstant.K_PASS;
import static com.kunlinr.lifealien.spring.springjdbc.properties.DBPropertiesConstant.K_URL;
import static com.kunlinr.lifealien.spring.springjdbc.properties.DBPropertiesConstant.K_USER;

import java.beans.PropertyVetoException;
import java.util.Properties;

import org.springframework.jdbc.core.JdbcTemplate;

import com.kunlinr.lifealien.spring.springjdbc.properties.DBProperties;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBManager {
	
	private static DBProperties dbPropertiesInstance;
	private static ComboPooledDataSource dataSourceInstance;
	private static JdbcTemplate templateInstance;
	
	public static JdbcTemplate getTemplate(Properties props) {
		if(dbPropertiesInstance!=null && dbPropertiesInstance.equals(props)) {
			return templateInstance;
		}else {
			dbPropertiesInstance = null;
			dataSourceInstance = null;
			templateInstance = null;
		}
		getDBProperties(props);
		getDataSource(dbPropertiesInstance);
		return _getTemplate(dataSourceInstance);
	}
	
	public static JdbcTemplate getCurrentTemplate() {
		return templateInstance;
	}
	
	public static ComboPooledDataSource getDataSource(DBProperties props) {
		if(dataSourceInstance == null) {
			_getDataSource(props);
		}
		return dataSourceInstance;
	}
	
	public static DBProperties getDBProperties(Properties props) {
		if(dbPropertiesInstance == null) {
			_getDBProperties(props);
		}
		return dbPropertiesInstance;
	}
	
	private static JdbcTemplate _getTemplate(ComboPooledDataSource dataSource) {
		templateInstance = new JdbcTemplate(dataSource);
		return templateInstance;
	}
	
	private static ComboPooledDataSource _getDataSource(DBProperties props) {
		dataSourceInstance = new ComboPooledDataSource();
		dataSourceInstance.setUser(props.getProperty(K_USER));
		dataSourceInstance.setPassword(props.getProperty(K_PASS));
		dataSourceInstance.setJdbcUrl(props.getProperty(K_URL));
		try {
			dataSourceInstance.setDriverClass(props.getProperty(K_DRIVERCLASS));
		} catch (PropertyVetoException e) {
			throw new RuntimeException("not found class"+props.getProperty(K_DRIVERCLASS));
		}
		dataSourceInstance.setInitialPoolSize(Integer.valueOf(props.getProperty(K_INIT_POOLSIZE)));
		dataSourceInstance.setMaxPoolSize(Integer.valueOf(props.getProperty(K_MAX_POOLSIZE)));
		return dataSourceInstance;
	}
	
	private static DBProperties _getDBProperties(Properties props) {
		dbPropertiesInstance = new DBProperties(props);
		return dbPropertiesInstance;
	}
}