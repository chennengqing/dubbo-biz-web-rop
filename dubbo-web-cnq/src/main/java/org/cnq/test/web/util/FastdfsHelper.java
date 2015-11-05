package org.cnq.test.web.util;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appleframework.config.core.PropertyConfigurer;


public class FastdfsHelper {

	private static boolean hasInit = false;
	private String groupName = "ad";
	private static Logger logger = LoggerFactory.getLogger(FastdfsHelper.class);
	TrackerClient trackerClient = null;
  
	
	public FastdfsHelper()
	{

		 initConfig();
		
	}
	
	public FastdfsHelper(String groupName)
	{
		
	    this.groupName = groupName;
	    initConfig();
	
		
	}
	
	private void initConfig(){
		
		if(!hasInit){
			try {
	            		
	            ClientGlobal.setG_connect_timeout(PropertyConfigurer.getInteger("connect_timeout"));
	            ClientGlobal.setG_network_timeout(PropertyConfigurer.getInteger("network_timeout"));
	            ClientGlobal.setG_charset(PropertyConfigurer.getString("charset"));
	            ClientGlobal.setG_tracker_http_port(PropertyConfigurer.getInteger("http.tracker_http_port"));
	            ClientGlobal.setG_anti_steal_token(false);
	            ClientGlobal.setG_secret_key(PropertyConfigurer.getString("http.secret_key"));
	            
	            String tracker_server= PropertyConfigurer.getString("tracker_server");
	            
	            InetSocketAddress[] tracker_servers = new InetSocketAddress[1];  
	            String[] ss= tracker_server.split(":");
	            
	            InetSocketAddress in=new InetSocketAddress(ss[0], Integer.parseInt(ss[1]));
	            tracker_servers[0]=in;
	            
	            ClientGlobal.setG_tracker_group(new TrackerGroup(tracker_servers)); 
	 
				hasInit = true;
				
			} catch (Exception e) {
				
				logger.error("initConfig", e);
			}
			
		}
		trackerClient = new TrackerClient(); 
		
          
	}
	
	
	/**
	 * 上传html文件
	 * @param file_buff
	 * @return
	 */
	public String uploadHtmlFile(byte[] file_buff){
		
		return uploadFile(file_buff, "html", null);
    }
	
	
	/**
	 * 上传图片文件
	 * @param file_buff
	 * @param file_ext_name
	 * @return
	 */
    public String uploadImageFile(byte[] file_buff,String file_ext_name){
			
		return uploadFile(file_buff, file_ext_name, null);
	}
	
    /**
     * 上传APK文件
     * @param file_buff
     * @return
     */
    public String uploadApkFile(byte[] file_buff){
		
		return uploadFile(file_buff, "apk", null);
	}
	
	public String uploadFile(byte[] file_buff,String file_ext_name){
		
		return uploadFile(file_buff, file_ext_name, null);
	}
	
	/**
	 * 上传文件
	 * @param file_buff
	 * @param file_ext_name
	 * @param meta_list
	 * @return
	 */
	public String uploadFile(byte[] file_buff,String file_ext_name,NameValuePair[] meta_list)
	{
		 String fileName = "";
		 TrackerServer trackerServer = null;
	     try {
	    	 
	    	trackerServer = trackerClient.getConnection();
	    	StorageServer storageServer = trackerClient.getStoreStorage(trackerServer,groupName);
		    StorageClient client = new StorageClient(trackerServer, storageServer);  
			String[] results = client.upload_file(file_buff, file_ext_name, meta_list);
			if(results == null){
				
				return fileName;
			}
			fileName = results[0]+"/"+results[1];
			
		} 
	     catch (Exception e) {
			
	    	 logger.error("上传文件到FastDFS失败", e);
		}
	    finally{
	    	
	    	try {
				trackerServer.close();
			} catch (IOException e) {
				logger.error("uploadFile", e);
			}
	    }
		
		return fileName;
		
	}
	
	/**
	 * 下载文件
	 * @param fullFileName
	 * @return
	 */
	public byte[] download(String fullFileName)
	{
		
		 byte[] buff = null;
		 TrackerServer trackerServer = null;
	     try {
	    	 
	        int index = fullFileName.indexOf("/");
	        String group = fullFileName.substring(0,index);
	        String fileName = fullFileName.substring(index+1,fullFileName.length());
	        trackerServer = trackerClient.getConnection();
	    	StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
		    StorageClient client = new StorageClient(trackerServer, storageServer);  
		    buff = client.download_file(group, fileName);
		   
			
		} 
	     catch (Exception e) {
			
	    	 logger.error("从FastDFS下载文件失败", e);
		}
	    finally{
	    	
	    	try {
				trackerServer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("uploadFile", e);
			}
	    }
		
		return buff;
		
	}
	
	/**
	 * 删除文件
	 * @param fullFileName
	 * @return
	 */
	public boolean deleteFile(String fullFileName){
		
		
		 TrackerServer trackerServer = null;
	     try {
	    	 
	        int index = fullFileName.indexOf("/");
	        String group = fullFileName.substring(0,index);
	        String fileName = fullFileName.substring(index+1,fullFileName.length());
	        trackerServer = trackerClient.getConnection();
	    	StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
		    StorageClient client = new StorageClient(trackerServer, storageServer);  
		    int ret = client.delete_file(group, fileName);
		    if(ret == 0)
		    	return true;
		   
			
		} 
	     catch (Exception e) {
			
	    	 logger.error("从FastDFS删除文件失败", e);
		}
	    finally{
	    	
	    	try {
				trackerServer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("uploadFile", e);
			}
	    }
	     
	    return false;
		
		
	}
}
