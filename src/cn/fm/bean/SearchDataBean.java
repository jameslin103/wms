package cn.fm.bean;

import java.util.List;
import java.util.Map;

import cn.fm.utils.Constant;

@SuppressWarnings("unchecked")
public class SearchDataBean {

	private String displayName;
	private String symbolId;
	private String fieldId;
	private String fieldType;
 	private String fieldName;
 	private String fieldNameAlias;
	private Map<Object,String> symbolMap;
	private String type;
	private List selectList;
	private String key;
	private String value;
	private String alis;
	private String moduleName;
    private String yearRange;
    private String fieldLength= Constant.WMS_DYNAMIC_QUERY_FIELD_LENTH;

    public String getFieldNameAlias() {
        return fieldNameAlias;
    }

    public void setFieldNameAlias(String fieldNameAlias) {
        this.fieldNameAlias = fieldNameAlias;
    }

    public String getFieldLength() {
        return fieldLength;
    }

    public void setFieldLength(String fieldLength) {
        this.fieldLength = fieldLength;
    }

    public String getYearRange() {
        return yearRange;
    }

    public void setYearRange(String yearRange) {
        this.yearRange = yearRange;
    }

    public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getAlis() {
		return alis;
	}
	public void setAlis(String alis) {
		this.alis = alis;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getSymbolId() {
		return symbolId;
	}
	public void setSymbolId(String symbolId) {
		this.symbolId = symbolId;
	}
 	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
 
	public Map<Object, String> getSymbolMap() {
		return symbolMap;
	}
	
	public void setSymbolMap(Map<Object, String> symbolMap) {
		this.symbolMap = symbolMap;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List getSelectList() { 
		return selectList;
	}
	public void setSelectList(List selectList) {
		this.selectList = selectList;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public String getFieldId() {
		return fieldId;
	}
 
}
