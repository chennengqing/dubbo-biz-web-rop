package org.cnq.test.web.util;

import com.appleframework.config.core.PropertyConfigurer;

public class Constants {
	
	/**
	 * 图片服务器
	 * @return
	 */
	public static String getImagerServer()
	{
		return PropertyConfigurer.getString("imager_server");
	}
	
	public static String getRedisServers(){
		return PropertyConfigurer.getString("redis.server");
	}
	
	public static String getCallBackUrl(){
		return PropertyConfigurer.getString("callBackUrl");
	}
	
	public static int getRedisMaxIdle()
	{
		String tmpVal = PropertyConfigurer.getString("redis.maxidle");
		if(tmpVal != null && tmpVal.length()>0){
			
			return Integer.valueOf(tmpVal);
		}
		else{
			
			return 10;
		}
		
	}
	
	public static int getRedisMaxTotal()
	{
		String tmpVal = PropertyConfigurer.getString("redis.maxtotal");
		if(tmpVal != null && tmpVal.length()>0){
			
			return Integer.valueOf(tmpVal);
		}
		else{
			
			return 100;
		}
		
	}
	
	public static long getRedisMaxWaitMillis()
	{
		String tmpVal = PropertyConfigurer.getString("redis.maxwaitmillis");
		if(tmpVal != null && tmpVal.length()>0){
			
			return Long.valueOf(tmpVal);
		}
		else{
			
			return 100;
		}
		
	}
	
	public static String getHtmlRootDir(){
		
		return PropertyConfigurer.getString("html.root");
		
	}
}
