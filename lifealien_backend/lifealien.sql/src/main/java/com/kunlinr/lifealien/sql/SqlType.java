package com.kunlinr.lifealien.sql;

public enum SqlType {
	/**
	 * 增
	 */
	INSERT("INSERT"),
	/**
	 * 删
	 */
	DELETE("DELETE"),
	/**
	 * 改
	 */
	UPDATE("UPDATE"),
	/**
	 * 查
	 */
	QUERY("SELECT");
	
	private String title;
	
	private SqlType(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
}
