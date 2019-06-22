package com.kunlinr.lifealien.sql;

import java.io.Serializable;

import com.kunlinr.lifealien.sql.table.TableDefine;


public class SqlField implements Serializable{
	private static final long serialVersionUID = -3315730352424726921L;
	/**
	 * 字段名
	 */
	private String name;
	/**
	 * 别名
	 */
	private String label;
	/**
	 * 类型：参考 {@link java.sql.Types}
	 */
	private int type;
	/**
	 * 物理表
	 */
	private TableDefine table;
	
	public SqlField(TableDefine table,String name,int type) {
		this.table = table;
		this.name = name;
		this.label = name;
		this.setType(type);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof SqlField) {
			return this.toString().equals(obj.toString());
		}
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
	
	@Override
	public String toString() {
		return table.getName()+name+label;
//		return JSON.toJSONString(this,SerializerFeature.IgnoreErrorGetter); //table字段太多时降低效率
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

	public TableDefine getTable() {
		return table;
	}

	public void setTable(TableDefine table) {
		this.table = table;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getString() {
		return getTable().getLabel()+"."+getName();
	}
}
