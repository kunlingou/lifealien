package com.kunlinr.lifealien.sql.table;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;

import com.kunlinr.lifealien.sql.SqlField;
import static com.kunlinr.lifealien.sql.SqlConstant.*;

public class TableDefineBuilder {
	
	public static TableDefine getTableDefine(String tableName,JdbcTemplate template) {
		TableDefine tableDefine = new TableDefine(tableName);
		RowCountCallbackHandler rcch = new RowCountCallbackHandler();
		template.query(String.format(SQL_QUERY_TABLEDEFINE_MYSQL, tableName), rcch);
		String[] columnNames = rcch.getColumnNames();
		int[] columnTypes = rcch.getColumnTypes();
		Map<String,SqlField> fieldMap = new HashMap<>();
		for(int i=0;i<rcch.getColumnCount();i++) {
			fieldMap.put(columnNames[i], new SqlField(tableDefine,columnNames[i],columnTypes[i]));
		}
		tableDefine.setFieldMap(fieldMap);
		tableDefine.setTemplate(template);
		return tableDefine;
	}
}
