package cn.fm.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
/**
 *
 *处理日期方法的工具类
 * @author jameslin
 * 2013-10-16
 */
public class DateUtilAll
{
	//每月天数(非润年)
	static int daysInMonth[]= { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	// 闰年的特殊月份
	static final int MONTH_FEBRUARY= 2;
	public static final int PRECISE_YEAR= 1;
	public static final int PRECISE_MONTH= 2;
	public static final int PRECISE_DAY= 3;
	public static final int PRECISE_HOUR= 4;
	public static final int PRECISE_MINUTE= 5;
	public static final int PRECISE_SECOND= 6;
	public static final int PRECISE_MilliSECOND= 7;
	/**
	 * 获得当天日期
	 * @return yyyy-mm-dd
	 */
	public static String getCurrentDateStrWithDiv()
	{
		Calendar cal= Calendar.getInstance();
		return getCurrentDateStrWithDiv(cal);
	}

	/**
	 * 获得当天日期
	 * @return yyyy-mm-dd
	 */
	public static String getCurrentDateStrWithDiv(Calendar cal)
	{
		String curDateStr= "";
		int year= cal.get(Calendar.YEAR);
		int month= cal.get(Calendar.MONTH) + 1;
		int day= cal.get(Calendar.DAY_OF_MONTH);
		curDateStr= String.valueOf(year) + "-";
		curDateStr += ((month < 10) ? "0" + String.valueOf(month) : String.valueOf(month)) + "-";
		curDateStr += ((day < 10) ? "0" + String.valueOf(day) : String.valueOf(day));
		return curDateStr;
	}

	/**
	 * 获得当天日期
	 * @return yymmdd 如040529
	 */
	public static String getCurrentDateStrNoDiv()
	{
		String curDateStr= "";
		Calendar cal= Calendar.getInstance();
		int year= cal.get(Calendar.YEAR);
		int month= cal.get(Calendar.MONTH) + 1;
		int day= cal.get(Calendar.DAY_OF_MONTH);
		curDateStr= String.valueOf(year).substring(2,4) + "";
		curDateStr += ((month < 10) ? "0" + String.valueOf(month) : String.valueOf(month)) + "";
		curDateStr += ((day < 10) ? "0" + String.valueOf(day) : String.valueOf(day));
		return curDateStr;
	}

	/**
	 * 日期格式为2005-12-31
	 */
	public static Calendar ConvertStrToCalendar(String time)
	{
		int year= 0, month= 0, date= 0;
		if ((time != null) && (time.trim().length() == 0))
		{
			year= 1900;
			month= 1;
			date= 1;
		}
		else
		{
			year= Integer.parseInt(time.substring(0, 4));
			month= Integer.parseInt(time.substring(5, 7)) - 1;
			date= Integer.parseInt(time.substring(8));
		}
		Calendar calendar= new GregorianCalendar(year, month, date);
		return calendar;
	}
	/**
	 * 获得当前时间，精度到毫秒
	 * @return hh:mm:ss.XXX
	 */
	public static String getCurrentTimeStr()
	{

		Calendar cal= Calendar.getInstance();

		return getCurrentTimeStr(cal);
	}


	/**
	 * 获得当前时间，精度到毫秒
	 * @return hh:mm:ss.XXX
	 */
	public static String getCurrentTimeStr(Calendar cal)
	{
		String curTimeSr= "";
		int hour= cal.get(Calendar.HOUR_OF_DAY);
		int minute= cal.get(Calendar.MINUTE);
		int second= cal.get(Calendar.SECOND);
		int milliSecond= cal.get(Calendar.MILLISECOND);
		curTimeSr= ((hour < 10) ? "0" + String.valueOf(hour) : String.valueOf(hour)) + ":";
		curTimeSr += ((minute < 10) ? "0" + String.valueOf(minute) : String.valueOf(minute)) + ":";
		curTimeSr += ((second < 10) ? "0" + String.valueOf(second) : String.valueOf(second));
		curTimeSr += "." + ((milliSecond < 10) ? "00" + String.valueOf(milliSecond) : ((milliSecond < 100) ? "0" + String.valueOf(milliSecond) : String.valueOf(milliSecond)));
		return curTimeSr;
	}

	/**
	 * 获得当前时间，精度到秒
	 * @return hh:mm:ss
	 */
	public static String getTimeToSecond()
	{
		Calendar cal= Calendar.getInstance();

		return getTimeToSecond(cal);
	}

	/**
	 * 获得当前时间，精度到秒
	 * @return hh:mm:ss
	 */
	public static String getTimeToSecond(Calendar cal)
	{
		String curTimeSr= "";
		int hour= cal.get(Calendar.HOUR_OF_DAY);
		int minute= cal.get(Calendar.MINUTE);
		int second= cal.get(Calendar.SECOND);
		int milliSecond= cal.get(Calendar.MILLISECOND);
		curTimeSr= ((hour < 10) ? "0" + String.valueOf(hour) : String.valueOf(hour)) + ":";
		curTimeSr += ((minute < 10) ? "0" + String.valueOf(minute) : String.valueOf(minute)) + ":";
		curTimeSr += ((second < 10) ? "0" + String.valueOf(second) : String.valueOf(second));
		return curTimeSr;
	}
	/**
	 * 获得当天时间，精度到秒
	 * @return yyyy-mm-dd hh-mm-ss
	 */
	public static String getCurrentDateTimeStr()
	{
		String curDateTimeStr= "";
		curDateTimeStr= getCurrentDateStrWithDiv() + " " + getTimeToSecond();
		return curDateTimeStr;
	}
	/**
	 * 获得当天时间，精度到毫秒
	 * @return yyyy-mm-dd hh-mm-ss.XXX
	 */
	public static String getCurrentDateTimeMilliStr()
	{
		String curDateTimeStr= "";
		curDateTimeStr= getCurrentDateStrWithDiv() + " " + getCurrentTimeStr();
		return curDateTimeStr;
	}
	/**
	 * 获得当前年份
	 * @return yyyy
	 */
	public static int getCurrentYear()
	{
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	/**
	 * 获取当前月份
	 * @return
	 */
	public static int getCurrentMonth()
	{
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}
	/**
	 * 获取当前时间（24小时制）
	 * @return
	 */
	public static int getCurrentHour()
	{
		return Calendar.getInstance().get(Calendar.HOUR);
	}


	/**
	 * 获取当前日
	 * @return
	 */
	public static String getCurrentDay()
	{
		String str = null;
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		if (day<10){
			str = "0" + day;
		}
		else{
			str = String.valueOf(day);
		}
		return str;
	}
	/**
	 * 判断是否闰月，用于计算当前时间加上分钟后的时间
	 * @param year 年份
	 * @return
	 */
	public static boolean isLeapYear(int year)
	{
		// 能被100整除, 不能被400整除的年份, 不是闰年.
		// 能被100整除, 也能被400整除的年份, 是闰年.
		if ((year % 100) == 0)
		{
			return ((year % 400) == 0);
		}
		else // 不能被100整除, 能被4整除的年份是闰年.
			{
			return ((year % 4) == 0);
		}
	}
	/**
	 * 计算当前时间加上秒钟后的时间,建议方法名换为increaseCurDateTime
	 * @param addedSecond 在当前时间上要加的秒数，注意输入的秒钟数不能大于一个月
	 * @return yyyy-mm-dd hh-mm-ss.XXXX
	 */
	public static String calDateTime(int addedSecond)
	{
		//若要限制输入的秒钟数不能大于一个月，则应在此加以判断
		Calendar cal= Calendar.getInstance();
		int year= cal.get(Calendar.YEAR);
		int month= cal.get(Calendar.MONTH) + 1;
		int day= cal.get(Calendar.DAY_OF_MONTH);
		int hour= cal.get(Calendar.HOUR_OF_DAY);
		int minute= cal.get(Calendar.MINUTE);
		int second= cal.get(Calendar.SECOND);
		int millisecond= cal.get(Calendar.MILLISECOND);
		// 获取当前月含有的天数, 如果是闰年的二月, 加一天.
		int daysInCurMonth= daysInMonth[month - 1];
		if (isLeapYear(year) && (month == MONTH_FEBRUARY))
		{
			daysInCurMonth += 1;
		}
		addedSecond += second;
		second= addedSecond % 60;
		//输入的分钟数不能大于一个月   ？？？ pay attention to here
		minute= minute + addedSecond / 60;
		//总的小时数
		hour= hour + minute / 60;
		//分钟数
		minute= minute % 60;
		//总的天数
		day= day + hour / 24;
		//小时数
		hour= hour % 24;
		if (day > daysInCurMonth)
		{
			//总的月份数,限制输入的秒钟数不能大于一个月的原因在此
			month= month + day / daysInCurMonth;
			//天数
			day= day % daysInCurMonth;
		}
		if (month > 12)
		{
			//总的年数
			year= year + month / 12;
			//月份数
			month= month % 12;
		}
		String dateTimeStr= "1900-01-01";
		dateTimeStr= String.valueOf(year) + "-";
		dateTimeStr += ((month < 10) ? "0" + String.valueOf(month) : String.valueOf(month)) + "-";
		dateTimeStr += ((day < 10) ? "0" + String.valueOf(day) : String.valueOf(day)) + " ";
		dateTimeStr += ((hour < 10) ? "0" + String.valueOf(hour) : String.valueOf(hour)) + ":";
		dateTimeStr += ((minute < 10) ? "0" + String.valueOf(minute) : String.valueOf(minute)) + ":";
		dateTimeStr += ((second < 10) ? "0" + String.valueOf(second) : String.valueOf(second));
		dateTimeStr += "." + String.valueOf(millisecond);
		return dateTimeStr;
	}
	/**
	 * 计算两个时间之间的时间差
	 * @param strDateTime1 减数，格式为yyyy-mm-dd hh-mm-ss
	 * @param strDateTime2 被减数，格式为yyyy-mm-dd hh-mm-ss
	 * @return strDateTime1 - strDateTime2的时间差，单位为毫秒
	 */
	public static long computeInterval(String strDateTime1, String strDateTime2)
	{
		long interval= 0;
		Timestamp date1= convertStrToDate(strDateTime1);
		Timestamp date2= convertStrToDate(strDateTime2);
		interval= date1.getTime() - date2.getTime();
		return interval;
	}
	/**
	 * 得到指定精度的时间字符串
	 * @param dateTimeString 原始时间字符串，格式为yyyy-mm-dd hh:mm:ss
	 * @param precise 指定的精度
	 * @return
	 */
	public static String customDateTimeStr(String dateTimeString, int precise)
	{
		if (dateTimeString == null)
		{
			dateTimeString= "";
			return dateTimeString;
		}
		if (dateTimeString.trim().length() == 0)
		{
			return dateTimeString;
		}
		if (dateTimeString.startsWith("1900"))
		{
			dateTimeString= "";
			return dateTimeString;
		}
		if (precise == PRECISE_YEAR)
		{
			dateTimeString= dateTimeString.substring(0, 4);
		}
		if (precise == PRECISE_MONTH)
		{
			dateTimeString= dateTimeString.substring(0, 7);
		}
		if (precise == PRECISE_DAY)
		{
			dateTimeString= dateTimeString.substring(0, 10);
		}
		if (precise == PRECISE_HOUR)
		{
			dateTimeString= dateTimeString.substring(0, 13);
		}
		if (precise == PRECISE_MINUTE)
		{
			dateTimeString= dateTimeString.substring(0, 16);
		}
		return dateTimeString;
	}
	public static String convertDateToStr(Timestamp date)
	{
		String result= "1900-01-01 00:00:00.000";
		if (date != null)
		{
			result= date.toString();
		}
		return result;
	}
	public static Timestamp convertStrToDate(String strDate)
	{
		if (strDate == null)
		{
			strDate= "1900-01-01 00:00:00.000";
		}
		else
		{
			if (strDate.trim().length() == 0)
			{
				strDate= "1900-01-01 00:00:00.000";
			}
			else
				if (strDate.trim().length() == 10) //传入的日期不包含时间
				{
					strDate += " 00:00:00.000";
				}
				else
					if (strDate.trim().length() == 16)						//传入的日期包含时间到分钟位,如2000-01-01 10:10
					{
						strDate += ":00.000";
					}
		}
		return Timestamp.valueOf(strDate);
	}
	/**
	 * 把日历类型转换成string的时间 yyyy-mm-dd hh-mm-ss
	 * @param cal
	 * @return
	 */
	public static String convertCalToStr(Calendar cal)
	{
		if(cal==null)
		{
			return "";
		}
		String curDateTimeStr= "";
		curDateTimeStr= getCurrentDateStrWithDiv(cal) + " " + getTimeToSecond(cal);
		return curDateTimeStr;
	}

	/**
	 * 将传入的string转换成calendar(add by ywj)
	 * @param dateTime ("2004-01-18 10:50:30")
	 * @return true or false
	 */
	public static Calendar converStrToCalendar(String dateTime)
	{
		if (dateTime == null){
			return null;
		}
		if (dateTime.length() ==0){
			return null;
		}
		Calendar cal = Calendar.getInstance(); //取当前时间
		Timestamp timestamp = convertStrToDate(dateTime);
		String getYearString = dateTime.substring(0, 4);
		int year = Integer.parseInt(getYearString);
		Date toDate = (Date) timestamp;
		int month = toDate.getMonth();
		int date = toDate.getDate();
		int hour = toDate.getHours();
		int minute = toDate.getMinutes();
		int second = toDate.getSeconds();
		cal.set(year,month,date,hour,minute,second);
		return cal;
	}

	/**
	 * 长日期字符串转化为短日期字符串
	 * @param dateTime ("2004-01-18 10:50:30")
	 * @return 2004-01-18
	 */
	public static String ConvertStrToDateStr(String time)
	{
		Timestamp date1= convertStrToDate(time);
		String getYearString = time.substring(0, 4);
		int year = Integer.parseInt(getYearString);
		Date toDate = (Date) date1;
		int month = toDate.getMonth()+1;
		int date = toDate.getDate();
		return year+"-"+month+"-"+date;
	}

	/**
	 * 日期格式为12/31/2005 转为 2005-12-31
	 */
	public static Calendar ConvertsStrToCalendar(String time)
	{
		int year= 0, month= 0, date= 0;
		if ((time != null) && (time.trim().length() == 0))
		{
			year= 1900;
			month= 1;
			date= 1;
		}
		else
		{
			month= Integer.parseInt(time.substring(0, 2))-1;
			date= Integer.parseInt(time.substring(3, 5));
			year= Integer.parseInt(time.substring(6));
		}
		Calendar calendar= new GregorianCalendar(year, month, date);
		return calendar;
	}

	/**
	 * 时间戳格式转换
	 * @param  date 时间戳值
	 * @param pattern 可设置为"",默认转换格式为yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String convertTimeStamp(long date,String pattern) {
        if (pattern.length() == 0)
            pattern = "yyyy-MM-dd HH:mm:ss";
        java.util.Calendar nowDate = new java.util.GregorianCalendar();
        nowDate.setTimeInMillis(date);
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(nowDate.getTime());
    }
	
	/**
	 * 时间戳格式转换
	 * @param  date 时间戳值
	 * @return
	 */
	public static String convertTimeStamp(long date) {
        return DateUtilAll.convertTimeStamp(date, "yyyy-MM-dd HH:mm:ss");
    }
	
	/**
	 * 日期和字符串的转换
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String formatDate(Date date,String pattern) {
	   java.text.DateFormat df = new java.text.SimpleDateFormat(pattern);
	   return df.format(date);
	}

	/**
	 * 取得两个日期之间的天数
	 * @param d1
	 * @param d2
	 * @return pyf 2009-10-14加
	 */
	public static int getDaysBetween(java.util.Calendar d1, java.util.Calendar d2) {
		if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
		java.util.Calendar swap = d1;
		d1 = d2;
		d2 = swap;
		}
		int days = d2.get(java.util.Calendar.DAY_OF_YEAR)
		- d1.get(java.util.Calendar.DAY_OF_YEAR);
		int y2 = d2.get(java.util.Calendar.YEAR);
		if (d1.get(java.util.Calendar.YEAR) != y2) {
		d1 = (java.util.Calendar) d1.clone();
		do {
		days = d1.getActualMaximum(java.util.Calendar.DAY_OF_YEAR);
		d1.add(java.util.Calendar.YEAR, 1);
		} while (d1.get(java.util.Calendar.YEAR) != y2);
		}
		return days;
		}


	/**
	 * 判断当前日期是星期几
	 * @param date
	 * @return
	 */
	public static String getWeekDay(String date)
    {
    	String returnValue="";
	    SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
		    //java.util.Date date = myFormatter.parse("2005-10-12");
		java.util.Date mydate = null;
		mydate = myFormatter.parse(date);
		SimpleDateFormat formatter4 = new SimpleDateFormat("E");
		String mydate3 = formatter4.format(mydate);
		returnValue=mydate3;
		} catch (ParseException e) {
		e.printStackTrace();
		}
		return returnValue;
    }
	
	
	
	public static void main(String[] args) {
		
		//System.out.println("+++++++  "+DateUtil.convertTimeStamp( System.currentTimeMillis())+"  +++++++++");
	}

}
