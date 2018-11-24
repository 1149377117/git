package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	/**
	 * 根据指定的日期字符串和掩码，返回日期值
	 * @param dateString	2018-01-01
	 * @param mask	yyyy-MM-dd
	 * @return	返回日期对象
	 * @throws ParseException 
	 */
	public static Date getDate(String dateString,String mask){
		try {
			return new SimpleDateFormat(mask).parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据指定的日期对象和掩码，返回格式化后的日期字符串
	 * @param date	日期对象
	 * @param mask	yyyy-MM-dd
	 * @return	返回日期字符串
	 */
	public static String format(Date date,String mask) {
		return new SimpleDateFormat(mask).format(date);
	}
}

