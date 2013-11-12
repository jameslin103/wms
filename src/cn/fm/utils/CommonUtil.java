package cn.fm.utils;

import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

public class CommonUtil {
	public static Query addQueryParams(Query query, Map params) {
		Set keys = params.keySet();
		for (Object obj : keys) {
			String key = (String) obj;
			query.setParameter(key, params.get(key));
		}
		return query;
	}
}
