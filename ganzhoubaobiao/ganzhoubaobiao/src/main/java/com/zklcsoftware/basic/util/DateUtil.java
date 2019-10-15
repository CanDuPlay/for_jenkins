package com.zklcsoftware.basic.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * 日期Util类
 */
public class DateUtil {

	private static String defaultDatePattern = "yyyy-MM-dd";
	private static String[] dateFormat = { "yyyy-MM-dd HH:mm:ss",
			"yyyy/MM/dd HH:mm:ss", "yyyy年MM月dd日HH时mm分ss秒", "yyyy-MM-dd",
			"yyyy/MM/dd", "yy-MM-dd", "yy/MM/dd", "yyyy年MM月dd日", "HH:mm:ss",
			"yyyyMMddHHmmss", "yyyyMMdd", "yyyy.MM.dd", "yy.MM.dd", "HH:mm" };

	/**
	 * 获得默认的date pattern
	 */

	public static String getDatePattern() {

		return defaultDatePattern;
	}

	/**
	 * 返回预设Format的当前日期字符串
	 */
	public static String getToday() {

		Date today = new Date();
		return format(today);
	}
	
	/**
	 * 返回预设Format的当前日期字符串
	 */
	public static String getTodayByYMD() {

		Date today = new Date();
		return formate(today,"yyyy-MM-dd");
	}
	
	/**
	 * 返回预设Format的date
	 */
	public static String getTodayByYMD(Date date) {
		return formate(date,"yyyy-MM-dd");
	}
	
   /**
     * 返回预设Format的time
     */
    public static String getTodayByHM(Date date) {
        return formate(date,"HH:mm");
    }

	/**
	 * 返回预设Format格式化Date成字符串
	 */
	private static String format(Date date) {

		return date == null ? "" : formate(date, getDatePattern());
	}

	/**
	 * 使用参数Formate格式化Date成字符串
	 */
	public static String formate(Date date, String datePattern) {

		return date == null ? "" : new SimpleDateFormat(datePattern)
				.format(date);
	}

	/**
	 * 使用预设格式将字符串转为Date
	 */

	public static Date parse(String strDate) throws ParseException {

//		System.out.println(strDate);
		return StringUtils.isBlank(strDate) ? null : parse(strDate,
				getDatePattern());
	}

	private static Date parse(String strDate, String datePattern)
			throws ParseException {

		return StringUtils.isAlpha(strDate) ? null : new SimpleDateFormat(
				datePattern).parse(strDate);
	}

	/**
	 * 在日期上增加数个整月
	 */
	public static Date addMonth(Date date, int n) {
	    
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}
	
