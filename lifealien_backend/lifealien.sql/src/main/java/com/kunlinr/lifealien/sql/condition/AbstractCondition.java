package com.kunlinr.lifealien.sql.condition;

/**   
 * 查询条件
 * @author gkl
 * @date 2019年5月28日  
 */
public interface AbstractCondition {

	public boolean isCondition();
	
	public boolean isConditionSet();
	
	public Condition toCondition();
	
	public ConditionSet toConditionSet();
}
