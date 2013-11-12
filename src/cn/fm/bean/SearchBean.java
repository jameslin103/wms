package cn.fm.bean;

import cn.fm.utils.StringUtil;

public class SearchBean {
    private String searchName;
    private String searchNameAlias;
    private String symbol;
    private String defaultValue;
    private String extendValue;
    private String type;
    private String alis;
    private Boolean isFail;
    private String moduleName;
    private String defindFieldType;

    public String getSearchNameAlias() {
        return searchNameAlias;
    }

    public void setSearchNameAlias(String searchNameAlias) {
        this.searchNameAlias = searchNameAlias;
    }

    public String getDefindFieldType() {
        return defindFieldType;
    }

    public void setDefindFieldType(String defindFieldType) {
        this.defindFieldType = defindFieldType;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Boolean getIsFail() {
        return StringUtil.isEmptyStr(new String[]{symbol, searchName});
    }

    public String getAlis() {
        return alis;
    }

    public void setAlis(String alis) {
        this.alis = alis;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setExtendValue(String extendValue) {
        this.extendValue = extendValue;
    }

    public String getExtendValue() {
        return extendValue;
    }

    public boolean getIsEnvalidValue() {
    	if(StringUtil.isEmpty(extendValue)){
        return StringUtil.isEmptyStr(new String[]{symbol, searchName,defaultValue});
    	}
    	return StringUtil.isEmptyStr(new String[]{symbol, searchName,extendValue});
    }
}
