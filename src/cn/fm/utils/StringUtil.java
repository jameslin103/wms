package cn.fm.utils;



public class StringUtil {
	public static boolean isEmpty(String source) {
        if (source == null || source.length() == 0)
            return true;
        return false;
    }
    public static boolean isEmptyStr(String source) {
        if (source == null || source.trim().length() == 0)
            return true;
        return false;
    }
    public static boolean isStringArrayEmpty(String[] source) {
        if (source == null || source.length == 0)
            return true;
        return false;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
