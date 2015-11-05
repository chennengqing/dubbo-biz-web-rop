package com.jiuzhi.ad.rop.test.controller;

import org.junit.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.appleframework.rop.utils.RopUtils;

public class AdClient {

	public static final String SERVER_URL = "http://localhost:8002/router";
	public static final String APP_KEY = "jz-yaya";
	public static final String APP_SECRET = "jiuzhi";
	
	@Test
	public void testa() {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();

		form.add("method", "jz.ad.mobile.list");
		form.add("appkey", APP_KEY);
		form.add("v", "1.0");
		//form.add("locale", "zh_CN");
		form.add("format", "json");
		form.add("app", "1");
		form.add("adtype", "1");
		
		// 对请求参数列表进行签名
		String sign = RopUtils.sign(form.toSingleValueMap(), APP_SECRET);
		System.out.println(sign);
		form.add("sign", sign);
		String response = restTemplate.postForObject(SERVER_URL, form, String.class);
		System.out.println("response:\n" + response);
	}
	
}
