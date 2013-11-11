package cn.fm.service.base;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import cn.fm.bean.GridParameter;
import cn.fm.bean.PageData;
import cn.fm.bean.QuerySymbol;
import cn.fm.bean.SearchBean;
import cn.fm.bean.utils.AbstractPageQueryUtil;
import cn.fm.bean.utils.QueryCharConvertUtil;
import cn.fm.bean.utils.QueryUtil;
import cn.fm.utils.Constant;
import cn.fm.utils.DateUtil;
import cn.fm.utils.StringUtil;

/**
 * Date 2013-11-10
 *
 * @author jameslin
 * @version 1.0
 */
public class SearchImpl{

    private JpaDAO  dao;

    
    
    public void setDao(JpaDAO dao) {
		this.dao = dao;
	}

	public BaseGrid findPage(GridParameter gridParameter, BaseGrid baseGrid) {
        String filterParams = baseGrid.getFilterParams();
        String objectAlias = gridParameter.getObjectAlias();
        String hql = makeSearhHQL(filterParams, gridParameter);
        String orderBy = makeSortStr(baseGrid, objectAlias);
        PageData pageData = findPageData(hql, baseGrid.getPage(), baseGrid.getRp(), orderBy);
        injectQueryResult(pageData, baseGrid);
        return baseGrid;
    }

    public BaseGrid findPageByList(BaseGrid baseGrid,List list){
	    String sortName = baseGrid.getSortname();
        String sortOrder = baseGrid.getSortorder();
    	PageData pageData = dao.findPageByList(baseGrid.getPage(), baseGrid.getRp(),sortName,sortOrder,list);
    	injectQueryResult(pageData, baseGrid);
    	return baseGrid;
    }

    public BaseGrid findPageByListNoSort(BaseGrid baseGrid,List list){
    	PageData pageData = dao.findPageByList(baseGrid.getPage(), baseGrid.getRp(),list);
    	injectQueryResult(pageData, baseGrid);
    	return baseGrid;
    }

    public BaseGrid findPageHQL(GridParameter gridParameter, BaseGrid baseGrid) {
        String objectAlias = gridParameter.getObjectAlias();
        String orderBy = makeSortStr(baseGrid, objectAlias);
        String initialHQL = makeInitHql(gridParameter).toString();
        String filterHQL = gridParameter.getFilterHQL();
        PageData pageData = findPageData(correctSearchHQL(initialHQL,filterHQL), baseGrid.getPage(), baseGrid.getRp(), orderBy);
        injectQueryResult(pageData, baseGrid);
        return baseGrid;
    }

    public String makeSearhHQL(String filterParams, GridParameter gridParameter) {
        List<SearchBean> searchBeanList = buildSearchBeans(gridParameter.getModuleName(), filterParams);
        String initialHQL = makeInitHql(gridParameter).toString();
        String filterHQL = makeHQLFromSearchBean(searchBeanList, gridParameter.getObjectAlias());
        return correctSearchHQL(initialHQL,filterHQL);
    }

    public String correctSearchHQL(String initialHQL,String filterHQL){
        if(initialHQL.contains(QuerySymbol.WHERE)){
            return  StringUtil.isEmptyStr(filterHQL)?initialHQL
                    :initialHQL  + QuerySymbol.BLANK_AND_BLANK + filterHQL ;
        }else{
           return  StringUtil.isEmptyStr(filterHQL)?initialHQL
                    :initialHQL +QuerySymbol.BLANK_WHERE_BLANK + filterHQL;
        }
    }
    

    public StringBuffer makeInitHql(GridParameter gridParameter) {
        StringBuffer hql = new StringBuffer();
        hql.append(makeIniHQLByDefaultHQL(gridParameter))
                .append(makeIniHQLByObject(gridParameter));
        hql = initialGroupQuery(hql);
        return hql;
    }

