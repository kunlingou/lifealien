package com.kunlinr.lifealien.sql.table;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.kunlinr.lifealien.sql.SqlField;

public class TableDefine {
	/**
	 * template
	 */
	private JdbcTemplate template;
	/**
	 * 表名
	 */
	private String name;
	/**
	 * 别名
	 */
	private String label;
	/**
	 * 字段
	 */
	private Map<String,SqlField> fieldMap;
	
	public TableDefine(String name) {
		this.name = name;
		this.label = name;
	}
	
	public SqlField getField(String name) {
		return fieldMap.get(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Map<String, SqlField> getFieldMap() {
		return fieldMap;
	}

	public void setFieldMap(Map<String, SqlField> fieldMap) {
		this.fieldMap = fieldMap;
	}

	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
}
