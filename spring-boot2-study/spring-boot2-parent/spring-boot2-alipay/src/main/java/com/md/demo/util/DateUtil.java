package com.md.demo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.lang.time.DateUtils;

/**
 * 日期工具类
 */
public final class DateUtil {

	public static final int DATE_NUM = 0;

	public static final String LONG_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String LONG_DATETIME_FORMAT_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

	public static final String LONGDATETIMEFORMAT = "yyyyMMddHHmmss";

	public static final String DATETIMEFORMAT = "yyyyMMdd";

	private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm";

	private static final String DATETIME_FORMAT_HOURS = "yyyy-MM-dd HH";

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	private static final String TIME_FORMAT = "HH:mm";

	/**
	 * 获取指定月的对应天数
	 */
	public static final int[] DAYS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	/**
	 * convert to millisecond
	 */
	public static final long TIME_ONE_HOUR = 3600L * 1000L;

	public static final long TIME_ONE_DAY = 24L * TIME_ONE_HOUR;

	/**
	 * Convert date to String like "yyyy-MM-dd HH:mm:ss".
	 */
	public static String getCurrentLongDateTime() {
		return new SimpleDateFormat(LONG_DATETIME_FORMAT).format(DateUtils.addHours(new Date(), DATE_NUM));
	}

	/**
	 * Convert date to String like "yyyy-MM-dd HH:mm:ss".
	 */
	public static String getCurrentLongDateTime(int num) {
		return new SimpleDateFormat(LONG_DATETIME_FORMAT).format(DateUtils.addDays(new Date(), num));
	}

	/**
	 * Convert date to String like "yyyy-MM-dd".
	 */
	public static String getCurrentDate() {
		return new SimpleDateFormat(DATE_FORMAT).format(DateUtils.addHours(new Date(), DATE_NUM));
	}

	public static String getCurrentDate(int num) {
		return new SimpleDateFormat(DATE_FORMAT).format(DateUtils.addDays(new Date(), num));
	}

	/**
	 * 返回当前系统时间（字符串格式：yyyy-MM-dd HH:mm:ss）
	 * 
	 * @return String
	 */
	public static String getDateTime() {
		Date d = DateUtils.addHours(new Date(), DATE_NUM);
		SimpleDateFormat sdfreplay = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String replytime = sdfreplay.format(d);
		return replytime;
	}

