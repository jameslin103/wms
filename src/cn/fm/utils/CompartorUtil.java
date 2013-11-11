package cn.fm.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.beanutils.PropertyUtils;


@SuppressWarnings("unchecked")
public class CompartorUtil implements Comparator{
	  protected String propertyName;
	    protected boolean reverse;

	    private CompartorUtil() {
	    }

	    private CompartorUtil(String propertyName, boolean reverse ) {
	        this.propertyName = propertyName;
	        this.reverse = reverse;
	    }

	    public static Comparator creatComparator(String propertyName, boolean reverse) {
	        return new CompartorUtil(propertyName, reverse);
	    }

	    public int compare(Object o1, Object o2) {
	        Object value1 = getPropertyValue(o1, propertyName);
	        Object value2 = getPropertyValue(o2, propertyName);

	        if (reverse) {
	            return -((Comparable) (value1))
	                    .compareTo(value2);
	        }else{
	            return ((Comparable) (value1))
	                    .compareTo(value2);
	        }
	    }
	    
	    public static Object getPropertyValue(Object bean, String propertyName) {
	        if (bean != null && !StringUtil.isEmptyStr(propertyName) && hasProperty(bean,propertyName)) {
	            try {
	              return  PropertyUtils.getProperty(bean,propertyName);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return null;
	    }
	    
	    public static boolean hasProperty(Object bean, String propertyName) 
	    {
	        if (bean == null || StringUtil.isEmptyStr(propertyName)) {
	            return false;
	        }
	        Map propertiesMap = getPropertiesMap(bean);
	        return propertiesMap.containsKey(propertyName);
	    }
	    
	    public static Map getPropertiesMap(Object bean) {
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
