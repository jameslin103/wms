package cn.fm.utils;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtils;

@SuppressWarnings("unchecked")
public class BeanPropertyUtil {
	 public static boolean hasProperty(Object bean, String propertyName) 
	 {
	        if (bean == null || StringUtil.isEmptyStr(propertyName)) {
	            return false;
	        }
	        Map propertiesMap = getPropertiesMap(bean);
	        return propertiesMap.containsKey(propertyName);
	 }
	 
	public static Map getPropertiesMap(Object bean) 
	 {
	        Map propertiesMap = new HashMap();
	        try {
	            propertiesMap = PropertyUtils.describe(bean);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        if (propertiesMap.containsKey("class")) propertiesMap.remove("class");
	        return propertiesMap;
	    }
}
