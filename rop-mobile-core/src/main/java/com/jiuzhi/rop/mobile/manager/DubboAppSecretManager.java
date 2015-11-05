package com.jiuzhi.rop.mobile.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appleframework.rop.security.AppSecretManager;
import com.appleframework.security.core.client.ClientDetails;
import com.appleframework.security.core.client.ClientDetailsService;

public class DubboAppSecretManager implements AppSecretManager {
	
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private ClientDetailsService clientDetailsService;    

    public void setClientDetailsService(
    		ClientDetailsService clientDetailsService) {
		this.clientDetailsService = clientDetailsService;
	}

    @Override
    public String getSecret(String appKey) {
    	logger.info("use dubbo AppSecretManager!");
    	ClientDetails detail = clientDetailsService.loadClientByClientId(appKey);
    	if(null == detail)
    		return null;
        return detail.getClientSecret();
    }

    @Override
    public boolean isValidAppKey(String appKey) {
        return getSecret(appKey) != null;
    }
}