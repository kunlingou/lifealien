package com.kunlinr.lifealien.sql;

import java.util.ArrayList;
import java.util.List;

import com.kunlinr.lifealien.sql.condition.Condition;
import com.kunlinr.lifealien.sql.condition.ConditionSet;
import com.kunlinr.lifealien.sql.query.Order;

/**
 * 查询参数
 * 
 * @author kongzhengting
 *
 */
public class QueryParam extends ConditionSet{

	private List<Order> orders = new ArrayList<Order>();
	
	public QueryParam(){
		super(ConSetType.AND);
	}
	
	public QueryParam addCondition(Condition... conditions) {
		super.addCondition(conditions);
		return this;
	}
	
	public ConditionSet addConditionSet(ConditionSet... conditionSets) {
		super.addConditionSet(conditionSets);
		return this;
	}
	
	public Order[] getOrders() {
		return orders.toArray(new Order[orders.size()]);
	}

	public QueryParam setOrders(Order[] orders) {
		this.orders.clear();
		for(Order order:orders){
			this.orders.add(order);
		}
		return this;
	}
	public QueryParam addOrders(Order... orders) {
		for(Order order:orders){
			this.orders.add(order);
		}
		return this;
	}
	
}
