package org.cnq.test.cache;

import java.util.Properties;


public interface BaseCache {

	
	/*************缓存Key定义开始***********************/
	public static final String PARENT_CATEGORY_ID_KEY = "parent_category_id_";
	
	public static final String Load_AVAILABLE_CATEGORY_KEY = "load_available_category";
	
	public static final String PREFIX_CHECK_CODE_KEY = "checkcode_";
	
	
	
	/*************缓存Key定义结束***********************/
	
	
	public static int PERIOD = 60*60*1000;
	/**
	 * 根据key从缓存中取出对象
	 * @param key 对象key
	 * @return 对象
	 */
	public Object getFromCache(String key);

	/**
	 * 根据key从缓存中取出对象
	 * @param key 对象key
	 * @param refreshPeriod 在缓存中存在的时长(秒)
	 * @return 对象
	 */
	public Object getFromCache(String key, int refreshPeriod);

	/**
	 * 把对象放到缓存中
	 * 
	 * @param key 对象key
	 * @param content 对象
	 */
	public void putIntoCache(String key, Object content);

	/**
	 * 把对象放到缓存中
	 * @param key   对象KEY
	 * @param content   对象
	 * @param refreshPeriod  保存时间 the refresh period in seconds
	 */
	public void putIntoCache(String key, Object content, int seconds);
	

	/**
	 * 从缓存中删除所有对象
	 */
	public void flushAll();

	/**
	 * 从缓存中删除指定日期内的对象
	 * @param date 日期
	 */
	public void flushAll(java.util.Date date);

	/**
	 * 根据key删除指定的对象
	 * @param key 对象key
	 */
	public void flushEntry(String key);
	
	
	public void removeEntry(String key);

	/**
	 * 销毁缓存对象
	 */
	public void destory();

	/**
	 * 设置缓存属性文件
	 * 
	 * @param prop 属性文件
	 */
	public void setPropFile(Properties prop);
	
	public String getStringFromCache(String key);
	
	


}