    public StringBuffer makeIniHQLByDefaultHQL(GridParameter gridParameter) {
        String defaultHQL = gridParameter.getDefaultHQL();
        String defaultValue = gridParameter.getDefaultValue();
        StringBuffer buffer = new StringBuffer("");
        if (StringUtil.isEmpty(defaultHQL)) return buffer;
        if (StringUtil.isEmptyStr(defaultValue)) return buffer.append(defaultHQL);
        if (defaultHQL.contains(QuerySymbol.WHERE))
            return buffer.append(defaultHQL).append(QuerySymbol.BLANK_AND_BLANK)
                    .append(defaultValue);
        return buffer.append(defaultHQL).append(QuerySymbol.BLANK_WHERE_BLANK)
                .append(defaultValue);

    }

    public StringBuffer makeIniHQLByObject(GridParameter gridParameter) {
        String defaultValue = gridParameter.getDefaultValue();
        String objectName = gridParameter.getObjectName();
        String objectAlias = gridParameter.getObjectAlias();
        boolean isIgnoreDefaultValueObjectAlias = gridParameter.getIsIgnoreDefaultValueObjectAlias();
        StringBuffer buffer = new StringBuffer("");
        if (StringUtil.isEmptyStr(objectName) || StringUtil.isEmptyStr(objectAlias)) return buffer;
        buffer.append(QuerySymbol.FROM).append(QuerySymbol.BLANK).append(objectName).append(QuerySymbol.BLANK).append(objectAlias);
        if (!StringUtil.isEmptyStr(defaultValue)) {
            buffer.append(QuerySymbol.BLANK_WHERE_BLANK);
            if(!isIgnoreDefaultValueObjectAlias)
                    buffer.append(objectAlias).append(QuerySymbol.POINT);
            buffer.append(defaultValue);
        }
        return buffer;
    }

    public StringBuffer initialGroupQuery(StringBuffer buffer) {
        if (buffer == null || buffer.length() == 0) return buffer;
        String bufferStr = buffer.toString();
        if (!bufferStr.contains(QuerySymbol.HAVING)) return buffer;
        if (bufferStr.contains(QuerySymbol.WHERE))
            return buffer.append(QuerySymbol.BLANK_WHERE_BLANK);
        return buffer;
    }


    public String makeSortStr(BaseGrid baseGrid, String objectAlias) {
        String sortName = baseGrid.getSortname();
        String sortOrder = baseGrid.getSortorder();
        String alias = baseGrid.getAlis();
        String fullSortName = makeFullSortName(sortName, objectAlias, alias);
        return makeOrderStr(fullSortName, objectAlias, sortOrder);
    }

    public String makeFullSortName(String sortName, String objectAlias, String alias) {
        if (StringUtil.isEmptyStr(sortName)) return "";
        String fullSortName = sortName;
        if (!Constant.WMS_SEARCH_FALSE.equals(alias)) {
            fullSortName = objectAlias + QuerySymbol.POINT + fullSortName;
        }
        return fullSortName;
    }

    public String makeOrderStr(String fullSortName, String objectAlias, String sortOrder) {
        StringBuffer orderBy = new StringBuffer("");
        if (StringUtil.isEmptyStr(fullSortName)
                || StringUtil.isEmptyStr(objectAlias)
                || StringUtil.isEmptyStr(sortOrder))
            return orderBy.toString();
        String fullIdSortName = objectAlias + QuerySymbol.POINT + QuerySymbol.ID_SORT_NAME;
        orderBy.append(fullSortName)
                .append(QuerySymbol.BLANK)
                .append(sortOrder);
        if (!fullIdSortName.equals(fullSortName)) {
            orderBy.append(QuerySymbol.COMMA).append(fullIdSortName).append(QuerySymbol.BLANK).append(QuerySymbol.ASC);
        }
        return orderBy.toString();
    }

    public boolean isSinglePageSort(String qtype) {
        return !StringUtil.isEmptyStr(qtype);
    }

