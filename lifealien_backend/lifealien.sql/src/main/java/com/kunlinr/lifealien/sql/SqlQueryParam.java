package com.kunlinr.lifealien.sql;

import java.util.Set;

import com.kunlinr.lifealien.sql.condition.ConditionSet;
import com.kunlinr.lifealien.sql.contain.Contain;
import com.kunlinr.lifealien.sql.contain.ContainSet;
import com.kunlinr.lifealien.sql.table.TableDefine;

public class SqlQueryParam extends Contain implements SqlParam{

	private SqlType sqlType;
	
	private Set<SqlField> fieldSet;
	
	private ContainSet containSet;
	
	private ConditionSet conditionSet;
	
	public SqlQueryParam() {
		this.sqlType = SqlType.QUERY;
	}
	
	public SqlQueryParam(TableDefine tableDefine) {
		super(tableDefine);
		this.sqlType = SqlType.QUERY;
	}

	public SqlType getSqlType() {
		return sqlType;
	}

	public void setSqlType(SqlType sqlType) {
		this.sqlType = sqlType;
	}

	public Set<SqlField> getFieldSet() {
		return fieldSet;
	}

	public void setFieldSet(Set<SqlField> fieldSet) {
		this.fieldSet = fieldSet;
	}

	public ContainSet getContainSet() {
		return containSet;
	}

	public void setContainSet(ContainSet containSet) {
		this.containSet = containSet;
	}

	public ConditionSet getConditionSet() {
		return conditionSet;
	}

	public void setConditionSet(ConditionSet conditionSet) {
		this.conditionSet = conditionSet;
	}
}
