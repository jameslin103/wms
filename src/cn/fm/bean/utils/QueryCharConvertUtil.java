package cn.fm.bean.utils;

import cn.fm.utils.StringUtil;


public class QueryCharConvertUtil {
    public static String converStr(String source){
        if(StringUtil.isEmptyStr(source)) return source;
        if(source.contains("'")){
            source = convertSingleQuotes(source);
        }
        return source;
    }

    public static String escapeQueryLikeStr(String source) {
        if(StringUtil.isEmptyStr(source)) return source;
        source = convertSlash(source);
        source = convertUnderline(source);
        source = convertPERCENT(source);
        source = convertSingleQuotes(source);
        return source;
    }

    public static String convertSingleQuotes(String source){
      return  source.replaceAll("'","''");
    }

    public static String convertUnderline(String source){
       return source.replaceAll("_", "/_");
    }

    public static String convertPERCENT(String source){
       return source.replaceAll("%", "/%");
    }

    public static String convertSlash(String source){
       return source.replaceAll("/", "//");
    }


}