    public String makeHQLFromSearchBean(List<SearchBean> searchBeanList, String objectAlias) {
        StringBuffer hql = new StringBuffer();
        if (searchBeanList == null || searchBeanList.isEmpty()) return hql.toString();
        Iterator<SearchBean> iterator = searchBeanList.iterator();
        while (iterator.hasNext()){
            SearchBean searchBean = iterator.next();
            String properyPrefix = makePropertyPrefix(searchBean.getAlis(), objectAlias);
            if (Constant.WMS_FELX_SEARCH_DATE.equals(searchBean.getType()))
                hql.append(makeDateTypeHql(searchBean, properyPrefix));
            else {
//                String fieldType = getDefindFieldType(searchBean.getModuleName(), searchBean.getSearchName());
//                hql.append(makeStrTypeHql(searchBean, properyPrefix, fieldType));
            }
            if(iterator.hasNext())
                hql.append(QuerySymbol.BLANK_AND_BLANK);
        }
        return hql.toString();
    }

    public String makePropertyPrefix(String alias, String objectAlias) {
        if (!StringUtil.isEmptyStr(alias)) {
            return alias + QuerySymbol.POINT;
        } else if (!StringUtil.isEmptyStr(objectAlias)) {
            return objectAlias + QuerySymbol.POINT;
        } else {
            return "";
        }
    }

    public StringBuffer makeDateTypeHql(SearchBean searchBean, String properyPrefix) {
        StringBuffer hql = new StringBuffer();
        if (!setSearchBean(searchBean, true)) return hql;
        String symbol = searchBean.getSymbol();
        if (symbol.equals(QuerySymbol.EQ) || symbol.equals(QuerySymbol.BT)) {
            hql.append(makeEQOrBTDateTypeHQL(searchBean, properyPrefix));
        } else {
            hql.append(makeGTOrLSDateTypeHQL(searchBean, properyPrefix));
        }
        return hql;
    }

