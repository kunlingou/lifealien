package com.kunlinr.lifealien.sql.contain;

import java.util.Arrays;
import java.util.List;

public class ContainSet implements AbstractContain{
	
	List<AbstractContain> containSet;
	
	private Join type;
	
	public ContainSet(Contain contain) {
		this.containSet = Arrays.asList(new Contain[] {contain});
	}

	@Override
	public boolean isContain() {
		return false;
	}

	@Override
	public boolean isContainSet() {
		return true;
	}

	@Override
	public Contain toContain() {
		return (Contain) containSet.get(0);
	}

	@Override
	public ContainSet toContainSet() {
		return null;
	}

	public String getString() {
		if(containSet.size()==1) {
			return this.toContain().getString()+" "+this.toContain().getLabel();
		}
		return null;
	}

	public Join getType() {
		return type;
	}

	public void setType(Join type) {
		this.type = type;
	}
}
