package cn.fm.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




@SuppressWarnings("unused")
public class DateUtil {
	    public final static String FORMAT_DATE = "yyyy-MM-dd";
	    public final static String FORMAT_DATE_MONTH = "yyyy年MM月";
	    public final static String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	    public final static String FORMAT_DB_DATE_TIME = "YYYY-MM-DD HH24:MI:SS";
	    public final static String FORMAT_DATE_YYYYMMMDD = "yyyyMMdd";
	    public final static String FORMAT_DATE_YYYYMMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
	    public final static String FORMAT_HOUR_TIME="HH:mm";

	

	    public static String dateFormat(String date, String userSetFormat, String dataBaseFormat) {
	        return dateToString(StringtoDate(date, userSetFormat), dataBaseFormat);
	    }
	    public static boolean isDateRegex(String str) {
	        Pattern pattern = Pattern.compile(regxForDate());
	        Matcher isNum = pattern.matcher(str);
	        if (!isNum.matches()) {
	            return false;
	        }
	        return true;
	    }
	    public static String regxForDate() {
	        String format = getDateFormat();
	        format = format.replaceAll("yyyy", Constant.WMS_REGEX_YEAR);
	        format = format.replaceAll("dd", Constant.WMS_REGEX_DATE);
	        format = format.replaceAll("mm", Constant.WMS_REGEX_DATE);
	        format = format.replaceAll("YYYY", Constant.WMS_REGEX_YEAR);
	        format = format.replaceAll("DD", Constant.WMS_REGEX_DATE);
	        format = format.replaceAll("MM", Constant.WMS_REGEX_DATE);
	        return format;
	    }
	    
	    /**
	     * @return yyyy-MM-dd
	     */
	    public static String getDateFormat() {
	    	
	    	Date  date=new Date();
	        return dateToString(date,FORMAT_DATE);
	    }
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
	     * 这种日期格式2013/05/08的转换
	     * @param str
	     * @param strFormat
	     * @return
	     */
	    public static Date StringTypeToDate(String str,String strFormat){
	    	SimpleDateFormat dateFormat=new SimpleDateFormat(strFormat);
	    	Date date=new Date();
	    	try {
	    		
				String year = str.substring(7, 11);
				String month = str.substring(3, 6);
				String yue = str.substring(0,2);
				
				date=dateFormat.parse(str);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
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
	    public final static String calendarToString(java.util.Calendar calendar, String formatType) {
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
	    /**
	     * //判断时间date1是否在时间date2之前   时间格式 2005-4-21 16:16:34
	     * @param date1
	     * @param date2
	     * @return
	     */
	    public static boolean isDateBefore(String date1,String date2){
	        try{
	         DateFormat df = DateFormat.getDateTimeInstance();
	         return df.parse(date1).before(df.parse(date2));
	        }catch(ParseException e){
	        	e.printStackTrace();
	         return false;
	        }
	     }
	    
	    /**
	     * 时间比大小
	     * @param date1
	     * @param date2
	     * @return
	     */
	    
	    public static int timeCompare(String date1,String date2){
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar c1=Calendar.getInstance();
	        Calendar c2=Calendar.getInstance();
	        try {
	            c1.setTime(formatter.parse(date1));
	            c2.setTime(formatter.parse(date2));
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        int result=c1.compareTo(c2);
	        return result;
	    }
	    
	    /**

	    * 两个时间比较

	    * @param date

	    * @return

	    */

	    public static int compareDateWithNow(Date date1){
	    	
		    Date date2 = new Date();
		    int rnum =date1.compareTo(date2);
		    return rnum;

	    }
	    
	    
	    /**
	     * 获得当前时间
	     * @return
	     */ 
	    public static String getCurrentTime(){  
	        Date currentTime = new Date();  
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	        String dateString = formatter.format(currentTime);  
	        return dateString;  
	    }  
	    
	    public static String getNextDay(String date){
	    	if(StringUtil.isEmptyStr(date)){
	    		return date;
	    	}
	    	Calendar defaultValue = DateUtil.StringtoCalendar(date,DateUtil.FORMAT_DATE );
	    	defaultValue.add(Calendar.DATE, 1);
	    	return DateUtil.calendarToString(defaultValue,getDateFormat());
	    }

	    public static Calendar getNextDay(Calendar calendar){
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(calendar.getTime());
	        cal.add(Calendar.DATE,1);
	        return cal;
	    }
	  
	    /**
	     * string to Calendar and set formate and set locale
	     *
	     * @param stringTime
	     * @param format
	     * @return
	     */
	    public final static Calendar StringtoCalendar(String stringTime, String format) {
	        if (StringUtil.isEmptyStr(stringTime) || StringUtil.isEmptyStr(format)) return null;
	        Date dateTime = StringtoDate(stringTime, format);
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(dateTime);
	        return calendar;
	    }
	    /**
	     * string to date and set formate and set locale
	     *
	     * @param stringTime
	     * @param format
	     * @return
	     */
	    public static Date StringtoDate(String stringTime, String format) {
	        if (StringUtil.isEmpty(stringTime)) return null;
	        if (format == null || ("").equals(format)) {
	            format = getDateFormat();
	        }
	        Date dateTime = null;
	        try {
	            String rs = chinaDateTran(stringTime);
	            if (!stringTime.equals(rs)) {
	                if (format.equals(getDateFormat())) {
	                    format = Constant.WMS_ACTIVITY_DATE;
	                } else if (format.equals(getCurrentTime())) {
	                    format = Constant.WMS_DATE_WITH_TIME;
	                } else {
	                    format = Constant.WMS_MSG_DATE_TIME;
	                }

	            }
	            SimpleDateFormat sdf = new SimpleDateFormat(format);
	            dateTime = sdf.parse(rs);
	        } catch (ParseException e) {

	            e.printStackTrace();
	        }
	        return dateTime;
	    }
	    public static String chinaDateTran(String stringTime) {
	        String rs = "";
	        boolean flag = false;
	        for (int i = 0; i < stringTime.length(); i++) {
	            int number = String.valueOf(stringTime.charAt(i)).getBytes().length;
	            if (number == 2) {
	                flag = true;
	                rs += stringTime.substring(0, i).trim() + "-";
	                stringTime = stringTime.substring(i + 1, stringTime.length());
	                i = 0;
	            }
	        }
	        if (!flag) {
	            return stringTime;
	        }
	        rs = rs.substring(0, rs.lastIndexOf("-")) + stringTime;
	        return rs;
	    }
	    public static String stringToString(String dateStr, String destFormat) {
	        Date date = stringToDate(dateStr);
	        return dateToString(date, destFormat);
	    }
	    public static Date stringToDate(String dateStr) {
	        return StringtoDate(dateStr, FORMAT_DATE);
	    }

	 
}
