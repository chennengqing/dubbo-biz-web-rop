/*package com.jiuzhi.ad.rop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.appleframework.config.core.PropertyConfigurer;
import com.appleframework.exception.AppleException;
import com.appleframework.rop.annotation.NeedInSessionType;
import com.appleframework.rop.annotation.ServiceMethod;
import com.appleframework.rop.annotation.ServiceMethodBean;
import com.jiuzhi.ad.rop.request.AdRequest;
import com.jiuzhi.ad.rop.request.AdStatRequest;
import com.jiuzhi.ad.rop.response.AdResponse;
import com.jiuzhi.ad.rop.response.AdStatResponse;
import com.jiuzhi.ad.rop.response.AdVo;
import com.jiuzhi.ad.rop.response.SlotVo;
import com.jiuzhi.biz.ad.entity.Ad;
import com.jiuzhi.biz.ad.entity.AdSetting;
import com.jiuzhi.biz.ad.entity.AdStat;
import com.jiuzhi.biz.ad.service.AdService;
import com.jiuzhi.biz.ad.service.AdSettingService;
import com.jiuzhi.biz.ad.service.AdStatService;
import com.jiuzhi.rop.mobile.controller.BaseController;


@ServiceMethodBean(version = "1.0")
public class AdController extends BaseController {
	private static Logger logger = Logger.getLogger(AdController.class);
	@Resource
	private AdService adService;
	
	@Resource
	private AdSettingService adSettingService;
	@Resource
	private AdStatService adStatService;
	
	
	@ServiceMethod(method = "jz.ad.mobile.kp", needInSession = NeedInSessionType.NO)
	public Object list(AdRequest adRequest){
		String appkey=adRequest.getRopRequestContext().getAppKey();
		String clnt=getHeader(adRequest, "clnt");
		AdResponse res = new AdResponse();
		AdSetting  adSetting =	adSettingService.get(1);
		List<AdVo> listAv=new ArrayList<AdVo>();
		List<Ad> list = adService.findOnlineByAppAndType(1, 1);
		for (Ad ad : list) {
			AdVo av=new AdVo();
			av.setDt(ad.getDt().getTime());
			av.setImg(ad.getImgurl());
			av.setShowtime(ad.getShowtime());
			av.setUrl(PropertyConfigurer.getString("url")+"?url="+ad.getUrl()+"&method=jz.ad&appkey="+appkey+"&id="+ad.getId()+"&clnt="+clnt);
			av.setId(ad.getId());
			av.setIsJump(ad.getIsjump());
			av.setOpen(ad.getOpen());
			List<SlotVo> listsl=new ArrayList<SlotVo>();
			String[] sl=ad.getSlot().split(",");
			for (String ss : sl) {
				String[] s=ss.split("-");
				SlotVo sv=new SlotVo();
				sv.setStart(s[0]);
				sv.setEnd(s[1]);
				listsl.add(sv);
			}
			av.setSlot(listsl);
			listAv.add(av);
		}
		
		res.setApart(adSetting.getApart());
		res.setHcurl(adSetting.getHcurl());
		res.setIswifi(adSetting.getIswifi());
		res.setLoading(adSetting.getLoading());
		res.setTimes(adSetting.getTimes());
		res.setAd(listAv);
		ObjectMapper objectmapper=new ObjectMapper();
		try {
			logger.info(objectmapper.writeValueAsString(res));
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	

	@ServiceMethod(method = "jz.ad.mobile.stat", needInSession = NeedInSessionType.NO)
	public Object stat(AdStatRequest adStatRequest){
		String appkey=adStatRequest.getRopRequestContext().getAppKey();
		String clnt=getHeader(adStatRequest, "clnt");
		AdStatResponse res=new AdStatResponse();
		String ss[]=adStatRequest.getAmount().split(",");
		for (String s : ss) {
			String stat[]=s.split(":");
			AdStat adStat=new AdStat();
			adStat.setCount(Integer.parseInt( stat[1]));
			adStat.setAdid(Integer.parseInt( stat[0]));
			adStat.setCreatetime(new Date());
			adStat.setAppkey(appkey);
			adStat.setClnt(clnt);
			try {
				adStatService.insert(adStat);
			} catch (AppleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		res.setIsSuccess(true);
		return res;
	}
	
	*//**
	 * 通过应用类型和广告类型
	 * @param adRequest
	 * @return
	 *//*
	@ServiceMethod(method = "jz.ad.mobile.list", needInSession = NeedInSessionType.NO)
	public Object listByAppAndType(AdRequest adRequest){
		String appkey=adRequest.getRopRequestContext().getAppKey();
		String clnt= getHeader(adRequest, "clnt");
		if(clnt == null)
			clnt = "";
		AdResponse res = new AdResponse();
		try {
			List<AdVo> listAv = new ArrayList<AdVo>();
			List<Ad> list = adService.findOnlineByAppAndType(adRequest.getApp(), adRequest.getAdtype());
			for (Ad ad : list) {
				AdVo av=new AdVo();
				av.setImg(ad.getImgurl());
				//跳转地址转码
				String url = java.net.URLEncoder.encode(ad.getUrl(), "utf-8");
				av.setUrl(PropertyConfigurer.getString("url")+"?url="+url+"&method=jz.ad&appkey="+appkey+"&id="+ad.getId()+"&clnt="+clnt);
				av.setId(ad.getId());
				av.setIsJump(ad.getIsjump());
				av.setOpen(ad.getOpen());
				listAv.add(av);
			}
			res.setAd(listAv);
			ObjectMapper objectmapper=new ObjectMapper();
			logger.info(objectmapper.writeValueAsString(res));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
*/