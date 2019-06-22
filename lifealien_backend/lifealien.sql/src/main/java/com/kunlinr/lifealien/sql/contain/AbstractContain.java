package com.kunlinr.lifealien.sql.contain;

/**   
 * 数据来源
 * @author gkl
 * @date 2019年5月28日  
 */
public interface AbstractContain {
	
	public boolean isContain();
	
	public boolean isContainSet();
	
	public Contain toContain();
	
	public ContainSet toContainSet();
}
