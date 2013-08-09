package cn.fm.utils;


public class WMSResource {
	private static final String WMS_RESOURCES_ROOT="WMS_RESOURCES_ROOT";
	private static final String WMS_CLASSPATH_RESOURCES="WMS_RESOURCES_ROOT";
	
	/**
	 * Get the resources root from system setting which defined by java command -D property=value
	 * @return String
	 */
	public static String getResourcesRootPath(){
		return System.getProperty(WMS_RESOURCES_ROOT);
	}
	
	public static String getPathOfClasspath(){
		return System.getProperty("java.class.path");
	}
}
