package com.kunlinr.lifealien.sql.contain;

import com.kunlinr.lifealien.sql.table.TableDefine;

public class Contain implements AbstractContain{
	
	TableDefine contain;
	
	String label;
	
	@Override
	public boolean isContain() {
		return true;
	}

	@Override
	public boolean isContainSet() {
		return false;
	}

	@Override
	public Contain toContain() {
		return null;
	}

	@Override
	public ContainSet toContainSet() {
		return new ContainSet(this);
	}
	
	public Contain(TableDefine tableDefine) {
		contain = tableDefine;
		label = tableDefine.getLabel();
	}
	
	public Contain() {
	}
	
	public String getString() {
		return contain.getName();
	}

	public TableDefine getContain() {
		return contain;
	}

	public void setContain(TableDefine contain) {
		this.contain = contain;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
