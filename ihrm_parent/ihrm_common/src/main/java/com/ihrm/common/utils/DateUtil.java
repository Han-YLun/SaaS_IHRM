package com.ihrm.common.utils;

import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by loni on 2017/8/24.
 */
public class DateUtil {
    /**
     * 日期转换-  String -> Date
     *
     * @param dateString 字符串时间
     * @return Date类型信息
     * @throws ParseException 抛出异常
     */
    public static Date parseString2Date(String dateString) throws ParseException {
        if (dateString == null) {
            return null;
        }
        return parseString2Date(dateString, "yyyy-MM-dd");
    }

    /**
     * 日期转换-  String -> Date
     *
     * @param dateString 字符串时间
     * @param pattern    格式模板
     * @return Date类型信息
     * @throws ParseException 抛出异常
     */
    public static Date parseString2Date(String dateString, String pattern) throws ParseException {
        if (dateString == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(dateString);
        return date;
    }

    /**
     * 日期转换 Date -> String
     *
     * @param date Date类型信息
     * @return 字符串时间
     * @throws ParseException 抛出异常
     */
    public static String parseDate2String(Date date) throws ParseException {
        if (date == null) {
            return null;
        }
        return parseDate2String(date, "yyyy-MM-dd");
    }

    /**
     * 日期转换 Date -> String
     *
     * @param date    Date类型信息
     * @param pattern 格式模板
     * @return 字符串时间
     * @throws ParseException 抛出异常
     */
    public static String parseDate2String(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String strDate = sdf.format(date);
        return strDate;
    }

    public static String getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最小天数
        int firstDay = cal.getMinimum(Calendar.DATE);
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd");
        return sdf.format(cal.getTime());
    }

    public static String getLastDayOfMonth1(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd");
        return sdf.format(cal.getTime());
    }

    public static String getStrMonth(int month) {
        String[] strs = {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};
        if (month > 0 && month < 13) {
            return strs[month - 1];
        } else {
            return "一";
        }
    }

    public static String parseTimestampToStr(Timestamp timestamp, String timeFromat){
        SimpleDateFormat df = new SimpleDateFormat(timeFromat);
        return df.format(timestamp);
    }

    public static String timestampToDate(long time, String timeFormat) {
        if (time < 10000000000L) {
            time = time * 1000;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(time))));
        return sd;
    }

    public  static List<String> getYearMonths() throws Exception {
	    ArrayList<String> result = new ArrayList<String>();

	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");//格式化为年月

	    Calendar min = Calendar.getInstance();
	    Calendar max = Calendar.getInstance();
	    min.set(min.get(Calendar.YEAR), 0, 1);

	    max.setTime(new Date());
	    max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

	    Calendar curr = max;

	    while (curr.after(min)) {
		    result.add(sdf.format(curr.getTime()));
		    curr.add(Calendar.MONTH, -1);
	    }

	    return  result;
    }

	public static String[] getDaysByYearMonth(String yearMonth) throws ParseException {
    	Date date = new SimpleDateFormat("yyyyMM").parse(yearMonth);
		Calendar a = Calendar.getInstance();
		a.setTime(date);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		String [] days = new String[maxDate];
		for (int i = 0; i < maxDate; i++) {
			String day = (i+1)<10 ? "0"+(i+1):(i+1)+"";
			days[i] = yearMonth+day;
		}
		return days;
	}

	public static boolean comparingDate(String targer,Date sourceDate) throws Exception {

		Date date1 = new SimpleDateFormat("HH:mm:ss").parse(targer);

		String t = new SimpleDateFormat("HH:mm:ss").format(sourceDate);

		Date date2 = new SimpleDateFormat("HH:mm:ss").parse(t);

		return date1.after(date2);
	}

	public static boolean isWeekend(String time) throws ParseException {
		;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parseString2Date(time,"yyyyMMdd"));
		int i = calendar.get(Calendar.DAY_OF_WEEK)-1;
		return i==0?true:i==5?true:false;
	}

	public static void main(String[] args) throws Exception {
		System.out.println("201801".substring(4));
	}
}
