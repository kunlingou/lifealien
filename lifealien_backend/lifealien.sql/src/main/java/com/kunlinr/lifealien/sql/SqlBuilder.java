package com.kunlinr.lifealien.sql;

public class SqlBuilder{
	
	public static String getSql(SqlQueryParam params) {
		String sql = null;
		switch(params.getSqlType()) {
		case INSERT:
			sql = getInsertSql(params);
			break;
		case DELETE:
			sql = getDeleteSql(params);
			break;
		case UPDATE:
			sql = getUpdateSql(params);
			break;
		case QUERY:
			sql = getQuerySql(params);
			break;
		}
		return sql;
	}
	
	private static String getInsertSql(SqlQueryParam params) {
		return null;
	}
	
	private static String getDeleteSql(SqlQueryParam params) {
		return null;
	}
	
	private static String getUpdateSql(SqlQueryParam params) {
		return null;
	}
	
	private static String getQuerySql(SqlQueryParam params) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(SqlType.QUERY.getTitle());
		sqlBuffer.append("\r\n");
		for(SqlField field:params.getFieldSet()) {
			sqlBuffer.append("  "+field.getName()+" "+field.getLabel());
			sqlBuffer.append(",\r\n");
		}
		sqlBuffer.delete(sqlBuffer.length()-3, sqlBuffer.length());
		sqlBuffer.append("\r\n");
		sqlBuffer.append("FROM "+params.getContainSet().getString());
		sqlBuffer.append("\r\n");
		sqlBuffer.append("WHERE "+params.getConditionSet().getString());
		return sqlBuffer.toString();
	}
}
