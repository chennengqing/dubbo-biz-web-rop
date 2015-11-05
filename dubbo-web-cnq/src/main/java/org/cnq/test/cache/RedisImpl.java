package org.cnq.test.cache;

import java.util.Date;
import java.util.Properties;

import org.cnq.test.web.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedisImpl implements BaseCache{

	private static Logger logger = LoggerFactory.getLogger(RedisImpl.class);
	
	
	@Override
	public Object getFromCache(String key) {
		
		try{
			
			return RedisUtil.getObject(key);
		}
		catch(Exception ex){
			
			logger.error("getFromCache", ex);
		}
		return null;
		
	
	}

	@Override
	public Object getFromCache(String key, int refreshPeriod) {
		
        try{
			
			return RedisUtil.getObject(key);
		}
		catch(Exception ex){
			
			logger.error("getFromCache", ex);
		}
		return null;
	}

	@Override
	public void putIntoCache(String key, Object content) {
		
		 try{
				
			 if(content instanceof String){
					
					RedisUtil.setString(key, (String)content);
				}
				else{
					
					RedisUtil.setObject(key, content);
				}
		  }
			catch(Exception ex){
				
				logger.error("putIntoCache", ex);
		 }
		 
		
		
	}

	@Override
	public void putIntoCache(String key, Object content, int seconds) {
		
		 try{
				
			 if(content instanceof String){
					
					RedisUtil.setString(key, (String)content);
				}
				else{
					
					RedisUtil.setObject(key, content);
				}
		        RedisUtil.setExpire(key, seconds);
		  }
			catch(Exception ex){
				
				logger.error("putIntoCache", ex);
		 }
		 
		
       
		
	}

	@Override
	public void flushAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flushAll(Date date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flushEntry(String key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeEntry(String key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPropFile(Properties prop) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getStringFromCache(String key) {
		

		 try{
				
			 return RedisUtil.getString(key);
		  }
			catch(Exception ex){
				
				logger.error("putIntoCache", ex);
		 }
		 
		 return null;
		
	}

	
}
