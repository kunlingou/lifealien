package com.kunlinr.lifealien.sql.condition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConditionSet implements AbstractCondition{
	private List<AbstractCondition> conditions = new ArrayList<AbstractCondition>();
	private ConSetType type;
	
	public ConditionSet(ConSetType type){
		this.type = type;
	}
	
	public ConditionSet(AbstractCondition condition) {
		this.conditions = Arrays.asList(new AbstractCondition[] {condition});
	}

	public void clearCondition(){
		this.conditions.clear();
	}
	
	public AbstractCondition[] getConditions() {
		return conditions.toArray(new AbstractCondition[conditions.size()]);
	}

	public ConditionSet addCondition(Condition... conditions) {
		for(Condition con:conditions){
			this.conditions.add(con);
		}
		return this;
	}
	
	public ConditionSet addConditionSet(ConditionSet... conditionSets) {
		for(ConditionSet con:conditionSets){
			this.conditions.add(con);
		}
		return this;
	}
	
	public ConSetType getType() {
		return type;
	}
	
	public boolean isCondition() {
		return false;
	}

	public boolean isConditionSet() {
		return true;
	}

	public Condition toCondition() {
		return (Condition) conditions.get(0);
	}

	public ConditionSet toConditionSet() {
		return this;
	}
	
	public enum ConSetType{
		OR,
		AND
	}

	public String getString() {
		if(conditions.size()==1) {
			return this.toCondition().getString();
		}
		return null;
	}
}
