package com.jiuzhi.ad.rop.util;

import java.util.Random;
/**
 * 
 * 项目名称:rop-zdb   
 * 类名称:IntegerUtil   
 * 类描述:hejunwen
 * 创建人:数字工具类
 * 创建时间:2015-1-2 下午5:46:57   
 * @version
 */
public class IntegerUtil {
	
	/**
	 * @Title: getRandomNumber 
	 * @Description: 生产32为的数字
	 * @return long    
	 * @throws
	 */
	public static long getRandomNumber(){
	        String rad = "0123456789"; 
	        StringBuffer result = new StringBuffer(); 
	        Random rand = new Random(); 
	        int length = 18; 
	        for (int i = 0; i < length; i++) { 
	            int randNum = rand.nextInt(10); 
	            result.append(rad.substring(randNum, randNum + 1)); 
	        } 
	        return new Long(result.toString()); 
	}
	/**
	 * @Title: getRandomNumber 
	 * @Description: 生产32为的数字
	 * @return long    
	 * @throws
	 */
	public static String getRandomNumber(int count){
	        String rad = "0123456789"; 
	        StringBuffer result = new StringBuffer(); 
	        Random rand = new Random(); 
	        for (int i = 0; i < count; i++) { 
	            int randNum = rand.nextInt(10); 
	            result.append(rad.substring(randNum, randNum + 1)); 
	        } 
	        return result.toString(); 
	}
}
