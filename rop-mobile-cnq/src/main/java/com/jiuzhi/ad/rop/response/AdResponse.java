package com.jiuzhi.ad.rop.response;

import java.util.List;

public class AdResponse {

	private int loading;
	private int iswifi;
	private String hcurl;
	private int times;
	private int apart;
	private List<AdVo> ad;

	public int getLoading() {
		return loading;
	}

	public void setLoading(int loading) {
		this.loading = loading;
	}

	public int getIswifi() {
		return iswifi;
	}

	public void setIswifi(int iswifi) {
		this.iswifi = iswifi;
	}

	public String getHcurl() {
		return hcurl;
	}

	public void setHcurl(String hcurl) {
		this.hcurl = hcurl;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public List<AdVo> getAd() {
		return ad;
	}

	public void setAd(List<AdVo> ad) {
		this.ad = ad;
	}

	public int getApart() {
		return apart;
	}

	public void setApart(int apart) {
		this.apart = apart;
	}

}
