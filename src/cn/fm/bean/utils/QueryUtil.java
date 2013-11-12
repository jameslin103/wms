package cn.fm.bean.utils;

import java.util.ArrayList;
import java.util.List;

import cn.fm.bean.QuerySymbol;
import cn.fm.bean.SearchBean;
import cn.fm.utils.Constant;
import cn.fm.utils.StringUtil;



public class QueryUtil implements QuerySymbol{
	  public static String genQuerySymbol(String symbol) {
	        StringBuffer hql = new StringBuffer();
	        if (GT.equals(symbol)) {
	            hql.append(GT_STR).append("? ");
	        } else if (EQ.equals(symbol)) {
	            hql.append(EQ_STR).append("? ");
	        } else if (LS.equals(symbol)) {
	            hql.append(LS_STR).append("? ");

	        } else if (BT.equals(symbol)) {
	            hql.append(" ").append(BT_STR).append(" ? ").append(AND).append(" ? ");
	        }
	        return hql.toString();
	    }

	    public static String getQuerySymbol(String symbol) {
	        if (GT.equals(symbol)) {
	            return GT_STR;
	        } else if (EQ.equals(symbol)) {
	            return EQ_STR;
	        } else if (LS.equals(symbol)) {
	            return LS_STR;
	        } else if (LK.equals(symbol)) {
	            return LK_STR;
	        }
	        return "";
	    }


	    public static List<SearchBean> parseGridFilterParam(String filterParam, String module) {
	        List<SearchBean> searchBeans = new ArrayList<SearchBean>();
	        if (!StringUtil.isEmptyStr(filterParam) && filterParam.length() > 1)
	            filterParam = filterParam.substring(0, filterParam.length() - 1);
	        if (!StringUtil.isSplitType(filterParam, Constant.WMS_GRID_FILTER_PARAM_SEPARATOR))
	            return searchBeans;
	        String[] parameters = filterParam.split("\\" + Constant.WMS_GRID_FILTER_PARAM_SEPARATOR);
	        for (String praram : parameters) {
	            SearchBean searchBean = buildSearchBean(praram, module);
	            if (!isValidSearchBean(searchBean)) continue;
	            searchBeans.add(searchBean);
	        }
	        return searchBeans;
	    }

	    public static SearchBean buildSearchBean(String queryParamStr, String module) {
	        if (!StringUtil.isSplitType(queryParamStr, Constant.WMS_GRID_FILTER_PARAM_VALUE_SEPARATOR))
	            return null;
	        SearchBean searchBean = new SearchBean();
	        String[] parameters = queryParamStr.split("\\" + Constant.WMS_GRID_FILTER_PARAM_VALUE_SEPARATOR);
	        if (parameters.length > 0) {
	            if (StringUtil.isEmptyStr(module)) {
	                searchBean.setSearchName(parameters[0]);
	            } else {
	                searchBean.setSearchName(module + "." + parameters[0]);
	            }
	        }
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

	    public static boolean isValidSearchBean(SearchBean searchBean) {
	        if (searchBean == null) return false;
	        return !StringUtil.isEmptyStr(
	                new String[]{searchBean.getSearchName(),
	                        searchBean.getSymbol(),
	                        searchBean.getDefaultValue(),
	                });
	    }


	    public static String generateQueryStr(String defaultSearhName, SearchBean searchBean) {
	        StringBuffer buffer = new StringBuffer();
	        String searchName = searchBean.getSearchName();
	        if (!StringUtil.isEmptyStr(defaultSearhName))
	            searchName = defaultSearhName;
	        buffer.append(searchName).append(" ").append(getQuerySymbol(searchBean.getSymbol())).append(" ");
	        String defaultValue = searchBean.getDefaultValue();
	        String defindFieldType = searchBean.getDefindFieldType();
	        String symbol = searchBean.getSymbol();
	        if (symbol.equals(QuerySymbol.LK)) {
	            buffer.append(makeLKStrTypeHql(defaultValue));
	        } else {
	            buffer.append(makeEqOrGtOrLsStrTypeHql(defaultValue, defindFieldType));
	        }
	        return buffer.toString();
	    }

	    public static StringBuffer makeLKStrTypeHql(String defaultValue) {
	        StringBuffer buffer = new StringBuffer();
	        buffer.append(QuerySymbol.SINGLE_QUOTES).append(QuerySymbol.PERCENT)
	                .append(QueryCharConvertUtil.escapeQueryLikeStr(defaultValue)).append(QuerySymbol.ESCAPE_SPLICE);
	        return buffer;
	    }

	    public static StringBuffer makeEqOrGtOrLsStrTypeHql(String defaultValue, String fieldType) {
	        StringBuffer buffer = new StringBuffer();
	        if (QuerySymbol.INTEGER.equals(fieldType) || defaultValue.contains(QuerySymbol.TO_DATE)) {
	            buffer.append(Integer.parseInt(defaultValue));
	        } else{
	            defaultValue = QueryCharConvertUtil.converStr(defaultValue);
	            buffer.append(QuerySymbol.SINGLE_QUOTES).append(defaultValue).append(QuerySymbol.SINGLE_QUOTES);
	        }
	        return buffer;
	    }


	    public static String getSymbol(String flag) {
	        flag = flag.trim();
	        String rs = "";
	        if (StringUtil.isEmpty(flag)) {
	            return rs;
	        }
	        if (flag.equals(GT)) {
	            rs = GT_STR;
	        }
	        if (flag.equals(EQ)) {
	            rs = EQ_STR;
	        }
	        if (flag.equals(LS)) {
	            rs = LS_STR;
	        }
	        if (flag.equals(BT)) {
	            rs = BT_STR;
	        }
	        if (flag.equals(LK)) {
	            rs = LK_STR;
	        }
	        if (flag.equals(LS_EQ)) {
	            rs = LS_EQ_STR;
	        }
	        if (flag.equals(GT_EQ)) {
	            rs = GT_EQ_STR;
	        }
	        return rs;
	    }
}
