package org.cnq.biz.test.provider.service;

import org.cnq.biz.test.entity.UserInfo;

import com.appleframework.exception.AppleException;

public interface UserInfoService {

	public UserInfo getById(Integer userId) throws AppleException;
}
