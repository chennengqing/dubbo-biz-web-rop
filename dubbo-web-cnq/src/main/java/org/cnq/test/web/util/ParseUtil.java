package org.cnq.test.web.util;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;




public class ParseUtil {

	 /**
	  * 将字符串转换成ArrayList
	  * @param str
	  * @return
	  */
	 public static ArrayList<String> asArrayList(String str)
	 {
	    return  parseString(new ArrayList<String>() , str, null);
	 }

	 /**
	  * 将字符串转换成ArrayList
	  * @param str
	  * @param separator
	  * @return
	  */
	 public static ArrayList<String> asArrayList(String str, char separator)
	 {
	    return  parseString(new ArrayList<String> (), str, separator + "");
	 }
	 
	 /**
	  * 将字符串转换成ArrayList
	  * @param list
	  * @param str
	  * @param delimiter
	  * @return
	  */
	 private static ArrayList<String> parseString(ArrayList<String> list, String str, String delimiter)
	  {
	    if (delimiter == null) {
	       delimiter = Globals.SEPARATOR1 + "";
	    }

	    int i = 0;

	    while ((i = str.indexOf(delimiter)) != -1) {
	      String s = str.substring(0, i);

	      if (s != null)
	        s = s.trim();
	      else {
	        s = "";
	      }

	      list.add(s);
	      str = str.substring(i + 1);
	    }

	    list.add(str);

	    return list;
	  }
	 /**
	  * 解析以","分隔的图片组，返回完整的图片链接数组
	  * @param imageServer
	  * @param images
	  * @return
	  */
	 public static String[] parseImages(String imageServer, String images) {
	     String[] imgs = StringUtils.split(images, Globals.GLOBAL_SPLIT);
	     if(imgs==null) {
	         return new String[]{};
	     }
	     for(int i=0;i<imgs.length;i++) {
	         imgs[i] = imageServer + "/" + imgs[i];
	     }
	     return imgs;
	 }

}
