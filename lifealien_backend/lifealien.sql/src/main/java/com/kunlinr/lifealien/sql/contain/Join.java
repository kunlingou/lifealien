package com.kunlinr.lifealien.sql.contain;

public enum Join {
	/**
	 * 增
	 */
	LEFT_JOIN("LEFT JOIN"),
	/**
	 * 删
	 */
	RIGHT_JOIN("RIGHT JOIN"),
	/**
	 * 改
	 */
	INNER_JOIN("INNER JOIN");
	
	private String title;
	
	private Join(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
}
