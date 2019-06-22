package com.kunlinr.lifealien.sql;

/**
 * 操作符
 */
public enum Operator {
	/**
	 * 等于
	 */
	EQUAL("等于"),
	/**
	 * 不等于
	 */
	UNEQUAL("不等于"),
	/**
	 * 大于
	 */
	GREATER_THAN("大于"),
	/**
	 * 小于
	 */
	LESS_THAN("小于"),
	/**
	 * 大于等于
	 */
	GREATER_THAN_OR_EQUAL("大于等于"),
	/**
	 * 小于等于
	 */
	LESS_THAN_OR_EQUAL("小于等于"),
	/**
	 * 前匹配
	 */
	STARTWITH("前匹配"),
	/**
	 * 后匹配
	 */
	ENDWITH("后匹配"),
	/**
	 * 模糊匹配
	 */
	VAGUEWITH("模糊匹配"),
	/**
	 * 包含
	 */
	IN("包含"),
	/**
	 * 空值
	 */
	ISNULL("空值"),
	/**
	 * 非空
	 */
	ISNOTNULL("非空");
	;
	
	private String title;
	
	private Operator(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return this.title;
	}
}
