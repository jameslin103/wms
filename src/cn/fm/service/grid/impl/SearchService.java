package cn.fm.service.grid.impl;


import java.util.ArrayList;
import java.util.List;

import cn.fm.bean.PageData;
import cn.fm.bean.QuerySymbol;
import cn.fm.bean.SearchBean;
import cn.fm.bean.utils.QueryCharConvertUtil;
import cn.fm.bean.utils.QueryUtil;
import cn.fm.service.base.BaseGrid;
import cn.fm.service.base.JpaDAO;
import cn.fm.utils.CollectionsUtil;
import cn.fm.utils.Constant;
import cn.fm.utils.DateUtil;
import cn.fm.utils.StringUtil;


public class SearchService {
	
	private JpaDAO  dao;

	public void setDao(JpaDAO dao) {
		this.dao = dao;
	}
	
	
	  /**
     * set basegrid init data
     */
    @SuppressWarnings("unchecked")
	public BaseGrid setBaseGrid(PageData pageData, boolean defiendSort, BaseGrid baseGrid) {
        int total = pageData.getTotalRows();
        int countPage = pageData.getPagesize();
        List gridList = pageData.getDatals();
        String query = pageData.getHql();
        if (!defiendSort) {
            if (baseGrid.getSortorder().equals(Constant.WMS_ORDER_DESC)) {
                gridList = CollectionsUtil.sortList(gridList, baseGrid.getSortname(), true);
            } else {
                gridList = CollectionsUtil.sortList(gridList, baseGrid.getSortname(), false);
            }

        }
        return baseGrid = new BaseGrid(total, baseGrid.getPage(), countPage, baseGrid
                .getRp(), query, null, baseGrid.getSortname(), baseGrid
                .getSortorder(), null, null, gridList);
    }

    /**
     * get sort type
     */
    public String getSortStr(String alias, BaseGrid baseGrid) {
        String orderby = "";
        String isAlis = baseGrid.getAlis();
        String sortName = baseGrid.getSortname();
        String sortOrder = baseGrid.getSortorder();
        if (!StringUtil.isEmptyStr(baseGrid.getQtype())) {
            orderby = "1";
        } else {
            orderby = setUpOrderStr(sortName, alias, isAlis, sortOrder);
        }
        return orderby;
    }

    public String setUpOrderStr(String sortName, String alias, String isAlis, String sortOrder) {
        StringBuffer orderBy = new StringBuffer("");
        if (StringUtil.isEmptyStr(sortName) || StringUtil.isEmptyStr(sortOrder))
            return orderBy.toString();
        if (StringUtil.isEmptyStr(alias))
            return orderBy.toString();
        String fullSortName = sortName;
        String idSortName = "id";
        String fullIdSortName = alias + "." + idSortName;
        if (!Constant.WMS_SEARCH_FALSE.equals(isAlis)) {
            fullSortName = alias + "." + fullSortName;
        }
        orderBy.append(fullSortName);
        orderBy.append(" ").append(sortOrder);
        if (!idSortName.equals(sortName)) {
            orderBy.append(",").append(fullIdSortName).append(" ").append("asc");
        }
        return orderBy.toString();
    }

    public StringBuffer initHql(String shql, String objectName, String alias, String defaultValue) {
        StringBuffer hql = new StringBuffer();
        if (!StringUtil.isEmptyStr(shql)) {
            hql = new StringBuffer(shql);
            if (!StringUtil.isEmptyStr(defaultValue)) {
                if (hql.indexOf("where") == -1) {
                    hql.append(" where " + defaultValue);
                } else {
                    hql.append(" and " + defaultValue);
                }
            }
        } else {
            hql.append(" from " + objectName + " " + alias);
            if (!StringUtil.isEmptyStr(defaultValue)) {
                hql.append(" where " + alias + "." + defaultValue);
            }
        }
        if (hql.indexOf("having") == -1) {
            if (hql.indexOf("where") == -1) {
                hql.append(" where ");
            } else {
                hql.append(" and ");
            }
        } else {
            hql.append(" and ");
        }
        return hql;
    }

