package cn.fm.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {
	    public final static String FORMAT_DATE = "yyyy-MM-dd";
	    public final static String FORMAT_DATE_MONTH = "yyyy年MM月";
	    public final static String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	    public final static String FORMAT_DB_DATE_TIME = "YYYY-MM-DD HH24:MI:SS";
	    public final static String FORMAT_DATE_YYYYMMMDD = "yyyyMMdd";
	    public final static String FORMAT_DATE_YYYYMMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
	    public final static String FORMAT_HOUR_TIME="HH:mm";

	


	    /**
	     * date to string and set formate and set locale
	     * @param date
	     * @param formatType
	     * @return
	     */
	    public final static String dateToString(Date date, String formatType) {
	        if (date == null) {
	            return "";
	        }
	        DateFormat dateFormat;
	        dateFormat = new SimpleDateFormat(formatType, Locale.ENGLISH);
	        dateFormat.setLenient(false);
	        return dateFormat.format(date);
	    }
	    public final static Date StringToDate(String strDate, String formatType) {
	        if (StringUtil.isEmpty(strDate)) {
	            return null;
	        }
	       
	        DateFormat dateFormat = new SimpleDateFormat(formatType, Locale.ENGLISH);
	        dateFormat.setLenient(false);
	        Date date = null;
			try {
				date = dateFormat.parse(strDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
	        return date;
	    }
	    
	    /**
	     * String to date and set formate and set locale
	     * @param formatType
	     * @param Calendar YYYY-MM-dd
	     * @return
	     * @throws ParseException 
	     */
	    public final static Calendar StringToCalendar(String formatType, String strDate)
	    {
	    	if(strDate==null)return null;
	    	 Calendar calendar= null;
	    	 SimpleDateFormat sdf = null;
	    	    try {
	    	      calendar = Calendar.getInstance();
	    	      calendar.clear();
	    	       sdf= new SimpleDateFormat(formatType);
	    	      Date date= sdf.parse(strDate);
	    	      calendar.setTime(date);
	    	    }
	    	    catch (ParseException ex) {
	    	      ex.printStackTrace();
	    	    }
	    	    return calendar;
	    }

	    /**
	     * @param calendar
	     * @param formatType
	     * @return empty if converting error
	     */
	    public final static String calendarToString(java.util.Calendar calendar,
	                                                String formatType) {
	        if (calendar == null) {
	            return "";
	        }
	        if(StringUtil.isEmpty(formatType)) return "";
	        SimpleDateFormat dateFormat=null;
	        try{
	        	dateFormat = new SimpleDateFormat(formatType, Locale.ENGLISH);
	        }catch(Exception e){}
	        
	        if(dateFormat==null) return "";
	        dateFormat.setLenient(false);
	        Date date = calendar.getTime();
	        return dateToString(date, formatType);
	    }




	    public static java.util.Date getCurSysDate() {
	        return new Date();
	    }



	    public static java.util.Calendar getCurSysCalendar() {
	        return Calendar.getInstance();
	    }



	    public static boolean yearCompareNow(String inputYear) {
	        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
	        int currentMonth = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH) + 1;
	        if (currentYear == Integer.valueOf(inputYear)) {
	            return Integer.valueOf(currentMonth) != 12;
	        } else
	            return Integer.valueOf(inputYear).compareTo(currentYear) > 0;
	    }

	    public static boolean monthCompareNow(String inputMonth) {
	        int currentMonth = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH) + 1;
	        return Integer.valueOf(inputMonth).compareTo(currentMonth) > 0;
	    }

	    public static boolean monthAndYearCompareNow(String inputYear, String inputMonth) {
	        int currentMonth = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH) + 1;
	        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
	        if (currentYear == Integer.valueOf(inputYear)) {
	            return Integer.valueOf(inputMonth).compareTo(currentMonth) > 0;
	        } else if (currentYear < Integer.valueOf(inputYear)) {
	            return true;
	        } else {
	            return false;
	        }
	    }

	 
	    /**
	     * 
	     * @param year :the year
	     * @param month :the month
	     * @param week :the week of the month
	     * @return
	     */
	    public static Date[] getStartdateAndEnddateOfTheweekOfTheyear(int year,int month, int week) {
			Date[] date = new Date[2];
			Calendar ca = Calendar.getInstance();
			ca.set(Calendar.YEAR, year);
			ca.set(Calendar.MONTH, month-1);
			ca.set(Calendar.WEEK_OF_MONTH, week);
			int day = ca.get(Calendar.DAY_OF_WEEK);
			ca.add(Calendar.DAY_OF_YEAR, 7 - day);
			date[0] = ca.getTime();
			ca.add(Calendar.DAY_OF_YEAR, -6);
			date[1] = ca.getTime();
			return date;
		}
	    public static boolean isHourTimeRegex(String str) {
	        Pattern pattern = Pattern.compile("^([0-1]{1}\\d|2[0-3]):([0-5]\\d)$");
	        Matcher isNum = pattern.matcher(str);
	        if (!isNum.matches()) {
	            return false;
	        }
	        return true;
	    }
	    


	    
	    public static Date getCurrYearLast(String yearStr){
	    	if(StringUtil.isEmpty(yearStr)||!isValidYearStr(yearStr)) return null ;
	    	Calendar calendar = Calendar.getInstance();  
			calendar.clear();  
			calendar.set(Calendar.YEAR,Integer.valueOf(yearStr) );  
			calendar.roll(Calendar.DAY_OF_YEAR, -1);  
			Date currYearLast = calendar.getTime();    
			return currYearLast;  
	    }
	    public static boolean isValidYearStr(String yearStr)
	    {
	    	if(StringUtil.isNumber(yearStr))
	    	{
	    		Long year=  StringUtil.stringToLong(yearStr);
	    		if(year<=9999&&year>=1000)
	    		{
	    			return true;
	    		}    		
	    	}
	    	return false;
	    }
	    public static Date getCurrYearFirst(String yearStr){
	    	if(StringUtil.isEmpty(yearStr)||!isValidYearStr(yearStr)) return null ;
	    	Calendar calendar = Calendar.getInstance();  
			calendar.clear();  
			calendar.set(Calendar.YEAR,Integer.valueOf(yearStr) );  
			Date currYearFirst = calendar.getTime();  
			return currYearFirst;  
	    }
	    public static Calendar getCalendarByYearAndMonth(Integer year,Integer month){
	    	if(year==null || month==null) return null;
	    	Calendar cal = Calendar.getInstance(); 
	    	cal.clear();
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH,month-1);		
			return cal;
	    }
	    
	    public static boolean isBeforeCurrentCalendar(Calendar toBeComparedCalendar){
	    	if(toBeComparedCalendar==null) return false;
	    	Calendar calendar = Calendar.getInstance();
	    	return toBeComparedCalendar.before(calendar);
	    }

}
