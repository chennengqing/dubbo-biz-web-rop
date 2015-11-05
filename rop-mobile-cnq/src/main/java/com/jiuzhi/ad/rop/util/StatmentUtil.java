package com.jiuzhi.ad.rop.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 项目名称:rop-zdb   
 * 类名称:StatmentUtil   
 * 类描述:报表工具类   
 * 创建人:ZhongCheng 
 * 创建时间:2015-1-10 16:10:20
 * @version v1.0
 */
public class StatmentUtil {

	/**
 	* @Title: checkTime 
 	* @Description: 比较起始和结束时间是否满足要求,日不超过365天|月不超过24个月
 	* @param String startTime, String endTime, int stype  
 	* 			stype : 0为多少天，1为多少个月
 	* @return boolean
 	* @throws 
 	* @author ZhongCheng
    */
	public static boolean checkTime (String startTime, String endTime, int stype){
		//取得时间差
		int i = compareTime(startTime, endTime, stype);
		/****************************判断是否满足时间差条件****************************/
		boolean bool = false ;
		//0判断天数不能超过365天
		if (stype == 0) {
			if ( i > 365 ) {
				bool = false;
			} else {
				bool = true;
			}
		}
		//判断月数不能超过24个月
		if (stype == 1) {
			if ( i > 24 ) {
				bool = false;
			} else {
				bool = true;
			}
		}
		
		return bool;
	}

	/**
 	* @Title: compareTime 
 	* @Description: 比较起始和结束时间相隔时间
 	* @param String startTime, String endTime, int stype
 	* 			stype : 0为多少天，1为多少个月，2为多少年  
 	* @return boolean
 	* @throws 
 	* @author ZhongCheng
    */
	public static int compareTime(String startTime, String endTime, int stype){
		int n = 0;  
		//判断需要转化的时间格式
        String formatStyle = stype==1?"yyyy-MM":"yyyy-MM-dd";  
        DateFormat df = new SimpleDateFormat(formatStyle);  
        Calendar c1 = Calendar.getInstance();  
        Calendar c2 = Calendar.getInstance();  
        try {  
            c1.setTime(df.parse(startTime));  
            c2.setTime(df.parse(endTime));  
        } catch (Exception e) {  
            e.printStackTrace(); 
        }  
        while (!c1.after(c2)) {                     // 循环对比，直到相等，n 就是所要的结果  
            n++;  
            if(stype==1){  
                c1.add(Calendar.MONTH, 1);          // 比较月份，月份+1  
            }  
            else{  
                c1.add(Calendar.DATE, 1);           // 比较天数，日期+1  
            }  
        }  
        n = n-1;  
        if(stype==2){  
            n = (int)n/365;  
        } 
        return n;
	}
	
	/**
 	* @Title: formatDate 
 	* @Description: 时间戳转日期字符串  yyyyMMdd
 	* @param Long date, String timeStype
 	* @return String
 	* @throws 
 	* @author ZhongCheng
    */
	public static String formatDate(Long date, String timeStype) {
	    SimpleDateFormat sdf=new SimpleDateFormat(timeStype);  
	    String sd = sdf.format(new Date(date));  
	    return sd;
	}
	
}
