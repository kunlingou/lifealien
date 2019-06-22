package com.kunlinr.lifealien.sql;

public interface SqlConstant {
	/**
	 * 参数为表名的简单SQL查询,获取表结构
	 */
	String SQL_QUERY_TABLEDEFINE_MYSQL = "SELECT * FROM %s LIMIT 1";
	
	String SQL_QUERY_TABLEDEFINE_ORACLE = "SELECT * FROM %s WHERE ROWNUM<2";
	
	String FORMAT_CONDITION_IN = "%s IN ( %s )";
	
	String FORMAT_CONDITION_EQUAL = "%s = %s";
}

