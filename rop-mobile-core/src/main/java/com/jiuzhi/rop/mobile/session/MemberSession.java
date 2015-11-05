package com.jiuzhi.rop.mobile.session;

import com.appleframework.rop.session.AbstractSession;
import com.jiuzhi.biz.user.entity.MemberAccount;

public class MemberSession extends AbstractSession {

	private static final long serialVersionUID = 5112340623290890760L;
	
	public static String ATTRIBUTE_MEMBER_ACCOUNT_KEY = "MEMBER_ACCOUNT";

	public MemberAccount getMemberAccount() {
		return (MemberAccount) super.getAttribute(ATTRIBUTE_MEMBER_ACCOUNT_KEY);
	}
	
	public Long getAccountId() {
		return getMemberAccount().getId();
	}

	public String getMobile() {
		return getMemberAccount().getMobile();
	}
	
	public String getUsername() {
		return getMemberAccount().getUsername();
	}

	public String getNickName() {
		return getMemberAccount().getNickName();
	}
	
}
