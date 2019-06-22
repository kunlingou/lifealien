package com.kunlinr.lifealien.sql.condition;

import java.util.StringJoiner;

import com.kunlinr.lifealien.sql.Operator;
import com.kunlinr.lifealien.sql.SqlField;
import static com.kunlinr.lifealien.sql.SqlConstant.*;

public class Condition implements AbstractCondition{
	private Operator operator;
	private SqlField field;
	private Object[] value;

	public Operator getOperator() {
		return operator;
	}

	public Condition setOperator(Operator operator) {
		this.operator = operator;
		return this;
	}

	public SqlField getField() {
		return field;
	}

	public Condition setField(SqlField field) {
		this.field = field;
		return this;
	}

	public Object[] getValue() {
		return value;
	}

	public Condition setValue(Object[] value) {
		this.value = value;
		return this;
	}

	public boolean isCondition() {
		return true;
	}
	
	public boolean isConditionSet() {
		return false;
	}

	public Condition toCondition() {
		return this;
	}

	public ConditionSet toConditionSet() {
		return new ConditionSet(this);
	}

	public String getString() {
		String s = null;
		switch(operator) {
		case IN:
			s = String.format(FORMAT_CONDITION_IN, getField().getString(),getValueAsString(value));
			break;
		case EQUAL:
			s = String.format(FORMAT_CONDITION_EQUAL, getField().getString(),getValueAsString(value));
			break;
		default:
			break;
		}
		return s;
	}

	private String getValueAsString(Object[] value) {
		StringJoiner joiner = new StringJoiner(",");
        for (int i=0;i<value.length;i++) {
            joiner.add("'"+value[i].toString()+"'");
        }
        return joiner.toString();
	}
}
