package cn.fm.service.base;

import java.util.List;
import java.util.Map;

import cn.fm.utils.StringUtil;


public class BaseGrid {

	 private Integer total; // total record
	    private Integer page = 1; // current page
	    private Integer countPage; // total page
	    private Integer rp = 10; // one page record
	    private String query; // search exp
	    private String type;
	    private String sortname; // sort name
	    private String sortorder; // sort way
	    private String qtype; // search type
	    private String states;
	    private String result;
	    private String alis;

	    private String filterParams;
	    private Map pMap;
	    private String defaultValue;
	    private String moduleName;
	    private String selectedIds;

	    public String getSelectedIds() {
	        return selectedIds;
	    }

	    public void setSelectedIds(String selectedIds) {
	        this.selectedIds = selectedIds;
	    }

	    public String getAlis() {
			return alis;
		}

		public void setAlis(String alis) {
			this.alis = alis;
		}

		public String getModuleName() {
			return moduleName;
		}

		public void setModuleName(String moduleName) {
			this.moduleName = moduleName;
		}

		public BaseGrid() {
	    }

	    public BaseGrid(Integer total, Integer page, Integer countPage, Integer rp,
	                    String query, String type, String sortname, String sortorder,
	                    String qtype, String states, List gridList) {
	        this.total = total;
	        this.page = page;
	        this.countPage = countPage;
	        this.rp = rp;
	        this.query = query;
	        this.type = type;
	        this.sortname = sortname;
	        this.sortorder = sortorder;
	        this.qtype = qtype;
	        this.states = states;
	        this.gridList = gridList;
	    }

	    public List gridList;// grid List


	    public List getGridList() {
	        return gridList;
	    }

	    public void setGridList(List gridList) {
	        this.gridList = gridList;
	    }


	    public Integer getCountPage() {
	        return countPage;
	    }

	    public void setCountPage(Integer countPage) {
	        this.countPage = countPage;
	    }

	    public String getSortorder() {
	        return sortorder;
	    }

	    public String getStates() {
	        return states;
	    }

	    public void setStates(String states) {
	        this.states = states;
	    }

	    public void setSortorder(String sortorder) {
	        this.sortorder = sortorder;
	    }

	    public String getQtype() {
	        return qtype;
	    }

	    public void setQtype(String qtype) {
	        this.qtype = qtype;
	    }

	    public Integer getTotal() {
	        return total;
	    }

	    public void setTotal(Integer total) {
	        this.total = total;
	    }

	    public Integer getPage() {
	        return page;
	    }

	    public void setPage(Integer page) {
	        this.page = page;
	    }

	    public Integer getRp() {
	        return rp;
	    }

	    public void setRp(Integer rp) {
	        this.rp = rp;
	    }

	    public String getQuery() {
	        return query;
	    }

	    public void setQuery(String query) {
	        this.query = query;
	    }

	    public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }

	    public String getSortname() {
	        return sortname;
	    }

	    public void setSortname(String sortname) {
	        this.sortname = convertSortName(sortname);
	    }

	    public String getFilterParams() {
	        return filterParams;
	    }

	    public void setFilterParams(String filterParams) {
	        this.filterParams = filterParams;
	    }

	    public Map getPMap() {
	        return pMap;
	    }

	    public void setPMap(Map pMap) {
	        this.pMap = pMap;
	    }

	    public String getDefaultValue() {
	        return defaultValue;
	    }

	    public void setDefaultValue(String defaultValue) {
	        this.defaultValue = defaultValue;
	    }

	    public String convertSortName(String sortname){
	        String covertedSortName = sortname;
	        if (!StringUtil.isEmptyStr(covertedSortName) && covertedSortName.endsWith("Str")) {
	            covertedSortName = covertedSortName.substring(0, covertedSortName.lastIndexOf("Str"));
	        }
	        return covertedSortName;
	    }

	    public void changeSortName(String otherSortName){
	        this.sortname = otherSortName;
	    }

	    public void changeSortName(String inputSortName,String otherSortName){
	        if(inputSortName.equals(sortname) && !StringUtil.isEmptyStr(sortorder)){
	            changeSortName(otherSortName);    
	        }
	    }

	    public void appendSortColumn(String inputSortColumn,String otherSortColumn){
	        StringBuffer newSortName = new StringBuffer();
	        if(inputSortColumn.equals(sortname) && !StringUtil.isEmptyStr(sortorder)){
	            newSortName.append(sortname).append(" ").append(sortorder)
	                    .append(",").append(otherSortColumn);
	        }
	        changeSortName(newSortName.toString());
	    }
	}
