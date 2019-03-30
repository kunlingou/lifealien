package com.kunlinr.lifealien.po;

import com.kunlinr.excel.annotation.ExcelColumn;

public class MigrationRow{


	@ExcelColumn(value = "源标识", col = 1)
    private String sourceKey;

    @ExcelColumn(value = "目标标识", col = 2)
    private String targetkey;

    @ExcelColumn(value = "源字段", col = 3)
    private String sourceField;

    @ExcelColumn(value = "目标字段", col = 4)
    private String targetField;
    
    @ExcelColumn(value = "类型", col = 5)
    private String type;
    
    @Override
    public String toString() {
    	return "MigrationRow [sourceKey=" + sourceKey + ", targetkey=" + targetkey + ", sourceField=" + sourceField
    			+ ", targetField=" + targetField + ", type=" + type + "]";
    }

	public String getSourceKey() {
		return sourceKey;
	}

	public void setSourceKey(String sourceKey) {
		this.sourceKey = sourceKey;
	}

	public String getTargetkey() {
		return targetkey;
	}

	public void setTargetkey(String targetkey) {
		this.targetkey = targetkey;
	}

	public String getSourceField() {
		return sourceField;
	}

	public void setSourceField(String sourceField) {
		this.sourceField = sourceField;
	}

	public String getTargetField() {
		return targetField;
	}

	public void setTargetField(String targetField) {
		this.targetField = targetField;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
