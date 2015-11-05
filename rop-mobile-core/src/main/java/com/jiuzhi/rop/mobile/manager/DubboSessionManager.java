package com.jiuzhi.rop.mobile.manager;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appleframework.core.utils.ObjectUtility;
import com.appleframework.rop.session.Session;
import com.appleframework.rop.session.SessionManager;
import com.appleframework.security.core.auth.Authentication;
import com.appleframework.security.core.token.TokenServices;
import com.jiuzhi.biz.user.entity.MemberAccount;
import com.jiuzhi.rop.mobile.session.MemberSession;

public class DubboSessionManager implements SessionManager{
	
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private TokenServices tokenServices;
    
    public void setTokenServices(TokenServices tokenServices) {
		this.tokenServices = tokenServices;
	}

    @Override
    public void addSession(String sessionId, Session session) {
        //sessionCache.put(sessionId, session);
    }

    @Override
    public Session getSession(String sessionId) {
    	try {
    		if(ObjectUtility.isEmpty(sessionId))
    			return null;
    		Authentication auth = tokenServices.loadAuthentication(sessionId);
        	if(ObjectUtility.isEmpty(auth))
        		return null;
        	MemberAccount account = (MemberAccount)auth.getUser();
        	Session session = new MemberSession();
        	session.setAttribute(MemberSession.ATTRIBUTE_MEMBER_ACCOUNT_KEY, account);
            return session;
		} catch (Exception e) {
			logger.error("DubboSessionManager:session=" + sessionId + "已经失效");
			return null;
		}
    }

    @Override
    public void removeSession(String sessionId) {
    	//tokenServices.remove(sessionId);
    }
}