	/**
	 * 返回一个随机数，组成格式"yyyyMMddhhmmss"+10000以内的随机数
	 * 
	 * @return
	 */
	public static String buildRandomFileName() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdfreplay = new SimpleDateFormat("yyyyMMddHHmmss");
		String replytime = sdfreplay.format(cal.getTime());
		return replytime + new Random().nextInt(1000);
	}

	/**
	 * Convert date to String like "yyyyMMddHHmmss".
	 */
	public static String getCurrentLongDateTime2() {
		return new SimpleDateFormat(LONGDATETIMEFORMAT).format(new Date());
	}

	/**
	 * Convert date to String like "yyyyMMdd".
	 */
	public static String getCurrentDate2(int num) {
		return new SimpleDateFormat(DATETIMEFORMAT).format(DateUtils.addDays(new Date(), num));
	}

	/**
	 * Convert date to String like "yyyy-MM-dd HH:mm".
	 */
	public static String getCurrentDateTime() {
		return new SimpleDateFormat(DATETIME_FORMAT).format(DateUtils.addHours(new Date(), DATE_NUM));
	}

	/**
	 * Convert date to String like "yyyy-MM-dd HH".
	 */
	public static String getCurrentDateTimeHours(int num) {
		return new SimpleDateFormat(DATETIME_FORMAT_HOURS).format(DateUtils.addHours(new Date(), num));
	}

	/**
	 * Convert date to String like "yyyy-MM-dd HH".
	 */
	public static String getCurrentDateTimeMinutes(int num) {
		return new SimpleDateFormat(DATETIME_FORMAT).format(DateUtils.addMinutes(new Date(), num));
	}

	/**
	 * Convert date to String like "yyyy-MM-dd HH:mm:ss".
	 */
	public static String getCurrentDateTimeMinutesSecond(int num) {
		return new SimpleDateFormat(LONG_DATETIME_FORMAT).format(DateUtils.addMinutes(new Date(), num));
	}

	/**
	 * Convert date to String like "yyyy-MM-dd HH:mm:ss".
	 */
	public static String getCurrentDateTimeSecond(int num) {
		return new SimpleDateFormat(LONG_DATETIME_FORMAT).format(DateUtils.addSeconds(new Date(), num));
	}

	/**
	 * Convert time to string like "HH:mm".
	 */
	public static String formatTime(Date d) {
		return new SimpleDateFormat(TIME_FORMAT).format(d);
	}

	/**
	 * Convert date and time to string like "yyyy-MM-dd HH:mm".
	 */
	public static String formatDateTime(Date d) {
		return new SimpleDateFormat(DATETIME_FORMAT).format(d);
	}

	/**
	 * Convert date to String like "yyyy-MM-dd".
	 */
	public static String formatDate(Date d) {
		return new SimpleDateFormat(DATE_FORMAT).format(d);
	}

	/**
	 * Convert date and time to string like "yyyy-MM-dd HH:mm:ss".
	 */
	public static String formatLongDate(Date d) {
		return new SimpleDateFormat(LONG_DATETIME_FORMAT).format(d);
	}

	/**
	 * Convert date and time to string like "yyyy-MM-dd HH:mm:ss SSS".
	 */
	public static String formatLongDateSSS(Date d) {
		return new SimpleDateFormat(LONG_DATETIME_FORMAT_SSS).format(d);
	}

	/**
	 * Parse date like "yyyy-MM-dd".
	 */
	public static Date parseDate(String d) {
		try {
			return new SimpleDateFormat(DATE_FORMAT).parse(d);
		} catch (ParseException e) {
		}
		return null;
	}

	/**
	 * Parse date and time like "yyyy-MM-dd HH:mm".
	 */
	public static Date parseDateTime(String dt) {
		try {
			return new SimpleDateFormat(DATETIME_FORMAT).parse(dt);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Parse date and time like "yyyy-MM-dd HH:mm:ss".
	 */
	public static Date parseLongDateTime(String dt, String format) {
		try {
			return new SimpleDateFormat(format).parse(dt);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Parse date and time like "yyyy-MM-dd HH:mm:ss".
	 */
	public static Date parseLongDateTime(String dt) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dt);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Convert date and time to string like "yyyy-MM-dd HH:mm".
	 */
	public static String formatLongDateTime(Date d, String format) {
		return new SimpleDateFormat(format).format(d);
	}

	/*
	 * 判断date是否在开始日期和结束日期之间
	 */
	public static boolean includes(Date fromDay, Date toDay, Date date) {
		long fromTime = fromDay.getTime();
		long toTime = toDay.getTime();
		long dateTime = date.getTime();
		return ((fromTime <= dateTime) && (dateTime < toTime));
	}

	/*
	 * 判断date是否在开始日期和结束日期之间
	 * 
	 */
	public static boolean includess(Date fromDay, Date toDay, Date date) {
		long fromTime = fromDay.getTime();
		long toTime = toDay.getTime();
		long dateTime = date.getTime();
		return ((fromTime <= dateTime) && (dateTime <= toTime));
	}

	/*
	 * 给定的日期,增加指定的天数 除去周六周日之外
	 */
	public static String addDays(String dateStr, int days, Properties holidays) {
		SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT);
		Date current = null;
		try {
			current = sf.parse(dateStr);
			int cuDay = 0;
			for (int i = 0; i < days; i++) {
				current.setDate(current.getDate() + 1);
				cuDay = current.getDay();
				/* 除去节假日 */
				Iterator iter = holidays.keySet().iterator();
				while (iter.hasNext()) {
					String key = (String) iter.next();
					if (key.startsWith("holiday.")) {
						String[] va = holidays.getProperty(key).split("&");
						boolean re = includes(sf.parse(va[0]), sf.parse(va[1]), current);
						if (re) {
							if (cuDay != 0 && cuDay != 6) {
								i--;
							}
						}
					}
				}
				/* 除去周六周日 */
				if (cuDay == 0 || cuDay == 6) {
					i--;
				}
			}
		} catch (ParseException e) {
			current = DateUtils.addHours(new Date(), DATE_NUM);
		}
		return sf.format(current);
	}

	/*
	 * 给定日期和系统当前日期的天数差 除去周六周日之外
	 */
	public static int getDayFromToday(String start, Properties holidays) {
		int day = -1;
		boolean negative = false;
		try {
			SimpleDateFormat sf = new SimpleDateFormat(DATE_FORMAT);
			Date startDate = sf.parse(start);
			Date endDate = DateUtils.addHours(new Date(), DATE_NUM);/* 系统当前日期 */
			int cuDay = 0;
			Date temp = null;
			if (endDate.before(startDate)) {
				temp = startDate;
				startDate = endDate;
				endDate = temp;
				negative = true;
			}
			while (endDate.compareTo(startDate) > 0) {
				startDate.setDate(startDate.getDate() + 1);
				cuDay = startDate.getDay();
				/* 除去节假日 */
				Iterator iter = holidays.keySet().iterator();
				while (iter.hasNext()) {
					String key = (String) iter.next();
					if (key.startsWith("holiday.")) {
						String[] va = holidays.getProperty(key).split("&");
						boolean re = includes(sf.parse(va[0]), sf.parse(va[1]), startDate);
						if (re) {
							if (cuDay != 0 && cuDay != 6) {
								day++;
							}
						}
					}
				}
				if (cuDay > 0 && cuDay < 6) {
					day++;
				}
			}
		} catch (ParseException e) {
		}

		return negative == true ? day : -day;
	}

	public static boolean isLeapYear(int year) {
		if (year % 100 == 0) {
			return year % 400 == 0;
		}
		return year % 4 == 0;
	}

	/**
	 * 将10-05-2013 转换为 2013-10-05
	 */
	public static String changedate(String d) {
		String ret = d;
		try {
			String tmp = d.substring(d.length() - 4, d.length());
			ret = tmp + "-" + d;
			ret = ret.substring(0, 10);
		} catch (Exception e) {
			return ret;
		}
		return ret;
	}

	/**
	 * 将01/10/2013 转换为 2013-10-01
	 */
	public static String changedates(String d) {
		String ret = d;
		try {
			String tmp = d.substring(3, 5);
			String tmpday = d.substring(0, 2);
			String tmpyew = d.substring(6, 10);
			ret = tmpyew + "-" + tmp + "-" + tmpday;

		} catch (Exception e) {
			return ret;
		}
		return ret;
	}

	/**
	 * 获取周几的中文名称，比如周一
	 * 
	 * @param key
	 * @return
	 */
	public static String getWeekDesc(String key) {
		String[][] keys = initWeeks();
		for (int i = 0; i < keys.length; i++) {
			String[] temp = keys[i];
			for (int j = 0; j < temp.length; j++) {
				if (key.equals(temp[0])) {
					return temp[1];
				}
			}
		}
		return "不存在对应的解析描述";
	}

	private static String[][] initWeeks() {
		String[][] KEY_VALUE = { { "1", "周一" }, { "2", "周二" }, { "3", "周三" }, { "4", "周四" }, { "5", "周五" },
				{ "6", "周六" }, { "7", "周日" } };
		return KEY_VALUE;
	}

	public static long getDateDays(Date nowDate, Date oldDate) {
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		// long ns = 1000;
		// 获得两个时间的毫秒时间差异
		long diff = nowDate.getTime() - oldDate.getTime();
		// 计算差多少天
		// long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		// long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		// long sec = diff % nd % nh % nm / ns;
		// return day + "天" + hour + "小时" + min + "分钟";
		return hour;
	}

	// public static void main(String[] args) {
	// Date d1 = DateUtil.parseLongDateTime("2017-04-25 14:12:12");
	// Date d2 = DateUtil.parseLongDateTime("2017-04-25 11:12:11");
	// long a = getDateDays(d2, d1);
	// System.out.println(a);
	// }

	/**
	 * 根据日期取得星期几
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeek(Date date) {
		String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week_index < 0) {
			week_index = 0;
		}
		if (week_index == 0) {
			week_index = 7; // 周日
		}
		return week_index;
		// SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		// String week = sdf.format(date);
		// return week;
	}

	/**
	 * 得到指定日期在一个月中的哪一天
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		int a = ca.get(Calendar.DAY_OF_MONTH);
		return a;
	}

	/**
	 * 将时间转换为时间戳
	 */
	public static String dateToStamp(String s) {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = simpleDateFormat.parse(s);
			long ts = date.getTime();
			res = String.valueOf(ts);
			return res;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将时间戳转换为时间
	 */
	public static String stampToDate(long timeStamp) {
		String result = null;
		Date date = new Date(timeStamp * 1000);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		result = sd.format(date);
		return result;
	}

	/**
	 * 两个时间相差距离多少天多少小时多少分多少秒
	 * 
	 * @param str1 时间参数 1 格式：1990-01-01 12:00:00
	 * @param str2 时间参数 2 格式：2009-01-01 12:00:00
	 * @return long[] 返回值为：{天, 时, 分, 秒}
	 */
	public static long[] getDistanceTimes(String str1, String str2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date one;
		Date two;
		long day = 0;
		long hour = 0;
		long min = 0;
		long sec = 0;
		try {
			one = df.parse(str1);
			two = df.parse(str2);
			long time1 = one.getTime();
			long time2 = two.getTime();
			long diff;
			if (time1 < time2) {
				diff = time2 - time1;
			} else {
				diff = time1 - time2;
			}
			day = diff / (24 * 60 * 60 * 1000);
			hour = (diff / (60 * 60 * 1000) - day * 24);
			min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long[] times = { day, hour, min, sec };
		return times;
	}

	/**
	 * 将时间转换为时间戳
	 */
	public static long dateToStamp2(String s) {
		if (s.equals("0")) {
			// 如果为空，则默认为当前时间
			s = getCurrentLongDateTime();
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = simpleDateFormat.parse(s);
			long ts = date.getTime() / 1000;
			return ts;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date().getTime() / 1000;
	}

	/**
	 * 将时间戳转换为时间
	 */
	public static String stampToDate2(long timeStamp) {
		String result = null;
		Date date = new Date(timeStamp * 1000);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		result = sd.format(date);
		return result;
	}

	/**
	 * 加减日期
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date operDay(Date date, int day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date);
		rightNow.add(Calendar.DAY_OF_YEAR, day);
		Date dt1 = rightNow.getTime();
		return dt1;
	}

	/**
	 * 加减日期
	 * 
	 * @param time 传入的String字符串
	 * @param day  加减多少天
	 * @param flag 加减类型标识，1=加减天数，2=加减小时
	 * @return
	 */
	public static String operDay2(String time, int day, int flag) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		Date date = new Date();
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(date);
		if (flag == 1) {
			rightNow.add(Calendar.DAY_OF_YEAR, day);
		} else {
			rightNow.add(Calendar.HOUR_OF_DAY, day);
		}
		Date dt1 = rightNow.getTime();
		return sdf.format(dt1);
	}

	/**
	 * 将HH:mm:ss转变成秒数
	 * 
	 * @param s
	 * @return
	 */
	public static long toFormatSecond(String s) {
		String[] time = s.split(":");
		long hourToSecond = Integer.parseInt(time[0]) * 3600;
		long minToSecond = Integer.parseInt(time[1]) * 60;
		long second = Integer.parseInt(time[2]);
		return hourToSecond + minToSecond + second;
	}

	public static long getStamp() {
		return System.currentTimeMillis() / 1000;
	}

	public static void main(String[] args) {
//		/* System.out.println(stampToDate2(1517228653L)); */
//		System.out.println(DateUtil.getCurrentDateTimeMinutesSecond(-1440));
//		System.out.println(DateUtil.getCurrentDateTimeMinutesSecond(0));
//		System.out.println(DateUtil.getCurrentDateTimeSecond(-5));

		System.out.println(getStamp());
	}
}