package com.jiuzhi.ad.rop.util;

import java.math.BigDecimal;

/**
 * 
 * 项目名称:rop-zdb   
 * 类名称:DoubleUtil   
 * 类描述:浮点类型工具类   
 * 创建人:hejunwen   
 * 创建时间:2015-1-9 上午4:06:35   
 * @version
 */
public class DoubleUtil {

	/**
	 * 
	 * @Title: getTwoLent
	 * @Description: 保留两位小数
	 * @param:  (传入参数) 
	 * @return: Double    返回类型
	 * @throws
	 */
	public static Double getTwoLent(Double value){
        
        BigDecimal bg = new BigDecimal(value);
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f1;
	}
}
