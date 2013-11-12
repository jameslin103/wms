package cn.fm.bean;

import java.util.List;


import cn.fm.utils.Constant;

public class PageData {

	private List datals = null;
	private int totalRows = 0;
	private int pagesize = Constant.WMS_PAGE_SIZE;
	private int currentPage = 0;
	private int fromRowId = 0;
	private int toRowId = 0;
	private int totalPageNum = 0;
	private String hql="";
	
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public List getDatals() {
		return datals;
	}
	public void setDatals(List datals) {
		this.datals = datals;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getFromRowId() {
		return fromRowId;
	}
	public void setFromRowId(int fromRowId) {
		this.fromRowId = fromRowId;
	}
	public int getToRowId() {
		return toRowId;
	}
	public void setToRowId(int toRowId) {
		this.toRowId = toRowId;
	}
	public int getTotalPageNum() {
		return totalPageNum;
	}
	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}
	public int getNextPage(){
		/*currentPage = currentPage + 1;
		return currentPage;*/
		return this.currentPage + 1;
	}
	public int getPrePage(){
		/*currentPage = currentPage - 1;
		return currentPage;*/		
		return this.currentPage - 1;
	}
	public String getHql() {
		return hql;
	}
	public void setHql(String hql) {
		this.hql = hql;
	}
}
