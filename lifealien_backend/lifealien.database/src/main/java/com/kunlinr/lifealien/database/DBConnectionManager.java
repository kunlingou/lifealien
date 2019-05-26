package com.kunlinr.lifealien.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class DBConnectionManager {
	private static List<Connection> pool = new ArrayList<>();
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "";
			for(int i=0;i<3;i++) {
				Connection conn = DriverManager.getConnection(url,"","");
				pool.add(conn);
			}
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
	}
	
	public synchronized static Connection getCon() {
		Connection con = pool.remove(0);
		return con;
	}
	
	public static void back(Connection con) {
		pool.add(con);
	}
}
