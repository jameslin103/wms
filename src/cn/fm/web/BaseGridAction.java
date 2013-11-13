package cn.fm.web;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.fm.bean.SearchDataBean;
import cn.fm.service.base.BaseGrid;
import cn.fm.service.grid.impl.SearchService;


@SuppressWarnings("unchecked")
public abstract class BaseGridAction {
	
    protected SearchService searchService;
    protected BaseGrid baseGrid;
    protected List<SearchDataBean> searchList;
    Map<String, Object> dynamicParametersMap = new HashMap<String, Object>();
   
	List excluedeQueryParams = new ArrayList();
    protected  boolean isJsonSessionExpired;
    protected boolean isJsonUserLogin = true;

    public abstract boolean getIsJsonUserLogin() ;

    public void setIsJsonUserLogin(boolean isJsonUserLogin) {
        this.isJsonUserLogin = isJsonUserLogin;
    }

    public abstract boolean getIsJsonSessionExpired();

    public void setIsJsonSessionExpired(boolean isJsonSessionExpired) {
        this.isJsonSessionExpired = isJsonSessionExpired;
    }

    public abstract BaseGrid getBaseGrid();

    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }


    public void setBaseGrid(BaseGrid baseGrid) {
        this.baseGrid = baseGrid;
    }

   
    public void addExcluedeQueryParams(String queryId){
         excluedeQueryParams.add(queryId);
    }

    public void addDynamicParametersMap(Map<String,Map> dynamicParameterMap){
        this.dynamicParametersMap.putAll(dynamicParameterMap);
    }

    public List<SearchDataBean> getSearchList() {
        return searchList;
    }

    private String flag;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
//    public void validateDateFormat(String filedName,BaseGrid baseGridTemp){
//    	 String name = searchService.getDefaultValue(filedName, baseGridTemp);
//    	 String extendName=searchService.getExtendValue(filedName, baseGridTemp);
//    	 String querySymbol = searchService.getSymbol(filedName, baseGridTemp);
//    	
//    	 if (!StringUtil.isEmptyStr(querySymbol)) {
//    	    	if (!StringUtil.isEmpty(name) && !DateUtil.isDateRegex(name)) {
//    	    		validateSearch(filedName, this.getText("date.format.error",new String[]{FormateUtilService.getDateFormat().toUpperCase()}));
//
//    			}else {
//    				if(StringUtil.isEmpty(name) == false&&!DateUtil.isValidDateStr(name)){
//    					validateSearch(filedName, this.getText("date.format.invalid"));
//    				} 
//    			}
//    	    	if (getFieldList().isEmpty()) {
//    	    	if (!StringUtil.isEmpty(extendName)&&!DateUtil.isDateRegex(extendName)) {
//    	    		validateSearch(filedName+"Temp", this.getText("date.format.error",new String[]{DateUtil.getDateFormat().toUpperCase()}));
//
//    			}else {
//    				if(!StringUtil.isEmpty(extendName)&&!DateUtil.isValidDateStr(extendName)){
//    					validateSearch(filedName+"Temp", this.getText("date.format.invalid"));
//    				} 
//    			}
//    	    	}
//    	    	
//    	 }
//    }
	
}
