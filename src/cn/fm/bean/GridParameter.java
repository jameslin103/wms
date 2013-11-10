package cn.fm.bean;

public class GridParameter {

	private String objectName;
    private String defaultValue;
    private String objectAlias;
    private String defaultHQL;
    private String moduleName;
    private String queryHQL;
    private String filterHQL;
    private boolean isIgnoreDefaultValueObjectAlias;

    public boolean getIsIgnoreDefaultValueObjectAlias() {
        return this.isIgnoreDefaultValueObjectAlias;
    }

    public void setIsIgnoreDefaultValueObjectAlias(boolean isIgnoreDefaultValueObjectAlias) {
        this.isIgnoreDefaultValueObjectAlias = isIgnoreDefaultValueObjectAlias;
    }

    public String getFilterHQL() {
        return filterHQL;
    }

    public void setFilterHQL(String filterHQL) {
        this.filterHQL = filterHQL;
    }

    public String getQueryHQL() {
        return queryHQL;
    }

    public void setQueryHQL(String queryHQL) {
        this.queryHQL = queryHQL;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public GridParameter setModuleNameVal(String moduleName) {
        this.moduleName = moduleName;
        return this;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public GridParameter setObjectNameVal(String objectName) {
        this.objectName = objectName;
        return this;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public GridParameter setDefaultValueVal(String defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }

    public String getObjectAlias() {
        return objectAlias;
    }

    public void setObjectAlias(String objectAlias) {
        this.objectAlias = objectAlias;
    }

    public GridParameter setObjectAliasVal(String objectAlias) {
        this.objectAlias = objectAlias;
        return this;
    }

    public String getDefaultHQL() {
        return defaultHQL;
    }

    public void setDefaultHQL(String defaultHQL) {
        this.defaultHQL = defaultHQL;
    }

    public GridParameter setDefaultHQLVal(String defaultHQL) {
        this.defaultHQL = defaultHQL;
        return this;
    }
    
    public static GridParameter getInstance(){
        return new GridParameter();
    }
	
}
