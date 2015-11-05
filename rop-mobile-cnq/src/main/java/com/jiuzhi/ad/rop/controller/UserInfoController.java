package com.jiuzhi.ad.rop.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.cnq.biz.test.entity.UserInfo;
import org.cnq.biz.test.provider.service.UserInfoService;

import com.appleframework.exception.AppleException;
import com.appleframework.rop.annotation.NeedInSessionType;
import com.appleframework.rop.annotation.ServiceMethod;
import com.appleframework.rop.annotation.ServiceMethodBean;
import com.jiuzhi.ad.rop.request.UserInfoRequest;
import com.jiuzhi.ad.rop.response.UserInfoResponse;
import com.jiuzhi.rop.mobile.controller.BaseController;

@ServiceMethodBean(version = "1.0")
public class UserInfoController extends BaseController {

	private static Logger logger = Logger.getLogger(UserInfoController.class);
	
	@Resource
	private UserInfoService userInfoService;
	
	@ServiceMethod(method = "cnq.get.user", needInSession = NeedInSessionType.NO)
	public Object stat(UserInfoRequest userInfoRequest){
		UserInfo userInfo = null;
		try {
			userInfo = userInfoService.getById(userInfoRequest.getUserId());
		} catch (AppleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserInfoResponse res = new UserInfoResponse();
		res.setUserinfo(userInfo);
		return res;
	}
}
