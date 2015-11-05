/**
 * 版权声明：中图一购网络科技有限公司 版权所有 违者必究 2012 
 * 日    期：12-7-14
 */
package com.jiuzhi.rop.mobile.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.appleframework.rop.AbstractRopRequest;

public class AccessTokenRequest extends AbstractRopRequest {

	@NotNull
	private String appkey;
	
	@NotNull
	private String appsecret;

	@NotNull
	private String username;
	
	@NotNull
	private String password;
	
	@NotNull
	@Pattern(regexp = "([0-9]{1})")
	private String accountType;
	
	@NotNull
	@Pattern(regexp = "([0-9A-Fa-f]{2})([0-9A-Fa-f]{2}){5}")
	private String mac;

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}
	
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public AccessTokenRequest(String appkey, String appsecret,
			String username, String password, String mac, String accountType) {
		super();
		this.appkey = appkey;
		this.appsecret = appsecret;
		this.username = username;
		this.password = password;
		this.mac = mac;
		this.accountType = accountType;
	}

	@Override
	public String toString() {
		return "AccessTokenRequest [appkey=" + appkey + ", appsecret="
				+ appsecret + ", username=" + username + ", password="
				+ password + ", mac=" + mac + "]";
	}

}
