package lifealien.excel;

import com.kunlinr.lifealien.excel.annotation.ExcelColumn;

public class Card {
	@Override
	public String toString() {
		return "Card [mappingKey=" + mappingKey + ", sourceKey=" + sourceKey + ", targetkey=" + targetkey
				+ ", sourceField=" + sourceField + ", targetField=" + targetField + ", type=" + type
				+ ", functionModule=" + functionModule + ", sourceDbInfo=" + sourceDbInfo + ", targetDbInfo="
				+ targetDbInfo + "]";
	}

	@ExcelColumn(value = "映射标识", col = 1)
    private String mappingKey;
	
	@ExcelColumn(value = "源标识", col = 2)
    private String sourceKey;

    @ExcelColumn(value = "目标标识", col = 3)
    private String targetkey;

    @ExcelColumn(value = "源字段", col = 4)
    private String sourceField;

    @ExcelColumn(value = "目标字段", col = 5)
    private String targetField;
    
    @ExcelColumn(value = "类型", col = 6)
    private String type;
    
    @ExcelColumn(value = "功能模块", col = 7)
    private String functionModule;
    
    @ExcelColumn(value = "源数据库", col = 8)
    private String sourceDbInfo;
    
    @ExcelColumn(value = "目标数据库", col = 9)
    private String targetDbInfo;

	public String getMappingKey() {
		return mappingKey;
	}

	public void setMappingKey(String mappingKey) {
		this.mappingKey = mappingKey;
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

	public String getFunctionModule() {
		return functionModule;
	}

	public void setFunctionModule(String functionModule) {
		this.functionModule = functionModule;
	}

	public String getSourceDbInfo() {
		return sourceDbInfo;
	}

	public void setSourceDbInfo(String sourceDbInfo) {
		this.sourceDbInfo = sourceDbInfo;
	}

	public String getTargetDbInfo() {
		return targetDbInfo;
	}

	public void setTargetDbInfo(String targetDbInfo) {
		this.targetDbInfo = targetDbInfo;
	}
}
