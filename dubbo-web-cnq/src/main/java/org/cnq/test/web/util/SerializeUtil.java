package org.cnq.test.web.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializeUtil {

	
	/**
	 * 序列列对象
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
		   //序列化
		    baos = new ByteArrayOutputStream();
		    oos = new ObjectOutputStream(baos);
		    oos.writeObject(object);
		    byte[] bytes = baos.toByteArray();
		    return bytes;
		    
		} catch (Exception e) {
		 
		}
		finally{
			
			try {
				if(oos != null){
					
					oos.close();
					oos = null;
				}
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		return null;
	}
		 
	/**
	 * 反序列对对象
	 * @param bytes
	 * @return
	 */
    public static Object unserialize(byte[] bytes) {
    	
		ByteArrayInputStream bais = null;
		try {
		    //反序列化
		   bais = new ByteArrayInputStream(bytes);
		   ObjectInputStream ois = new ObjectInputStream(bais);
		   return ois.readObject();
		} catch (Exception e) {
		 
		}
		finally{
			
			if(bais != null){
				try {
					bais.close();
					bais = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
