package com.kunlinr.lifealien.sql;

import java.util.Set;

import com.kunlinr.lifealien.sql.condition.ConditionSet;
import com.kunlinr.lifealien.sql.contain.ContainSet;

public interface SqlParam {
	
	public SqlType getSqlType();

	public void setSqlType(SqlType sqlType);

	public Set<SqlField> getFieldSet();

	public void setFieldSet(Set<SqlField> fieldSet);

	public ContainSet getContainSet();

	public void setContainSet(ContainSet containSet);

	public ConditionSet getConditionSet();

	public void setConditionSet(ConditionSet conditionSet);
}
