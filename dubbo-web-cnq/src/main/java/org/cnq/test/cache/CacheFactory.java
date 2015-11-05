package org.cnq.test.cache;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheFactory {

	private static Logger logger = LoggerFactory.getLogger(CacheFactory.class);
	
	private static CacheFactory facheFactory;
	
	private static BaseCache redisObject;
	
	private static Object lockObj = new Object();
	
	private CacheFactory(){
		
		redisObject = new RedisImpl();
	}

	public static CacheFactory getInstance(){
		
		if(facheFactory == null){
			
			synchronized(lockObj){
				
				if(facheFactory == null){
					
					facheFactory = new CacheFactory();
				}
			}
			
		}
		
		return facheFactory;
		
	}
	

	public BaseCache getRedis() {
		
		return redisObject;
	}

	

}
