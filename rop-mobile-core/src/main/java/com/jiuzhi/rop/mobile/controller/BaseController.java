package com.jiuzhi.rop.mobile.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.appleframework.core.utils.ObjectUtility;
import com.appleframework.model.Operator;
import com.appleframework.model.OperatorType;
import com.appleframework.rop.AbstractRopRequest;
/*import com.jiuzhi.biz.user.entity.MemberAccount;*/
import com.jiuzhi.rop.mobile.session.MemberSession;

public class BaseController {

	/*public MemberAccount getUserAccount(AbstractRopRequest request) {
		MemberSession session = (MemberSession) request.getRopRequestContext().getSession();
		if(null != session)
			return session.getMemberAccount();
		else
			return null;
	}*/
	
	public MemberSession getSession(AbstractRopRequest request) {
		MemberSession session = (MemberSession) request.getRopRequestContext().getSession();
		return session;
	}
	
	public Locale getLocale(AbstractRopRequest request) {
		return request.getRopRequestContext().getLocale();
	}
	
	public String getMethod(AbstractRopRequest request) {
		return request.getRopRequestContext().getMethod();
	}
		
	public String getDevice(AbstractRopRequest request) {
		return this.getHeader(request, "device");
	}
	
	public String getUuid(AbstractRopRequest request) {
		return this.getHeader(request, "uuid");
	}
	
	public String getVersion(AbstractRopRequest request) {
		return this.getHeader(request, "version");
	}
	
	public String getHeader(AbstractRopRequest request, String code) {
		HttpServletRequest httpRequest = (HttpServletRequest)request.getRopRequestContext().getRawRequestObject();
		return httpRequest.getHeader(code);
	}
	
	/*public Operator getOperator(AbstractRopRequest request) {
		MemberAccount account = this.getUserAccount(request);
		if(ObjectUtility.isEmpty(account))
			return Operator.creat(OperatorType.USER, null);
		return Operator.creat(OperatorType.USER, account.getId());
	}
	
	public Operator getOperator2(AbstractRopRequest request) {
		MemberAccount account = this.getUserAccount(request);
		if(ObjectUtility.isEmpty(account))
			return Operator.creat(OperatorType.USER, null);
		return Operator.creat(OperatorType.USER, account);
	}*/
}