    public boolean setSearchBean(SearchBean searchBean, boolean isDate) {
        boolean isExistValue = true;
        if (StringUtil.isEmptyStr(searchBean.getDefaultValue()) && !StringUtil.isEmptyStr(searchBean.getExtendValue())) {
            searchBean.setDefaultValue(searchBean.getExtendValue());
            if (searchBean.getSymbol().equals(QuerySymbol.BT)) {
                if (isDate) searchBean.setDefaultValue(DateUtil.getNextDay(searchBean.getDefaultValue()));
                searchBean.setSymbol(QuerySymbol.LS);
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
            searchBean.setDefaultValue(DateUtil.dateFormat(searchBean.getDefaultValue(), DateUtil.getDateFormat(), Constant.WMS_ACTIVITY_DATE));
            searchBean.setExtendValue(DateUtil.dateFormat(searchBean.getExtendValue(), DateUtil.getDateFormat(), Constant.WMS_ACTIVITY_DATE));
        }
        return isExistValue;


    }

    /**
     *
     * */

    public StringBuffer setDateTypeHql(SearchBean searchBean, String alisTable) {
        StringBuffer hql = new StringBuffer();
        if (!setSearchBean(searchBean, true)) return hql;
        String symbol = searchBean.getSymbol();
        if (symbol.equals(QuerySymbol.EQ) || symbol.equals(QuerySymbol.BT)) {
            hql.append(" " + alisTable
                    + searchBean.getSearchName()
                    + " >= to_date('"
                    + searchBean.getDefaultValue()
                    + "','" + Constant.WMS_ACTIVITY_DATE + "') and " + alisTable + searchBean.getSearchName() + " < to_date('"
                    + searchBean.getExtendValue()
                    + "','" + Constant.WMS_ACTIVITY_DATE + "')+1" + " and ");
        } else {
            hql.append(" " + alisTable
                    + searchBean.getSearchName()
                    + QueryUtil.getSymbol(symbol) + " to_date('"
                    + searchBean.getDefaultValue()
                    + "','" + Constant.WMS_ACTIVITY_DATE + "')" + " and ");
        }

        return hql;
    }

    /**
     * set hql
     */
    public StringBuffer setStrTypeHql(SearchBean searchBean, String alisTable) {
        StringBuffer hql = new StringBuffer();
        if (!setSearchBean(searchBean, false)) return hql;
        String moduleName = searchBean.getModuleName();
        String searchName = searchBean.getSearchName();
        String symbol = searchBean.getSymbol();
        String defaultValue = searchBean.getDefaultValue();
        String extendValue = searchBean.getExtendValue();
       
        return setStrTypeHql(searchName, symbol, defaultValue, extendValue, alisTable, null);
    }

    public StringBuffer setStrTypeHql(String searchName,
                                      String symbol,
                                      String defaultValue,
                                      String extendValue,
                                      String alisTable,
                                      String fieldType) {
        if (alisTable == null) alisTable = "";
        StringBuffer hql = new StringBuffer();
        hql.append(" ").append(alisTable).append(searchName).append(QueryUtil.getSymbol(symbol));
        if (symbol.equals(QuerySymbol.BT)) {
            if ("Integer".equals(fieldType)) {
                hql.append(Integer.parseInt(defaultValue)
                        + " and " + Integer.parseInt(extendValue) + " and ");
            } else {
                hql.append(" '" + defaultValue
                        + "' and '" + extendValue + "' and ");
            }
        } else if (symbol.equals(QuerySymbol.LK)) {
            hql.append(" '%"
                    + QueryCharConvertUtil.escapeQueryLikeStr(defaultValue) + "%' escape '/' and ");
        } else {
            String value = "";
            if ("Integer".equals(fieldType) || defaultValue.indexOf("to_date") != -1) {
                value = Integer.parseInt(defaultValue) + " and ";
            } else if ("String".equals(fieldType)) {
                value = " '" + defaultValue + "' and ";
            } else {
                value = " '" + defaultValue + "' and ";
            }
            hql.append(value);
        }
        return hql;
    }


    /**
     * set table alias
     */
    public String getAlias(String alias, SearchBean searchBean) {
        String alisTable = alias + ".";
        if (!StringUtil.isEmptyStr(searchBean.getAlis())) {
            alisTable = searchBean.getAlis() + ".";
        } else if (!StringUtil.isEmptyStr(alias)) {
            alisTable = alias + ".";
        } else {
            alisTable = "";
        }
        return alisTable;
    }

    /**
     * Generate hql
     *
     * @param searchBeanList ,tableName,alias,defaule,orderby
     * @return String
     */
    public String generateHQL(String defaultHql, List<SearchBean> searchBeanList,
                                String objectName, String alias, String defaultValue) {
        StringBuffer hql = new StringBuffer();
        hql = initHql(defaultHql, objectName, alias, defaultValue);
        hql.append(generateHQL(searchBeanList,alias));
        String result = "";
        if (hql.indexOf("and") == -1) {
            if (hql.lastIndexOf("where") != -1) {
                result = hql.substring(0, hql.lastIndexOf("where"));
            }
            if (hql.lastIndexOf("having") != -1) {
                result = hql.substring(0, hql.lastIndexOf("having"));
            }
        } else {
            result = hql.substring(0, hql.lastIndexOf("and"));
        }
        return result;
    }


    public String generateHQL(List<SearchBean> searchBeanList, String alias) {
        StringBuffer hql = new StringBuffer();
        if(searchBeanList==null ||searchBeanList.isEmpty()) return hql.toString();
        for (SearchBean searchBean : searchBeanList) {
            if (searchBean == null || searchBean.getIsFail()) continue;
            if (Constant.WMS_FELX_SEARCH_DATE.equals(searchBean.getType()))
                hql.append(setDateTypeHql(searchBean, getAlias(alias, searchBean)));
            else
                hql.append(setStrTypeHql(searchBean, getAlias(alias, searchBean)));

        }
        return hql.toString();
    }

    /**
     * to searchBean
     *
     * @param queryParamsStr
     * @return List<SearchBean>
     */

    public List<SearchBean> buildSearchBeans(String moduleName, String queryParamsStr) {
        if (StringUtil.isEmptyStr(queryParamsStr))
            return null;
        String queryParamArray[] = splitQueryParamsStr(queryParamsStr);
        if (queryParamArray == null || queryParamArray.length == 0)
            return null;
        List<SearchBean> searchList = new ArrayList<SearchBean>();
        for (String queryParam : queryParamArray) {
            SearchBean searchBean = buildSearchBean(moduleName, queryParam);
            //searchBean = setAlisName(moduleName, searchBean);
            if (searchBean == null) continue;
            searchList.add(searchBean);
        }
        return searchList;
    }

    public List<SearchBean> removeSpecifyBean(List<SearchBean> searchBeans,String searchName){
        if(searchBeans==null ||searchBeans.isEmpty() || StringUtil.isEmptyStr(searchName))
            return searchBeans;
        List<SearchBean> tempBeans =new ArrayList<SearchBean>();
        for(SearchBean bean : searchBeans){
            if(!searchName.equals(bean.getSearchName()))
                tempBeans.add(bean);
        }
        return tempBeans;
    }

    public SearchBean getSpecifyBean(List<SearchBean> searchBeans,String searchName){
        if(searchBeans==null ||searchBeans.isEmpty() || StringUtil.isEmptyStr(searchName))
            return null;
        for(SearchBean bean : searchBeans){
            if(searchName.equals(bean.getSearchName()))
               return bean;
        }
        return null;
    }

//    public String getDefindAlis(String moduleName, String fileName) {
//        if (StringUtil.isEmptyStr(moduleName) || StringUtil.isEmptyStr(fileName)) {
//            return "";
//        }
//        QueryParamXML queryParamXML = new QueryParamXML(false);
//        List<SearchDataBean> selectList = queryParamXML.getQueryParamsByModuleName(moduleName);
//        for (SearchDataBean searchDataBean : selectList) {
//            if (searchDataBean.getFieldName().equals(fileName)) {
//                return searchDataBean.getAlis();
//            }
//        }
//        return "";
//    }
//
//    public String getDefindFieldType(String moduleName, String fileName) {
//        if (StringUtil.isEmptyStr(moduleName) || StringUtil.isEmptyStr(fileName)) {
//            return "";
//        }
//        QueryParamXML queryParamXML = new QueryParamXML(false);
//        List<SearchDataBean> selectList = queryParamXML.getQueryParamsByModuleName(moduleName);
//        for (SearchDataBean searchDataBean : selectList) {
//            if (searchDataBean.getFieldName().equals(fileName)) {
//                return searchDataBean.getFieldType();
//            }
//        }
//        return "";
//    }

    public SearchBean getFilterSearchBean(String fileName, BaseGrid baseGrid) {
        if (StringUtil.isEmptyStr(fileName) || baseGrid == null) return null;
        List<SearchBean> searchBeanList = buildSearchBeans(baseGrid.getModuleName(), baseGrid
                .getFilterParams());
        if (searchBeanList == null || searchBeanList.size() == 0) {
            return null;
        }
        for (SearchBean searchBean : searchBeanList) {
            if (searchBean.getSearchName().equals(fileName)) {
                return searchBean;
            }
        }
        return null;
    }

    /**
     * get search validate field value
     */
    public String getDefaultValue(String fileName, BaseGrid baseGrid) {
        SearchBean searchBean = getFilterSearchBean(fileName, baseGrid);
        if (searchBean != null) return searchBean.getDefaultValue();
        return null;
    }

    public String getExtendValue(String fileName, BaseGrid baseGrid) {
        SearchBean searchBean = getFilterSearchBean(fileName, baseGrid);
        if (searchBean != null) return searchBean.getExtendValue();
        return null;
    }

    public String getSymbol(String fileName, BaseGrid baseGrid) {
        SearchBean searchBean = getFilterSearchBean(fileName, baseGrid);
        if (searchBean != null) return searchBean.getSymbol();
        return null;
    }

    public String generateHQL(String defaultHql, String objectName,
                              String defaultValue, String alias, BaseGrid baseGrid) {
        List<SearchBean> searchBeanList = buildSearchBeans(baseGrid.getModuleName(), baseGrid
                .getFilterParams());
        return generateHQL(defaultHql, searchBeanList, objectName,
                alias, defaultValue);

    }

    public String generateHQL(String objectName,
                              String defaultValue, String alias, BaseGrid baseGrid) {
        List<SearchBean> searchBeanList = buildSearchBeans(baseGrid.getModuleName(), baseGrid
                .getFilterParams());
        return generateHQL(null, searchBeanList, objectName, alias,
                defaultValue);
    }

    public String generateHQL(String defaultSearhName, SearchBean searchBean) {
        StringBuffer buffer = new StringBuffer();
        String searchName = searchBean.getSearchName();
        if (!StringUtil.isEmptyStr(defaultSearhName))
            searchName = defaultSearhName;
        buffer.append(searchName).append(" ").append(QueryUtil.getSymbol(searchBean.getSymbol())).append(" ");
        String defaultValue = searchBean.getDefaultValue();
        String defindFieldType = searchBean.getDefindFieldType();
        if ("Integer".equals(defindFieldType)) {
            buffer.append(Integer.parseInt(defaultValue));
        } else if ("Double".equals(defindFieldType)) {
            buffer.append(Double.parseDouble(defaultValue));
        } else {
            buffer.append(defaultValue);
        }
        return buffer.toString();
    }

    public String generateDateTypHQL(SearchBean searchBean) {
        if (!isValidSearchBean(searchBean) || !"date".equals(searchBean.getType())) return null;
        StringBuffer buffer = new StringBuffer();
        String searchName = searchBean.getSearchName();
        String defaultValue = searchBean.getDefaultValue();
        String extendValue = searchBean.getExtendValue();
        String symbol = searchBean.getSymbol();
        String alias = searchBean.getAlis();
        if (symbol.equals(QuerySymbol.BT)
                && !StringUtil.isEmptyStr(extendValue)) {
            buffer.append(generateToDatePeriodHQL(alias, searchName, defaultValue, extendValue));
        } else if (symbol.equals(QuerySymbol.EQ)) {
            buffer.append(generateToDateHQL(alias, searchName, defaultValue, symbol));
        } else {
            buffer.append(generateToDateHQL(alias, searchName, defaultValue, symbol));
        }
        return buffer.toString();

    }


    public String generateToDateHQL(String alias, String searchName, String value, String symbol) {
        StringBuffer buffer = new StringBuffer();
        if (!StringUtil.isEmptyStr(alias)) {
            searchName = alias + "." + searchName;
        }
        buffer.append(searchName).append(" ").append(QueryUtil.getSymbol(symbol)).append(" ")
                .append(generateDateFunctionHQL(value));
        return buffer.toString();
    }

    public String generateToDatePeriodHQL(String alias, String searchName, String start, String end) {
        StringBuffer buffer = new StringBuffer();
        if (!StringUtil.isEmptyStr(alias)) {
            searchName = alias + "." + searchName;
        }
        end = DateUtil.getNextDay(end);
        buffer.append(searchName).append(" ").append(QuerySymbol.BT_STR).append(" ")
                .append(generateDateFunctionHQL(start)).append(" ").append(QuerySymbol.AND).append(" ").append(generateDateFunctionHQL(end));
        return buffer.toString();
    }

    private String generateDateFunctionHQL(String value) {
        value = DateUtil.stringToString(value, DateUtil.FORMAT_DATE);
        StringBuffer buffer = new StringBuffer();
        return buffer.append(QuerySymbol.TO_DATE).append("('").append(value).append("','").append(DateUtil.FORMAT_DATE).append("')").toString();
    }


    public SearchBean assembleSearchBean(String moduleName, String searchName, String queryParamsStr) {
        if (queryParamsStr == null) return null;
        String fieldQueryParam = getQueryFieldSearchParam(searchName, queryParamsStr);
        if (StringUtil.isEmptyStr(fieldQueryParam)) return null;
        SearchBean searchBean = buildSearchBean(moduleName, fieldQueryParam);
        if (searchBean == null) return null;
      
        return searchBean;
    }

    public SearchBean buildSearchBean(String moduleName, String queryParamStr) {
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
        return searchBean;
    }

  
  
    
    public String getQueryFieldSearchParam(String searchName, String queryParamsStr) {
        if (StringUtil.isEmpty(searchName)
                || StringUtil.isEmptyStr(queryParamsStr)
                || !queryParamsStr.contains(searchName)
                || splitQueryParamsStr(queryParamsStr) == null
                || splitQueryParamsStr(queryParamsStr).length == 0)
            return null;
        String[] queryFieldsParam = splitQueryParamsStr(queryParamsStr);
        for (String fieldParam : queryFieldsParam) {
            if (fieldParam.startsWith(searchName))
                return fieldParam;
        }
        return null;
    }

    public String[] splitQueryParamsStr(String queryParamsStr) {
        if (StringUtil.isEmptyStr(queryParamsStr)) {
            return null;
        }
        return queryParamsStr.split("\\|");
    }

    public String filterQueryParam(String queryParamsStr, String searchName) {
        if (StringUtil.isEmptyStr(queryParamsStr)
                || StringUtil.isEmptyStr(searchName)
                || !queryParamsStr.contains(searchName)
                )
            return queryParamsStr;
        String[] params = splitQueryParamsStr(queryParamsStr);
        if (params == null) return null;
        if (params.length == 1 && params[0].startsWith(searchName)) return "";
        StringBuffer buffer = new StringBuffer();
        for (String param : params) {
            if (!param.startsWith(searchName))
                buffer.append(param).append("|");
        }
        return buffer.subSequence(0, buffer.lastIndexOf("|")).toString();
    }

    public boolean isValidSearchBean(SearchBean searchBean) {
        if (searchBean == null) return false;
        return !StringUtil.isEmptyStr(
                new String[]{searchBean.getModuleName(),
                        searchBean.getSearchName(),
                        searchBean.getDefaultValue(),
                        searchBean.getSymbol()});
    }

    public BaseGrid findPageByHQL(String defaultHql, String alias,
                                  BaseGrid baseGrid) {
        List<SearchBean> searchBeanList = buildSearchBeans(baseGrid.getModuleName(), baseGrid
                .getFilterParams());
        String orderby = "";
        boolean defiendSort = true;
        if (getSortStr(alias, baseGrid).equals(Constant.WMS_SEARCH_TRUE)) {
            defiendSort = false;
        } else {
            orderby = getSortStr(alias, baseGrid);
        }
        String hql = generateHQL(defaultHql, searchBeanList, "",
                alias, baseGrid.getDefaultValue());
        PageData pageData = dao.findPageData(hql, alias, null, baseGrid.getPage(),
                baseGrid.getRp(), orderby);
        return setBaseGrid(pageData, defiendSort, baseGrid);
    }

    public String genHQL(String defaultHQL, List<SearchBean> searchBeanList,
                         String alias) {
        StringBuffer hql = new StringBuffer();
        String result = "";
        hql = initHql(defaultHQL, "", alias, "");
        if (searchBeanList != null) {
            for (SearchBean searchBean : searchBeanList) {
                if (searchBean != null && !searchBean.getIsFail()) {
                    if (!StringUtil.isEmptyStr(searchBean.getType()) && searchBean.getType().equals(Constant.WMS_FELX_SEARCH_DATE)) {
                        hql.append(setDateTypeHql(searchBean, getAlias(alias, searchBean)));
                    } else {
                        hql.append(setStrTypeHql(searchBean, getAlias(alias, searchBean)));
                    }
                }
            }
        }
        if (hql.indexOf("and") == -1) {
            if (hql.lastIndexOf("where") != -1) {
                result = hql.substring(0, hql.lastIndexOf("where"));
            }
            if (hql.lastIndexOf("having") != -1) {
                result = hql.substring(0, hql.lastIndexOf("having"));
            }

        } else {
            result = hql.substring(0, hql.lastIndexOf("and"));
        }
        return result;
    }

    public String genFilterHql(List<SearchBean> searchBeanList, String alias) {
        StringBuffer hql = new StringBuffer();
        if (searchBeanList != null) {
            for (SearchBean searchBean : searchBeanList) {
                if (searchBean != null && !searchBean.getIsFail()) {
                    if (!StringUtil.isEmptyStr(searchBean.getType()) && searchBean.getType().equals(Constant.WMS_FELX_SEARCH_DATE)) {
                        hql.append(setDateTypeHql(searchBean, getAlias(alias, searchBean)));
                    } else {
                        hql.append(setStrTypeHql(searchBean, getAlias(alias, searchBean)));
                    }
                }
            }
        }
        if (hql.indexOf("and") == -1) return hql.toString();
        return hql.substring(0, hql.lastIndexOf("and"));

    }

	
	
	
	
	
	
	
}
