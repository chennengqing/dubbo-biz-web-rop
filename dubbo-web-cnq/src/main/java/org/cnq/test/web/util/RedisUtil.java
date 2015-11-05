package org.cnq.test.web.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cnq.test.result.ItemsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisUtil {


	private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    private static ShardedJedisPool pool = null;
	
	private static Object lock = new Object();
	
	
	public static void main(String[] args) {
		
		
		ItemsResult result = new ItemsResult();
		setObject("obj",result);
		
		ItemsResult result2 = (ItemsResult)getObject("obj");
		if(result2 != null){
			
			System.out.println("get object");
		}
		
			
	}

	/**
	 * 构建redis连接池
	 * 
	 * @param ip
	 * @param port
	 * @return JedisPool
	 */
	public static ShardedJedisPool getPool() {
		
		if (pool == null) {
			
			synchronized(lock){
			
				try{
					
					// 生成多机连接信息列表
					List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
					
					
					String server = Constants.getRedisServers();
					List<String> serverList = ParseUtil.asArrayList(server, '|');
					if(serverList != null && serverList.size()>0){
						
						for(String str : serverList){
							
							String[] tmpVal = str.split("\\:");
							shards.add( new JedisShardInfo(tmpVal[0], Integer.valueOf(tmpVal[1])) );
						}
					}
				
					//shards.add( new JedisShardInfo("192.168.1.189", 6379) );
					// 生成连接池配置信息
					JedisPoolConfig config = new JedisPoolConfig();
					config.setMaxIdle(Constants.getRedisMaxIdle());
					config.setMaxTotal(Constants.getRedisMaxTotal());
					config.setMaxWaitMillis(Constants.getRedisMaxWaitMillis());
					

					// 在应用初始化的时候生成连接池
					pool = new ShardedJedisPool(config, shards);
				}
				catch(Exception ex){
					
					logger.error("getPool", ex);
				}
				
				
			}
		}
		return pool;
	}

	
	/**
	 * 释放连接
	 * @param redis
	 */
	public static void returnResource(ShardedJedis redis) {
		if (redis != null) {
			//返回链接池
			pool.returnResource(redis);
		}
	}
	
	/**
	 * 向缓存中设置字符串内容
	 * @param key key
	 * @param value value
	 * @return
	 * @throws Exception
	 */
	public static boolean  setString(String key,String value){
		ShardedJedis jedis = null;
		try {
			jedis = getPool().getResource();
			jedis.set(key, value);
			return true;
		} catch (Exception e) {
			logger.error("setString", e);
			return false;
		}finally{
			returnResource(jedis);
		}
	}
	
	/**
	 * 向缓存中设置对象
	 * @param key 
	 * @param value
	 * @return
	 */
	public static boolean  setObject(String key,Object value){
		ShardedJedis jedis = null;
		try {
			byte[] bytes = SerializeUtil.serialize(value);
			jedis = getPool().getResource();
			jedis.set(key.getBytes(), bytes);
			return true;
		} catch (Exception e) {
			logger.error("setObject", e);
			return false;
		}finally{
			returnResource(jedis);
		}
	}
	
	/**
	 * 删除缓存中得对象，根据key
	 * @param key
	 * @return
	 */
	public static boolean delete(String key){
		ShardedJedis jedis = null;
		try {
			jedis = getPool().getResource();
			jedis.del(key);
			return true;
		} catch (Exception e) {
			logger.error("delete", e);
			return false;
		}finally{
			returnResource(jedis);
		}
	}
	
	/**
	 * 根据key 获取内容
	 * @param key
	 * @return
	 */
	public static String getString(String key){
		ShardedJedis jedis = null;
		try {
			jedis = getPool().getResource();
			Object value = jedis.get(key);
			return (String)value;
		} catch (Exception e) {
			logger.error("getString", e);
			return null;
		}finally{
			returnResource(jedis);
		}
	}
	

	/**
	 * 根据key 获取对象
	 * @param key
	 * @return
	 */
	public static Object getObject(String key){
		ShardedJedis jedis = null;
		try {
			jedis = getPool().getResource();
			byte[] values = jedis.get(key.getBytes());
			if(values != null && values.length>0){
				
				return SerializeUtil.unserialize(values);
			}
			return null;
		} catch (Exception e) {
			logger.error("getObject", e);
			return null;
		}finally{
			returnResource(jedis);
		}
	}
	
	/**
	 * 根据key设置map对象
	 * @param key
	 * @param mapVal
	 * @return
	 */
	public static boolean setMap(String key,Map<String,String> mapVal){
		ShardedJedis jedis = null;
		try {
			jedis = getPool().getResource();
			jedis.hmset(key, mapVal);
			return true;
		} catch (Exception e) {
			logger.error("getObject", e);
			return false;
		}finally{
			returnResource(jedis);
		}
	}
	
	/**
	 * 设置过期时间
	 * @param key
	 * @param seconds
	 * @return
	 * @throws Exception
	 */
	public static boolean  setExpire(String key,int seconds){
		ShardedJedis jedis = null;
		try {
			jedis = getPool().getResource();
			jedis.expire(key, seconds);
			return true;
		} catch (Exception e) {
			logger.error("setExpire", e);
			return false;
		}finally{
			returnResource(jedis);
		}
	}
	
	
}