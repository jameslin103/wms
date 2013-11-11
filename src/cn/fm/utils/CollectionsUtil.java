package cn.fm.utils;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("unchecked")
public class CollectionsUtil {
	
	public static <WmsType> List<WmsType> sortList(List<WmsType> source, final String propertyName, final boolean reverse) {
        if (source == null || source.size() == 0) return source;
        if (BeanPropertyUtil.hasProperty(source.get(0), propertyName)) {
            Collections.sort(source, CompartorUtil.creatComparator(propertyName, reverse));
        }
        return source;
    }
	
	
}
