package com.wuyubin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author 吴宇斌
 *
 * 2019年11月7日
 */
public class DateUtils {
	/**
	 * 
	 * @Title: compare  
	 * @Description: TODO  
	 * @param @param date1
	 * @param @param date2
	 * @param @return     
	 * @return int    
	 * @throws
	 */
	public static int compare(Date date1,Date date2) {
		if (date1==null || date2==null) {
			throw new RuntimeException("参数不能为空");
		}
		return date1.compareTo(date2);
	}
	/**
	 * 
	 * @Title: calculateAge  
	 * 计算年龄
	 * @param @param birthday
	 * @param @return     
	 * @return int    
	 * @throws
	 */
	public static int calculateAge(Date birthday) {

		if (birthday.compareTo(new Date())>0) {
			throw new RuntimeException("生日不能大于当前的日期"+birthday);
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(birthday);

		int bdYear = calendar.get(Calendar.YEAR);
		int bdMonth = calendar.get(Calendar.MONTH);
		int bdDay = calendar.get(Calendar.DAY_OF_MONTH);

		System.out.println("bdYear:"+bdYear+" bdMonth:"+bdMonth+" bdDay:"+bdDay);

		calendar.setTime(new Date());

		int currentYear = calendar.get(Calendar.YEAR);
		int currentMonth = calendar.get(Calendar.MONTH);
		int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

		System.out.println("currentYear:"+currentYear+" currentMonth:"+currentMonth+" currentDay:"+currentDay);

		int age=currentYear-bdYear;
		if (currentMonth<bdMonth) {
			age--;
		} else if(currentMonth==bdMonth && currentDay<bdDay){
			age--;
		}
		return age;
	}
	/**
	 * 
	 * @Title: remainDays  
	 * @Description: TODO  
	 * @param @param futureDate
	 * @param @return
	 * @param @throws CmsExcetion     
	 * @return int    
	 * @throws
	 */
	public int remainDays(Date futureDate) throws CmsExcetion{

		if (futureDate.compareTo(new Date())<0) {
			throw new CmsExcetion("未来日期不能小于当前日期"+futureDate);
		}

		int days=(int)(futureDate.getTime()-new Date().getTime())/1000/60/60/24;

		return days;
	}
	/**
	 * 
	 * @Title: isToday  
	 * @Description: TODO  
	 * @param @param date
	 * @param @return     
	 * @return boolean    
	 * @throws
	 */
	public static boolean isToday(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		String dateStr = sdf.format(date);


		String todayStr = sdf.format(new Date());

		return (dateStr.equals(todayStr));

	}

	/**
	 * 
	 * @Title: isThisWeek  
	 * @Description: TODO  
	 * @param @param date
	 * @param @return     
	 * @return boolean    
	 * @throws
	 */
	public static boolean isThisWeek(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		Calendar firstDayOfWeek = Calendar.getInstance(Locale.getDefault());

		firstDayOfWeek.setFirstDayOfWeek(Calendar.MONDAY);

		int day = firstDayOfWeek.get(Calendar.DAY_OF_WEEK);

		firstDayOfWeek.add(Calendar.DATE, -day + 1 + 1);



		System.out.println(format.format(firstDayOfWeek.getTime()));

		Calendar lastDayOfWeek = Calendar.getInstance(Locale.getDefault());

		lastDayOfWeek.setFirstDayOfWeek(Calendar.MONDAY);

		day = lastDayOfWeek.get(Calendar.DAY_OF_WEEK);

		lastDayOfWeek.add(Calendar.DATE, 7 - day + 1);



		System.out.println(format.format(lastDayOfWeek.getTime()));

		return (date.getTime() < lastDayOfWeek.getTime().getTime()
				&& date.getTime() > firstDayOfWeek.getTime().getTime());

	}

	/**
	 * 
	 * @Title: isThisMonth  
	 * @Description: TODO  
	 * @param @param date
	 * @param @return     
	 * @return boolean    
	 * @throws
	 */
	public boolean isThisMonth(Date date) {

		return false;
	}



	/**
	 * 
	 * @Title: getBOM  
	 * @Description: TODO  
	 * @param @param date
	 * @param @return     
	 * @return Date    
	 * @throws
	 */
	public static Date getBOM(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();

	}

	/**
	 * 
	 * @Title: getEOM  
	 * @Description: TODO  
	 * @param @param date
	 * @param @return     
	 * @return Date    
	 * @throws
	 */
	public static Date getEOM(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.SECOND, -1);

		return calendar.getTime();

	}

	/**
	 * 生成随机时间
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static Date randomDate(String beginDate, String endDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date start = format.parse(beginDate);// 构造开始日期
			Date end = format.parse(endDate);// 构造结束日期
			// getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
			if (start.getTime() >= end.getTime()) {
				return null;
			}
			long date = random(start.getTime(), end.getTime());
			return new Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static long random(long begin, long end) {
		long rtn = begin + (long) (Math.random() * (end - begin));
		// 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
		if (rtn == begin || rtn == end) {
			return random(begin, end);
		}
		return rtn;
	}

}
