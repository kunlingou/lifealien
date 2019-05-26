package com.kunlinr.lifealien.main.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	public static String DBurl = "";
	
	private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>() {
		protected Connection initialValue() {
	        try {
				return DriverManager.getConnection(DBurl);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        return null;
	    }
	};
	
	public static void close(Object... objects) {
		for(Object object : objects) close(object);
	}
	
	public static void close(Object object) {
		if(object != null) {
			try {
				if(object instanceof AutoCloseable) {
					((AutoCloseable) object).close();
				}
			}catch(Exception e) {}
		}	
	}
}
