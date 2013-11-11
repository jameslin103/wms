package cn.fm.bean.utils;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.CallableStatement;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;


import cn.fm.bean.PageData;
import cn.fm.bean.QuerySymbol;
import cn.fm.bean.SearchBean;
import cn.fm.utils.Constant;
import cn.fm.utils.StringUtil;

@SuppressWarnings("unchecked")
public class AbstractPageQueryUtil {

    private Session session;
    private String sql;
    private int pageSize;
    private int toPage;
    private String defaultSortName;
    private String sortName;
    private String sortOrder;
    private String filterSql;
    private String sessionDivisionId;

    public String getSessionDivisionId() {
        return sessionDivisionId;
    }

    public void setSessionDivisionId(String sessionDivisionId) {
        this.sessionDivisionId = sessionDivisionId;
    }

    public String getFilterSql() {
        return filterSql;
    }

    public void setFilterSql(String filterSql) {
        this.filterSql = filterSql;
    }

    public SQLQuery addQueryColumn(SQLQuery sqlQuery) {
        return null;
    }

    
	public List setUpResult(List datals) {
        return null;
    }

    public List setUpResult(ResultSet resultSet) {
        return null;
    }

    public String getDefaultSortName() {
        return defaultSortName;
    }

    public void setDefaultSortName(String defaultSortName) {
        this.defaultSortName = defaultSortName;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getToPage() {
        return toPage;
    }

    public void setToPage(int toPage) {
        this.toPage = toPage;
    }

    public boolean isValidInputParameters() {
        if (StringUtil.isEmptyStr(sql)) {
            return false;
        } else {
            return true;
        }
    }

    public Integer getTotalrow(String fullSql) {
        StringBuffer fullCountsql = new StringBuffer(QuerySymbol.COUNT_ALL);
        fullCountsql.append(QuerySymbol.L_BRACKET);
        fullCountsql.append(fullSql);
        fullCountsql.append(QuerySymbol.R_BRACKET);
        Query query = session.createSQLQuery(fullCountsql.toString());
        BigDecimal totalrowResult = (BigDecimal) query.uniqueResult();
        return totalrowResult.intValue();
    }

    public PageData queryData() {
        StringBuffer fullSql = new StringBuffer(sql);
        if (!StringUtil.isEmptyStr(filterSql)) {
            fullSql.append(QuerySymbol.BLANK_AND_BLANK).append(filterSql);
        }
        int totalrow = getTotalrow(fullSql.toString());
        String orderBy = genSQLOrderBy();
        if (!StringUtil.isEmptyStr(orderBy)) {
            fullSql.append(QuerySymbol.BLANK).append(QuerySymbol.ORDER_BY).append(QuerySymbol.BLANK)
                    .append(orderBy);
        }
        SQLQuery sqlQuery = session.createSQLQuery(fullSql.toString());
        int size = pageSize;
        if (size <= 0) size = Constant.WMS_PAGE_SIZE;
        int fromrow = (toPage - 1) * size;
        sqlQuery.setFirstResult(fromrow);
        sqlQuery.setMaxResults(size);
        sqlQuery = addQueryColumn(sqlQuery);
        PageData pdata = new PageData();
        List datals = sqlQuery.list();
        datals = setUpResult(datals);
        pdata.setDatals(datals);

        pdata.setTotalRows(totalrow);
        int torowid = fromrow + size;
        if (torowid > totalrow) torowid = totalrow;
        pdata.setFromRowId(fromrow + 1);
        pdata.setToRowId(torowid);
        int pagenum = (totalrow % size == 0) ? totalrow / size : totalrow / size + 1;
        pdata.setPagesize(size);
        pdata.setTotalPageNum(pagenum);
        pdata.setCurrentPage(toPage);
        return pdata;
    }

    public String genSQLOrderBy() {
        StringBuffer orderBy = new StringBuffer();
        if (!StringUtil.isEmptyStr(sortName)) {
            orderBy.append(sortName).append(" ").append(sortOrder).append(",");
        }
        orderBy.append(defaultSortName).append(" ").append("asc");

        return orderBy.toString();
    }

    public PageData queryDataProcedure() {
        PageData pdata = new PageData();
        try {
            Connection connection = session.close();
            CallableStatement proc = connection.prepareCall(sql);
            proc.setInt(1, toPage);
            proc.setInt(2, pageSize);
            proc.setString(8, filterSql);
            proc.setString(9, sortOrder);
            proc.setString(10, genSQLOrderBy());
            proc.setString(11, sessionDivisionId);

            proc.registerOutParameter(3, 1);
            proc.registerOutParameter(4, 1);
            proc.registerOutParameter(5, 1);
            proc.registerOutParameter(6, 1);
            proc.registerOutParameter(7, 1);
            proc.execute();
            int totalPage = proc.getInt(3);
            int totalRows = proc.getInt(4);
            int fromRowId = proc.getInt(5);
            int toRowId = proc.getInt(6);
            pdata.setTotalPageNum(totalPage);
            pdata.setTotalRows(totalRows);
            pdata.setFromRowId(fromRowId);
            pdata.setToRowId(toRowId);
            pdata.setCurrentPage(toPage);
            pdata.setPagesize(pageSize);
            ResultSet resultSet = (ResultSet) proc.getObject(7);
            List datals = setUpResult(resultSet);
            resultSet.close();
            pdata.setDatals(datals);
        } catch (HibernateException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pdata;
    }

    public List<SearchBean> updateSearchBean(List<SearchBean> searchBeans){
        return searchBeans;
    }
}
