package cn.fm.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;





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
    public static String removeSpecifyId(String source, String target) {
        if (isEmptyStr(source) || isEmptyStr(target)) return source;
        if (source.equals(target)) return "";
        List<Long> sourceIdList = stringToLongList(source);
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < sourceIdList.size(); i++) {
            Long sourceId = sourceIdList.get(i);
            if (target.equals(sourceId.toString())) {
                continue;
            }
            result.append(sourceId);
            if (i == sourceIdList.size() - 1)
                continue;
            result.append(",");
        }
        if(result.toString().endsWith(",")){
          result =  result.deleteCharAt(result.lastIndexOf(","));
        }
        return result.toString();
    }

    public static boolean isSplitType(String source) {
        if (source != null && source.lastIndexOf(",") != -1
                && source.lastIndexOf(",") > 0)
            return true;
        return false;
    }

    public static boolean isSplitType(String source, String sym) {
        if (source != null && source.lastIndexOf(sym) != -1
                && source.lastIndexOf(sym) > 0)
            return true;
        return false;
    }

    public final static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public final static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^\\d+(\\.\\d+)?$");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public final static boolean isValidateByRegx(String str, String regx) {
        Pattern pattern = Pattern.compile(regx);
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public final static boolean isString(String str) {
        Pattern pattern = Pattern.compile("[a-z|A-Z]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public final static boolean isLetterChar(String source){
       if(isEmptyStr(source)) return false;
       Pattern pattern = Pattern.compile("[a-z|A-Z|0-9]*");
       Matcher matcher = pattern.matcher(source);
       return matcher.matches();
    }

//    public final static boolean isValidLogBookCode(String source){
//       if(isEmptyStr(source)) return false;
//       Pattern pattern = Pattern.compile(Constant.EGIS_LOG_BOOK_CODE_REGX);
//       Matcher matcher = pattern.matcher(source);
//       return matcher.matches();
//    }

    public static String transEmptyStr(String source) {
        if (source == null || source.trim().length() == 0)
            return "";
        return source;
    }

    public static boolean isEmptyStr(String[] source) {
        if (!isStringArrayEmpty(source)) {
            for (String str : source) {
                if (isEmptyStr(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isEmptyAndNumber(String para) {
        return !isEmptyStr(para) && isNumeric(para);
    }

    /**
     * string parameter format:'11|1,12|2,13|3'
     */
    public static Map<Long, Long> splitToList(String assignId) {
        List<Map<Long, Long>> resultList = new ArrayList<Map<Long, Long>>();
        Map<Long, Long> scheduleTaskMap = new HashMap<Long, Long>();
        String assignCol[] = null;
        if (!StringUtil.isEmptyStr(assignId)) {
            if (isSplitType(assignId)) {
                assignCol = assignId.split(",");
                for (String assign : assignCol) {
                    resultList.add(splitToMap(scheduleTaskMap, assign, "|"));
                }
            } else {
                resultList.add(splitToMap(scheduleTaskMap, assignId, "|"));
            }
        }

        return scheduleTaskMap;
    }

    /**
     * key: 1 value: 0
     */
    public static Map<Long, Long> splitToMap(Map<Long, Long> scheduleTaskMap,
                                             String value, String symbol) {
        if (isSplitType(value, symbol)) {
            String[] valueCol = value.split("\\" + symbol);
            if (valueCol.length > 0 && !StringUtil.isEmptyStr(valueCol[1])
                    && !StringUtil.isEmptyStr(valueCol[0])) {
                scheduleTaskMap.put(Long.valueOf(valueCol[1].trim()), Long
                        .valueOf(valueCol[0].trim()));
            }
        }
        return scheduleTaskMap;
    }

    public static String genPassword(){
        StringBuffer password = new StringBuffer();
        password.append(genRandomString(1,false,true)).append(genRandomString(6,false,false)).append(genRandomString(1,false,true));
        Random r = new Random();
        int position = r.nextInt(7);
        while(position==0){
            position = r.nextInt(7);
        }
        password.setCharAt(position,genRandomString(1,true,false).charAt(0));
        return password.toString();
    }

    public static String genRandomString(int length) {
        return genRandomString(length,false,false);
    }

    public static String genRandomString(int length,boolean isNumber,boolean isString) {
        int maxNum = 36;
        int maxStringNum = 26;
        int maxNumberNum = 10;
        int i;
        int count = 0;
        char[] str = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] stringArray = Arrays.copyOfRange(str,0,26);
        char[] numberArray = Arrays.copyOfRange(str,26,36);
        if(isNumber){
            maxNum = maxNumberNum;
            str = numberArray;
        }else if(isString){
            maxNum = maxStringNum;
            str = stringArray;
        }
        StringBuffer buffer = new StringBuffer("");
        Random r = new Random();
        while (count < length) {
            i = Math.abs(r.nextInt(maxNum));
            if (i >= 0 && i < str.length) {
                buffer.append(str[i]);
                count++;
            }
        }
        return buffer.toString();
    }

    public static Double stringToDouble(String source){
       if(isEmptyStr(source) || !isNumber(source)) return null;
       return Double.parseDouble(source);
    }

     public static Long stringToLong(String source){
       if(isEmptyStr(source) || !isNumeric(source)) return null;
       return Long.parseLong(source);
    }

    public static long[] stringToLongArray(String str, String format) {
        if (StringUtil.isEmpty(str)) return null;
        if (StringUtil.isEmpty(format)) return null;
        String temp[] = str.split(format);
        if (temp != null && temp.length != 0 && temp[0].length() != 0) {
            long longArray[] = new long[temp.length];
            for (int i = 0; i < temp.length; i++)
                longArray[i] = Long.parseLong(temp[i].trim());
            return longArray;
        } else
            return null;
    }

    public static List<Long> splitToLongList(String str) {
        if (StringUtil.isEmptyStr(str) || !isSplitType(str)) return null;
        List<Long> targetList = new ArrayList<Long>();
        String temp[] = str.split(",");
        for (String t : temp) {
            try {
                targetList.add(Long.parseLong(t));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return targetList;
    }

    public static List<Long> stringToLongList(String str) {
        if(!str.contains(",")&&isNumeric(str)){
            List<Long> targetList = new ArrayList<Long>();
            targetList.add(Long.parseLong(str));
            return targetList;
        }else{
            return splitToLongList(str);
        }
    }

    public static boolean isValidDateRegex(String regex) {
        Matcher yearMatcher = Pattern.compile("yyyy").matcher(regex);
        Matcher monthMatcher = Pattern.compile("MM").matcher(regex);
        Matcher dateMatcher = Pattern.compile("dd").matcher(regex);
        if (yearMatcher.find() && !yearMatcher.find() && monthMatcher.find()
                && !monthMatcher.find() && dateMatcher.find()
                && !dateMatcher.find()) {
            return true;
        }
        return false;
    }

    public static boolean isValidTimeRegex(String regex) {
        Matcher hourMatcher = Pattern.compile("HH").matcher(regex);
        Matcher minuteMatcher = Pattern.compile("mm").matcher(regex);
        Matcher secondMatcher = Pattern.compile("ss").matcher(regex);
        if (hourMatcher.find() && !hourMatcher.find() && minuteMatcher.find()
                && !minuteMatcher.find() && secondMatcher.find()
                && !secondMatcher.find()) {
            return true;
        }
        return false;
    }

    public static String replaceLineBreak(String source) {
        if (isEmptyStr(source)) return source;
        String result = source.replace("\r\n", "<br/>");
        if (result.endsWith("<br/>")) {
            result = result.substring(0, result.lastIndexOf("<br/>"));
        }
        return result;
    }

    public static String replaceLineBreakAndEmpty(String source) {
        if (isEmptyStr(source)) return source;
        String result = source.replace("\r\n", "<br/>").replace(" ", "&nbsp;");
        if (result.endsWith("<br/>")) {
            result = result.substring(0, result.lastIndexOf("<br/>"));
        }
        return result;
    }
    
    public static boolean isExist(Object field, Object[] col) {
        for (Object arg : col) {
            if (arg.equals(field)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPositiveNumberStr(String source) {
        if (isNumber(source)) {
            double val = Double.parseDouble(source);
            if (val > 0) return true;
        }
        return false;
    }

//    public static String getCourseTypeName(String type) {
//        String displayType = "";
//        Map courseTypeMap = SelectTextXML.getInstance().getSelectText(Constant.LOCALIZATION_TRADITIONAL_CHINESE, "courseType");
//        if (courseTypeMap != null && courseTypeMap.size() > 0) {
//            if (courseTypeMap.get(type) != null) {
//                displayType = courseTypeMap.get(type).toString();
//            } else {
//                displayType = "";
//            }
//        }
//        return displayType;
//    }

//    public static Boolean isWithOneDecimalFormat(Double d) {
//        if (d != null) {
//            if (StringUtil.isValidateByRegx(d.toString(), Constant.EGIS_DOUBLE_FORAMT)) return true;
//            else return false;
//        } else return true;
//    }
//
//    public static String getGenderStr(Long source){
//        if(source==null) return null;
//        if(source==Constant.EGIS_GENDER_MALE.longValue()) return Constant.EGIS_GENDER_MALE_STR;
//        if(source==Constant.EGIS_GENDER_FEMALE.longValue()) return Constant.EGIS_GENDER_FEMALE_STR;
//        else return null;
//    }
//
//    public static boolean isValidEmailAddress(String emailAddress) {
//        Pattern pattern = Pattern.compile(Constant.EMAIL_ADDRESS_PATTERN);
//        Matcher matcher = pattern.matcher(emailAddress);
//        return matcher.matches();
//    }

    public static boolean isWordCharacter(String input){
        if(isEmptyStr(input)) return false;
        return input.matches("\\w+");
    }

    public static boolean isValidMonthAndDayPattern(String input){
        if(isEmptyStr(input)) return false;
        if(!input.contains("-")) return false;
        if(input.indexOf("-")==0) return false;
        if(input.lastIndexOf("-")==input.length()-1) return false;
        String[] resultArray = input.split("-");
        if(resultArray.length!=2) return false;
        if(!isNumeric(resultArray[0]) || !isNumeric(resultArray[1])) return false;
        int month = Integer.parseInt(resultArray[0]);
        if(month>12 || month<1) return false;
        int day = Integer.parseInt(resultArray[1]);
        if(day>31 || day<1) return false;
        return true;
    }
    public static List<Integer> splitTimeToStringList(String timeStr) {
        if (StringUtil.isEmptyStr(timeStr)) return null;
        List<Integer> targetList = new ArrayList<Integer>();
        String temp[] = timeStr.split(":");
        for (String t : temp) {
            try {
                targetList.add(Integer.parseInt(t));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return targetList;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	

}
