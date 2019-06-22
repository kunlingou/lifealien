package com.kunlinr.lifealien.sql.query;

/**
 * 过滤排序
 */
public class Order {

	private String field;
	private boolean desc;

	public String getField() {
		return field;
	}

	public Order setField(String field) {
		this.field = field;
		return this;
	}

	public boolean isDesc() {
		return desc;
	}

	public Order setDesc(boolean desc) {
		this.desc = desc;
		return this;
	}

}
