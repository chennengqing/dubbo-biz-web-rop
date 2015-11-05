package org.cnq.test.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtils {

	public static final String PATTERN_DATE = "yyyy-MM-dd";
	
	public static final String PATTERN_TIME = "yyyy-MM-dd HH:mm:ss";
	
	public static String format(Date date,String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
}