	/**
	 * 在日期上增加数一天
	 */
	public static Date addDay(Date date, int n) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, n);
		return cal.getTime();
	}

	/**
     * 
     * 描述:获取下一个月的第一天.
     * 
     * @return
     */
    public static String getPerFirstDayOfMonth(int index,int andmonth) {
        SimpleDateFormat dft = new SimpleDateFormat(dateFormat[index]);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, andmonth);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }

	
	/**
	 * 取得指定月份的第一天
	 * 
	 * @param strdate
	 *            String
	 * @return String
	 * @throws Exception
	 */
	public static String getMonthBegin(String strdate) throws Exception {

		java.util.Date date = parse(strdate);
		return formatDateByFormat(date, "yyyy-MM") + "-01";
	}

	/**
	 * 取得某月最后一天
	 * 
	 * @param strdate
	 *            String
	 * @return String
	 * @throws Exception
	 */
	public static String getMonthEnd(Date date) throws Exception {

	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
	    Calendar ca = Calendar.getInstance(); 
	    ca.setTime(date);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
        String last = format.format(ca.getTime());
	    
		return last;
	}

	/**
	 * 常用的格式化日期
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String formatDate(java.util.Date date) {

		return formatDateByFormat(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 常用的格式化日期
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String formatDateByMin(java.util.Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String result = sdf.format(date);
		return result;
	}

	/**
	 * 常用的格式化日期
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String formatDateBySec(java.util.Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String result = sdf.format(date);
		return result;
	}

	/**
	 * 以指定的格式来格式化日期
	 * 
	 * @param date
	 *            Date
	 * @param format
	 *            String
	 * @return String
	 */
	public static String formatDateByFormat(java.util.Date date, String format) {

		String result = "";
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {

				ex.printStackTrace();
			}
		}
		return result;
	}

	public static Timestamp convUtilCalendarToSqlTimestamp(Calendar date) {

		if (date == null)
			return null;

		return new Timestamp(date.getTimeInMillis());
	}

	public static Calendar convSqlTimestampToUtilCalendar(Timestamp date) {

		if (date == null)
			return null;

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(date.getTime());
		return gc;
	}

	public static Calendar parseDate(String dateStr) {

		if ((dateStr == null) || (dateStr.trim().length() == 0))
			return null;

		Date result = parseDate(dateStr, 0);
		Calendar cal = Calendar.getInstance();
		cal.setTime(result);

		return cal;
	}

	public static String toDateTimeStr(Calendar date) {

		if (date == null)
			return null;
		return new SimpleDateFormat(dateFormat[0]).format(date.getTime());
	}

	public static String toDateStr(Calendar date) {

		if (date == null)
			return null;
		return new SimpleDateFormat(dateFormat[3]).format(date.getTime());
	}

	public static String toDateStr(Date date, int index) {

		if (date == null)
			return null;
		return new SimpleDateFormat(dateFormat[index]).format(date);
	}

	public static int calendarMinus(Calendar d1, Calendar d2) {

		if ((d1 == null) || (d2 == null)) {
			return 0;
		}

		d1.set(11, 0);
		d1.set(12, 0);
		d1.set(13, 0);

		d2.set(11, 0);
		d2.set(12, 0);
		d2.set(13, 0);

		long t1 = d1.getTimeInMillis();
		long t2 = d2.getTimeInMillis();
		long daylong = 86400000L;
		t1 -= t1 % daylong;
		t2 -= t2 % daylong;

		long t = t1 - t2;
		int value = (int) (t / daylong);

		return value;
	}

	/**** 
	  * 传入具体日期 ，返回具体日期减少一天 
	  * @param date 日期(2017-04-13) 
	  * @return 2017-04-12
	  * @throws ParseException 
	  */  
	 public static String subDay(Date date, Integer num){  
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
	     Calendar rightNow = Calendar.getInstance();  
	     rightNow.setTime(date);  
	     rightNow.add(Calendar.DAY_OF_MONTH, num);  
	     Date dt1 = rightNow.getTime();  
	     String reStr = sdf.format(dt1);  
	     return reStr;  
	 }
	 
	public static Date parseDate(String dateStr, int index) {

		if (dateStr == null || "".equals(dateStr)) {
			return null;
		}
		DateFormat df = null;
		try {
			df = new SimpleDateFormat(dateFormat[index]);

			return df.parse(dateStr);
		} catch (ParseException pe) {
			return parseDate(dateStr, index + 1);
		} catch (ArrayIndexOutOfBoundsException aioe) {
			return null;
		}
	}

	public static Date getNextDay(int day) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}

	public static String addDateMinut(String day, int x)// 返回的是字符串型的时间
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");// 24小时制
		Date date = null;
		try {
			date = format.parse(day);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (date == null)
			return "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, x);// 24小时制
		date = cal.getTime();
		return format.format(date);

	}

	public static String addDateSec(String day, int x)// 返回的是字符串型的时间
	{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制
		Date date = null;
		try {
			date = format.parse(day);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (date == null)
			return "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, x);// 24小时制
		date = cal.getTime();
		return format.format(date);

	}
	
	public static String addDateDay(String day, int x,int index)// 返回的是字符串型的时间
    {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat[index]);// 24小时制
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null)
            return "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, x);
        date = cal.getTime();
        return format.format(date);

    }

	public static String getDateTime(long time) {
		Date dat = new Date(time);
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dat);
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String startTime = format.format(gc.getTime());
		return startTime;
	}
	
    /**
     * 根据日期获得星期
     * @param date
     * @return
     */
	public static String getWeekOfDate(Date date) {
		  //String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		  String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" };
		  Calendar calendar = Calendar.getInstance();
		  calendar.setTime(date);
		  int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		  return weekDaysCode[intWeek];
	} 
	
	/**
	  * 获得周几的日期
	  *
	  * @param date
	  * @return
	  */
	public static String getWeekOfDay(Date date, int week) {

	  Calendar calendar = Calendar.getInstance();
	  calendar.setTime(date);
	  int value=0;
	  if(week == 0){
		  value = Calendar.SUNDAY;
	  }else if(week == 1){
		  value = Calendar.MONDAY;
	  }else if(week == 2){
		  value = Calendar.TUESDAY;
	  }else if(week == 3){
		  value = Calendar.WEDNESDAY;
	  }else if(week == 4){
		  value = Calendar.THURSDAY;
	  }else if(week == 5){
		  value = Calendar.FRIDAY;
	  }else if(week == 6){
		  value = Calendar.SATURDAY;
	  }
	  calendar.set(Calendar.DAY_OF_WEEK, value);

	  return DateUtil.getTodayByYMD(calendar.getTime());

	} 
	
	// 获得本周一与当前日期相差的天数
    public static  int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } else {
            return 2 - dayOfWeek;
        }
    }
	
	// 获得当前周- 周一的日期
    public static  String getCurrentMonday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String preMonday = df.format(monday);
        return preMonday;
    }

    // 获得当前周- 周日  的日期
    public static String getPreviousSunday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus +6);
        Date monday = currentDate.getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String preMonday = df.format(monday);
        return preMonday;
    }
	
    
    /**
     * 获取两个日期之间的所有日期
     * 
     * @param startTime
     *            开始日期
     * @param endTime
     *            结束日期
     * @return
     */
    public static List<String> getDays(String startTime, String endTime) {

        // 返回的日期集合
        List<String> days = new ArrayList<String>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return days;
    }
    
    /**
     * 
    * @Description: 判断两个日期大小 
    * @param @param DATE1
    * @param @param DATE2
    * @throws
     */
    public static int compare_date(String DATE1, String DATE2, int index) {
        
        DateFormat df = new SimpleDateFormat(dateFormat[index]);
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() < dt2.getTime()) {
//                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() > dt2.getTime()) {
//                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
    
    /**
     * 
    * @Title: getNextWeekFirstDay  
    * @Description: 获取下周一日期
    * @param @return    参数  
    * @return String    返回类型  
     */
    public static String getNextWeekFirstDay() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        
        if(day != Calendar.SUNDAY)
         cal.add(Calendar.WEEK_OF_MONTH, 1);
        
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//        System.out.println(cal.getTime());
        return df.format(cal.getTime());
    }
    
    /**
     * 
    * @Title: getNextWeekLastDay  
    * @Description: TODO(这里用一句话描述这个方法的作用)  
    * @param @return    参数  
     */
    public static String getNextWeekLastDay(int weeknum) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        
        if(day != Calendar.SUNDAY)
         cal.add(Calendar.WEEK_OF_MONTH, 1+weeknum);
        else
         cal.add(Calendar.WEEK_OF_MONTH, weeknum);
        
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        
//        System.out.println(cal.getTime());
        return df.format(cal.getTime());
    }

    
/*	public static void main(String[] args) {
		System.out.println(getWeekOfDay(new Date(),4));
	}*/
}