    public StringBuffer makeEQOrBTDateTypeHQL(SearchBean searchBean, String properyPrefix) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(QuerySymbol.BLANK).append(properyPrefix).append(searchBean.getSearchName())
                .append(QuerySymbol.GT_EQ_STR).append(QuerySymbol.TO_DATE).append(QuerySymbol.L_BRACKET).append(QuerySymbol.SINGLE_QUOTES)
                .append(searchBean.getDefaultValue()).append(QuerySymbol.QUOTES_COMMA).append(DateUtil.FORMAT_DATE)
                .append(QuerySymbol.SINGLE_QUOTES).append(QuerySymbol.R_BRACKET).append(QuerySymbol.BLANK_AND_BLANK)
                .append(properyPrefix).append(searchBean.getSearchName()).append(QuerySymbol.BLANK_LS_BLANK_STR).append(QuerySymbol.TO_DATE)
                .append(QuerySymbol.L_BRACKET).append(QuerySymbol.SINGLE_QUOTES).append(searchBean.getExtendValue()).append(QuerySymbol.QUOTES_COMMA)
                .append(DateUtil.FORMAT_DATE).append(QuerySymbol.SINGLE_QUOTES).append(QuerySymbol.R_BRACKET).append("+1");
        return buffer;
    }

    public StringBuffer makeGTOrLSDateTypeHQL(SearchBean searchBean, String properyPrefix) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(QuerySymbol.BLANK)
        .append(QuerySymbol.TO_DATE).append(QuerySymbol.L_BRACKET)
        .append(QuerySymbol.TO_CHAR)
        .append(properyPrefix).append(searchBean.getSearchName())
        .append(QuerySymbol.COMMA).append(QuerySymbol.SINGLE_QUOTES).append(DateUtil.FORMAT_DATE)
        .append(QuerySymbol.SINGLE_QUOTES).append(QuerySymbol.R_BRACKET)
        .append(QuerySymbol.COMMA).append(QuerySymbol.SINGLE_QUOTES)
        .append(DateUtil.FORMAT_DATE)
        .append(QuerySymbol.SINGLE_QUOTES)
        .append(QuerySymbol.R_BRACKET)
        .append(QuerySymbol.BLANK)
                .append(QueryUtil.getSymbol(searchBean.getSymbol())).append(QuerySymbol.BLANK).append(QuerySymbol.TO_DATE).append(QuerySymbol.L_BRACKET).append(QuerySymbol.SINGLE_QUOTES)
                .append(searchBean.getDefaultValue()).append(QuerySymbol.QUOTES_COMMA).append(DateUtil.FORMAT_DATE).append(QuerySymbol.SINGLE_QUOTES).append(QuerySymbol.R_BRACKET)
                ;
        return buffer;
    }

    public StringBuffer makeStrTypeHql(SearchBean searchBean, String properyPrefix, String fieldType) {
        StringBuffer hql = new StringBuffer();
        if (!setSearchBean(searchBean, false)) return hql;
        String searchName = searchBean.getSearchName();
        if(!StringUtil.isEmptyStr(searchBean.getSearchNameAlias())){
            searchName = searchBean.getSearchNameAlias();
        }
        String symbol = searchBean.getSymbol();
        String defaultValue = searchBean.getDefaultValue();
        hql.append(QuerySymbol.BLANK).append(properyPrefix).append(searchName).append(QuerySymbol.BLANK).append(QueryUtil.getSymbol(symbol)).append(QuerySymbol.BLANK);
        if (symbol.equals(QuerySymbol.BT)) {
            hql.append(makeBTStrTypeHql(searchBean, fieldType));
        } else if (symbol.equals(QuerySymbol.LK)) {
            hql.append(makeLKStrTypeHql(defaultValue));
        } else {
            hql.append(makeEqOrGtOrLsStrTypeHql(defaultValue, fieldType));
        }
        return hql;
    }

    public StringBuffer makeBTStrTypeHql(SearchBean searchBean, String fieldType) {
        StringBuffer buffer = new StringBuffer();
        if (QuerySymbol.INTEGER.equals(fieldType)) {
            buffer.append(Integer.parseInt(searchBean.getDefaultValue())).append(QuerySymbol.BLANK_AND_BLANK)
                    .append(Integer.parseInt(searchBean.getExtendValue()));
        } else {
            buffer.append(QuerySymbol.SINGLE_QUOTES).append(searchBean.getDefaultValue())
                    .append(QuerySymbol.SINGLE_QUOTES).append(QuerySymbol.BLANK_AND_BLANK).append(QuerySymbol.SINGLE_QUOTES)
                    .append(searchBean.getExtendValue()).append(QuerySymbol.SINGLE_QUOTES);
        }
        return buffer;
    }

    public StringBuffer makeLKStrTypeHql(String defaultValue) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(QuerySymbol.SINGLE_QUOTES).append(QuerySymbol.PERCENT)
                .append(QueryCharConvertUtil.escapeQueryLikeStr(defaultValue)).append(QuerySymbol.ESCAPE_SPLICE);
        return buffer;
    }

    public StringBuffer makeEqOrGtOrLsStrTypeHql(String defaultValue, String fieldType) {
        StringBuffer buffer = new StringBuffer();
        if (QuerySymbol.INTEGER.equals(fieldType) || defaultValue.contains(QuerySymbol.TO_DATE)) {
            buffer.append(Integer.parseInt(defaultValue));
        } else{                               
            defaultValue = QueryCharConvertUtil.converStr(defaultValue);
            buffer.append(QuerySymbol.SINGLE_QUOTES).append(defaultValue).append(QuerySymbol.SINGLE_QUOTES);
        }
        return buffer;
    }

    public boolean setSearchBean(SearchBean searchBean, boolean isDate) {
        boolean isExistValue = true;
        if (StringUtil.isEmptyStr(searchBean.getDefaultValue()) && !StringUtil.isEmptyStr(searchBean.getExtendValue())) {
            searchBean.setDefaultValue(searchBean.getExtendValue());
            if (searchBean.getSymbol().equals(QuerySymbol.BT)) {
//                if (isDate) searchBean.setDefaultValue(DateUtil.getNextDay(searchBean.getDefaultValue()));
                searchBean.setSymbol(QuerySymbol.LS_EQ);
            }

        }
        if (!StringUtil.isEmptyStr(searchBean.getDefaultValue()) && StringUtil.isEmptyStr(searchBean.getExtendValue())) {
            searchBean.setExtendValue(searchBean.getDefaultValue());
            if (searchBean.getSymbol().equals(QuerySymbol.BT)) {
                searchBean.setSymbol(QuerySymbol.GT_EQ);
            }
        }

        if (StringUtil.isEmptyStr(searchBean.getDefaultValue()) && StringUtil.isEmptyStr(searchBean.getExtendValue())) {
            isExistValue = false;
        }
        if (isExistValue && isDate) {
            searchBean.setDefaultValue(searchBean.getDefaultValue());
            searchBean.setExtendValue(searchBean.getExtendValue());
        }
        return isExistValue;


    }



    public List<SearchBean> buildSearchBeans(String moduleName, String queryParamsStr) {
        if (!isValidFilterParams(queryParamsStr))
            return null;
        String queryParamArray[] = splitQueryParamsStr(queryParamsStr);
        if (queryParamArray == null || queryParamArray.length == 0)
            return null;
        List<SearchBean> searchList = new ArrayList<SearchBean>();
        for (String queryParam : queryParamArray) {
            SearchBean searchBean = buildSearchBean(moduleName, queryParam);
            if (searchBean == null) continue;
            searchList.add(searchBean);
        }
        return searchList;
    }

    public boolean isValidFilterParams(String filterParams){
       if(StringUtil.isEmptyStr(filterParams))
            return false;
       String pattern = ".*#.*#.*#.*#.*\\|?";
       if(!filterParams.matches(pattern))
            return false;
        String[] splices = splitQueryParamsStr(filterParams);
        for(int i=0;i<splices.length;i++){
            if(isValidSingleFilterParam(splices[i])) return true;
        }
       return false;
    }

    public boolean isValidSingleFilterParam(String filterParams){
        if(StringUtil.isEmptyStr(filterParams))
            return false;
        String patter = "^\\w[\\w.]+#\\d#.+#.*#.*";
        String betweenPatter="^\\w[\\w.]+#\\d#.*#.+#.*";
        if(!filterParams.matches(patter))
        	if(!filterParams.matches(betweenPatter))
            return false;
        return true;
    }

    public String[] splitQueryParamsStr(String queryParamsStr) {
        if (StringUtil.isEmptyStr(queryParamsStr)) {
            return null;
        }
        return queryParamsStr.split("\\|");
    }

    public SearchBean buildSearchBean(String moduleName, String queryParamStr) {
        SearchBean searchBean = setUpSearchBean(moduleName,queryParamStr);
        if(searchBean!=null){
            setAlisName(moduleName, searchBean);
        }
        return searchBean;
    }

    public SearchBean buildSearchBean(String moduleName, String queryParamStr,boolean isSQL) {
         SearchBean searchBean = setUpSearchBean(moduleName,queryParamStr);
        if(searchBean!=null){
            setAlisName(moduleName, searchBean);
        }
        return searchBean;
    }
    public void setAlisName(String moduleName, SearchBean searchBean) {
        if (!StringUtil.isEmptyStr(moduleName) && searchBean != null &&
                !StringUtil.isEmptyStr(searchBean.getSearchName())) {
           //setUpDefindAlis(searchBean);
        }
    }
    public SearchBean setUpSearchBean(String moduleName,String queryParamStr){
        if (StringUtil.isEmptyStr(moduleName) || !StringUtil.isSplitType(queryParamStr, "#")) return null;
        SearchBean searchBean = new SearchBean();
        searchBean.setModuleName(moduleName);
        String[] parameters = queryParamStr.split("#");
        if (parameters.length > 0)
            searchBean.setSearchName(parameters[0]);
        if (parameters.length > 1)
            searchBean.setSymbol(parameters[1]);
        if (parameters.length > 2)
            searchBean.setDefaultValue(parameters[2]);
        if (parameters.length > 3)
            searchBean.setExtendValue(parameters[3]);
        if (parameters.length > 4)
            searchBean.setType(parameters[4]);
        if(searchBean.getIsEnvalidValue()) return null;
        return  searchBean;
    }

    public List<SearchBean> buildSearchBeans(String moduleName, String queryParamsStr,boolean isSQL) {
        if (!isValidFilterParams(queryParamsStr))
            return null;
        String queryParamArray[] = splitQueryParamsStr(queryParamsStr);
        if (queryParamArray == null || queryParamArray.length == 0)
            return null;
        List<SearchBean> searchList = new ArrayList<SearchBean>();
        for (String queryParam : queryParamArray) {
            SearchBean searchBean = buildSearchBean(moduleName, queryParam,isSQL);
            if (searchBean == null) continue;
            searchList.add(searchBean);
        }
        return searchList;
    }



    public void setFieldAlisName(String moduleName, SearchBean searchBean) {
        if (!StringUtil.isEmptyStr(moduleName) && searchBean != null && !
                StringUtil.isEmptyStr(searchBean.getSearchName())) {
        }
    }


   

    public void injectQueryResult(PageData pageData, BaseGrid baseGrid) {
        baseGrid.setTotal(pageData.getTotalRows());
        baseGrid.setCountPage(pageData.getPagesize());
        baseGrid.setGridList(pageData.getDatals());
        baseGrid.setQuery(pageData.getHql());
    }

    public SearchBean drawOutSpecifySearchBean(List<SearchBean> searchBeans,String searchName){
        if(searchBeans==null ||searchBeans.isEmpty() || StringUtil.isEmptyStr(searchName))
            return null;
        int j = -1;
        for(int i=0;i<searchBeans.size();i++){
            SearchBean bean = searchBeans.get(i);
            if(bean!=null && searchName.equals(bean.getSearchName())){
                j=i;
                break;
            }
        }
        if(j==-1)
            return null;
        SearchBean target = searchBeans.get(j);
        searchBeans.remove(j);
        return target;
    }

    public SearchBean getSpecifySearchBean(List<SearchBean> searchBeans,String searchName){
        if(searchBeans==null ||searchBeans.isEmpty() || StringUtil.isEmptyStr(searchName))
            return null;
        for(SearchBean bean : searchBeans){
            if(searchName.equals(bean.getSearchName()))
                return bean;
        }
        return null;
    }

    public void removeSpecifySearchBean(List<SearchBean> searchBeans,String searchName){
        if(searchBeans==null ||searchBeans.isEmpty() || StringUtil.isEmptyStr(searchName))
            return ;
        int j = -1;
        for(int i=0;i<searchBeans.size();i++){
            SearchBean bean = searchBeans.get(i);
            if(bean!=null && searchName.equals(bean.getSearchName())){
                j=i;
                break;
            }
        }
        if(j==-1)
            return;
        searchBeans.remove(j);
    }

    public String getQueryFieldSearchParam(String searchName, String queryParamsStr) {
        if (StringUtil.isEmpty(searchName) || isValidFilterParams(queryParamsStr))
            return null;
        String[] queryFieldsParam = splitQueryParamsStr(queryParamsStr);
        for (String fieldParam : queryFieldsParam) {
            if (fieldParam.startsWith(searchName) && isValidSingleFilterParam(fieldParam))
                return fieldParam;
        }
        return null;
    }

    public PageData findPageData(String hql, int toPage, int pageSize, String orderBy) {
        return dao.findByPage(hql, null, toPage, pageSize, orderBy);
    }

    public String getSpecifyQueryFieldStr(String searchName,String queryParamsStr){
          if (StringUtil.isEmpty(searchName) || StringUtil.isEmptyStr(queryParamsStr) || !queryParamsStr.contains(searchName)
               || splitQueryParamsStr(queryParamsStr).length == 0)
            return "";
        String[] queryFieldsParam = splitQueryParamsStr(queryParamsStr);
        for (String fieldParam : queryFieldsParam) {
            String[]  targetArray= fieldParam.split("#");
            if (targetArray.length>0 && targetArray[0].equals(searchName))
                return fieldParam;

        }
       return "";
    }

    public String removeSpecifyQueryFieldStr(String searchName,String queryParamsStr){
         if (StringUtil.isEmpty(searchName) || StringUtil.isEmptyStr(queryParamsStr) || !queryParamsStr.contains(searchName)
               || splitQueryParamsStr(queryParamsStr).length == 0)
            return queryParamsStr;
        String[] queryFieldsParam = splitQueryParamsStr(queryParamsStr);
        if(queryFieldsParam.length==0) return queryParamsStr;
        StringBuffer temp = new StringBuffer("");
         for (String fieldParam : queryFieldsParam) {
            String[]  targetArray= fieldParam.split("#");
            if (targetArray.length>0 && targetArray[0].equals(searchName))
                continue;
             temp.append(fieldParam).append("|");
        }
        if(temp.length()>0){
            temp = new StringBuffer(temp.substring(0,temp.lastIndexOf("|")));

        }
        return temp.toString();
    }


     public String makeSQLFromSearchBean(List<SearchBean> searchBeanList) {
        StringBuffer sql = new StringBuffer();
        if (searchBeanList == null || searchBeanList.isEmpty()) return sql.toString();
        Iterator<SearchBean> iterator = searchBeanList.iterator();
        while (iterator.hasNext()){
            SearchBean searchBean = iterator.next();
            if (Constant.WMS_FELX_SEARCH_DATE.equals(searchBean.getType()))
                sql.append(makeDateTypeHql(searchBean, ""));
            else {
//                String fieldType = getDefindFieldType(searchBean.getModuleName(), searchBean.getSearchName());
//                sql.append(makeStrTypeHql(searchBean, "", fieldType));
            }
            if(iterator.hasNext())
                sql.append(QuerySymbol.BLANK_AND_BLANK);
        }
        return sql.toString();
    }

    public BaseGrid findBySQL(BaseGrid baseGrid, AbstractPageQueryUtil pageQuery){
        if(StringUtil.isEmptyStr(pageQuery.getFilterSql())){
            List<SearchBean> searchBeans = buildSearchBeans(baseGrid.getModuleName(),baseGrid.getFilterParams());
             String filterSQL = makeSQLFromSearchBean(searchBeans);
             pageQuery.setFilterSql(filterSQL);
        }
        PageData pageData = dao.findPageDataSQL(pageQuery);
        injectQueryResult(pageData, baseGrid);
        return baseGrid;
    }

    public BaseGrid findByProcedure(BaseGrid baseGrid, AbstractPageQueryUtil pageQuery){
        if(StringUtil.isEmptyStr(pageQuery.getFilterSql())){
            List<SearchBean> searchBeans = buildSearchBeans(baseGrid.getModuleName(),baseGrid.getFilterParams());
            pageQuery.updateSearchBean(searchBeans);
            String filterSQL = makeSQLFromSearchBean(searchBeans);
            pageQuery.setFilterSql(filterSQL);
        }
        PageData pageData = dao.findPageDataProcedure(pageQuery);
        injectQueryResult(pageData, baseGrid);
        return baseGrid;
    }
}
